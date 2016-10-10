package org.trinity.yqyl.process.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.criteria.CriteriaBuilder.In;
import javax.persistence.criteria.Predicate;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.trinity.common.accessright.ISecurityUtil;
import org.trinity.common.exception.IException;
import org.trinity.message.LookupParser;
import org.trinity.process.converter.IObjectConverter;
import org.trinity.yqyl.common.message.dto.domain.ServiceCategoryDto;
import org.trinity.yqyl.common.message.dto.domain.ServiceInfoDto;
import org.trinity.yqyl.common.message.dto.domain.ServiceOrderDto;
import org.trinity.yqyl.common.message.dto.domain.ServiceOrderSearchingDto;
import org.trinity.yqyl.common.message.dto.domain.ServiceSubOrderDto;
import org.trinity.yqyl.common.message.dto.domain.ServiceSupplierClientDto;
import org.trinity.yqyl.common.message.exception.ErrorMessage;
import org.trinity.yqyl.common.message.lookup.AccessRight;
import org.trinity.yqyl.common.message.lookup.OrderStatus;
import org.trinity.yqyl.common.message.lookup.RecordStatus;
import org.trinity.yqyl.process.controller.base.AbstractAutowiredCrudProcessController;
import org.trinity.yqyl.process.controller.base.IOrderProcessController;
import org.trinity.yqyl.repository.business.dataaccess.IServiceInfoRepository;
import org.trinity.yqyl.repository.business.dataaccess.IServiceOrderRepository;
import org.trinity.yqyl.repository.business.dataaccess.IServiceSubOrderRepository;
import org.trinity.yqyl.repository.business.dataaccess.IUserRepository;
import org.trinity.yqyl.repository.business.entity.ServiceCategory;
import org.trinity.yqyl.repository.business.entity.ServiceInfo;
import org.trinity.yqyl.repository.business.entity.ServiceInfo_;
import org.trinity.yqyl.repository.business.entity.ServiceOrder;
import org.trinity.yqyl.repository.business.entity.ServiceOrder_;
import org.trinity.yqyl.repository.business.entity.ServiceSubOrder;
import org.trinity.yqyl.repository.business.entity.ServiceSubOrder_;
import org.trinity.yqyl.repository.business.entity.ServiceSupplierClient;
import org.trinity.yqyl.repository.business.entity.ServiceSupplierClient_;
import org.trinity.yqyl.repository.business.entity.User;

@Service
public class ServiceOrderProcessController
		extends AbstractAutowiredCrudProcessController<ServiceOrder, ServiceOrderDto, ServiceOrderSearchingDto, IServiceOrderRepository>
		implements IOrderProcessController {
	@Autowired
	private ISecurityUtil<AccessRight> securityUtil;

	@Autowired
	private IUserRepository userRepository;

	@Autowired
	private IServiceSubOrderRepository serviceSubOrderRepository;

	@Autowired
	private IServiceInfoRepository serviceInfoRepository;

	@Autowired
	private IObjectConverter<ServiceInfo, ServiceInfoDto> serviceInfoConverter;

	@Autowired
	private IObjectConverter<ServiceCategory, ServiceCategoryDto> serviceCategoryConverter;

	@Autowired
	private IObjectConverter<ServiceSubOrder, ServiceSubOrderDto> serviceSubOrderConverter;

	@Autowired
	private IObjectConverter<ServiceSupplierClient, ServiceSupplierClientDto> serviceSupplierClientConverter;

	public ServiceOrderProcessController() {
		super(ServiceOrder.class, ErrorMessage.UNABLE_TO_FIND_SERVICE_ORDER);
	}

	@Override
	public Page<ServiceOrderDto> getAll(final ServiceOrderSearchingDto dto) throws IException {
		final Pageable pagable = pagingConverter.convert(dto);

		final Specification<ServiceOrder> specification = (root, query, cb) -> {
			final List<Predicate> predicates = new ArrayList<>();

			if (!StringUtils.isEmpty(dto.getReceiverUserName())) {
				final User user = userRepository.findOneByUsername(dto.getReceiverUserName());
				predicates.add(cb.equal(root.get(ServiceOrder_.user), user));
			}

			if (!dto.getStatus().isEmpty()) {
				final In<OrderStatus> in = cb.in(root.get(ServiceOrder_.status));
				dto.getStatus().forEach(item -> in.value(LookupParser.parse(OrderStatus.class, item)));
				predicates.add(in);
			}

			if (dto.getServiceSupplierClientId() != null) {
				predicates.add(cb.equal(
						root.join(ServiceOrder_.serviceSubOrders).join(ServiceSubOrder_.serviceInfo)
								.join(ServiceInfo_.serviceSupplierClient).get(ServiceSupplierClient_.userId),
						dto.getServiceSupplierClientId()));

				query.distinct(true);
			}

			return cb.and(predicates.toArray(new Predicate[0]));
		};

		final Page<ServiceOrder> findAll = getDomainEntityRepository().findAll(specification, pagable);

		return findAll.map(item -> {
			final ServiceOrderDto serviceOrderDto = getDomainObjectConverter().convert(item);

			final List<ServiceSubOrder> serviceSubOrders = item.getServiceSubOrders();
			final List<ServiceSubOrderDto> serviceSubOrderDtos = serviceSubOrders.stream().map(serviceSubOrder -> {
				final ServiceSubOrderDto serviceSubOrderDto = serviceSubOrderConverter.convert(serviceSubOrder);

				final ServiceInfo serviceInfo = serviceSubOrder.getServiceInfo();
				final ServiceInfoDto serviceInfoDto = serviceInfoConverter.convert(serviceInfo);

				final ServiceCategory serviceCategory = serviceInfo.getServiceCategory();
				final ServiceCategoryDto serviceCategoryDto = serviceCategoryConverter.convert(serviceCategory);

				final ServiceSupplierClient serviceSupplierClient = serviceInfo.getServiceSupplierClient();
				final ServiceSupplierClientDto serviceSupplierDto = serviceSupplierClientConverter.convert(serviceSupplierClient);

				serviceInfoDto.setServiceCategory(serviceCategoryDto);
				serviceInfoDto.setServiceSupplierClient(serviceSupplierDto);
				serviceSubOrderDto.setService(serviceInfoDto);

				return serviceSubOrderDto;
			}).collect(Collectors.toList());

			serviceOrderDto.setServiceSubOrders(serviceSubOrderDtos);

			if (dto.getServiceSupplierClientId() != null) {
				serviceOrderDto.setUsername(item.getUser().getUsername());
			}

			return serviceOrderDto;
		});
	}

	@Override
	@Transactional
	public ServiceOrderDto getOne(final Long id) throws IException {
		final ServiceOrder entity = getDomainEntityRepository().findOne(id);
		if (entity == null) {
			throw getExceptionFactory().createException(getNoInstanceFoundError(), String.valueOf(id));
		}

		final ServiceOrderDto dto = getDomainObjectConverter().convert(entity);

		final List<ServiceSubOrderDto> serviceSubOrders = entity.getServiceSubOrders().stream().map(item -> {
			final ServiceSubOrderDto serviceSubOrderDto = serviceSubOrderConverter.convert(item);
			final ServiceInfoDto serviceInfoDto = serviceInfoConverter.convert(item.getServiceInfo());
			serviceInfoDto
					.setServiceSupplierClient(serviceSupplierClientConverter.convert(item.getServiceInfo().getServiceSupplierClient()));

			serviceSubOrderDto.setService(serviceInfoDto);
			return serviceSubOrderDto;
		}).collect(Collectors.toList());

		dto.setServiceSubOrders(serviceSubOrders);

		return dto;
	}

	@Override
	@Transactional
	public ServiceOrderDto proposeOrder(final ServiceOrderDto serviceOrderDto) throws IException {
		final User user = userRepository.findOneByUsername(securityUtil.getCurrentToken().getUsername());

		final ServiceOrder serviceOrder = new ServiceOrder();
		serviceOrder.setPrice(0d);
		serviceOrder.setProposalTime(new Date());
		serviceOrder.setStatus(OrderStatus.AWAITING_PAYMENT);
		serviceOrder.setUser(user);

		final List<ServiceSubOrder> serviceSubOrders = serviceOrderDto.getServiceSubOrders().stream().map(item -> {
			final ServiceSubOrder subOrder = serviceSubOrderConverter.convertBack(item);

			final ServiceInfo serviceInfo = serviceInfoRepository.findOne(item.getService().getId());

			subOrder.setId(null);
			subOrder.setPrice(serviceInfo.getPrice());
			subOrder.setServiceInfo(serviceInfo);
			subOrder.setStatus(RecordStatus.ACTIVE);
			subOrder.setServiceOrder(serviceOrder);

			serviceOrder.setPrice(serviceOrder.getPrice() + subOrder.getPrice());

			return subOrder;
		}).collect(Collectors.toList());

		if (serviceSubOrders.isEmpty()) {
			throw getExceptionFactory().createException(ErrorMessage.NO_SUB_ORDERS);
		}

		getDomainEntityRepository().save(serviceOrder);
		serviceSubOrderRepository.save(serviceSubOrders);

		return getDomainObjectConverter().convert(serviceOrder);
	}
}
