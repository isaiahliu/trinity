package org.trinity.yqyl.process.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.persistence.criteria.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.trinity.common.accessright.ISecurityUtil;
import org.trinity.common.exception.IException;
import org.trinity.process.converter.IObjectConverter;
import org.trinity.yqyl.common.message.dto.domain.ServiceCategoryDto;
import org.trinity.yqyl.common.message.dto.domain.ServiceSupplierClientDto;
import org.trinity.yqyl.common.message.dto.domain.ServiceSupplierClientSearchingDto;
import org.trinity.yqyl.common.message.exception.ErrorMessage;
import org.trinity.yqyl.common.message.lookup.AccessRight;
import org.trinity.yqyl.common.message.lookup.PersonalType;
import org.trinity.yqyl.common.message.lookup.RecordStatus;
import org.trinity.yqyl.common.message.lookup.ServiceSupplierClientStatus;
import org.trinity.yqyl.process.controller.base.AbstractAutowiredCrudProcessController;
import org.trinity.yqyl.process.controller.base.IServiceSupplierClientProcessController;
import org.trinity.yqyl.repository.business.dataaccess.IContentRepository;
import org.trinity.yqyl.repository.business.dataaccess.IServiceCategoryRepository;
import org.trinity.yqyl.repository.business.dataaccess.IServiceSupplierClientRepository;
import org.trinity.yqyl.repository.business.dataaccess.IUserRepository;
import org.trinity.yqyl.repository.business.entity.Content;
import org.trinity.yqyl.repository.business.entity.ServiceCategory;
import org.trinity.yqyl.repository.business.entity.ServiceCategory_;
import org.trinity.yqyl.repository.business.entity.ServiceInfo;
import org.trinity.yqyl.repository.business.entity.ServiceInfo_;
import org.trinity.yqyl.repository.business.entity.ServiceSupplierClient;
import org.trinity.yqyl.repository.business.entity.ServiceSupplierClient_;
import org.trinity.yqyl.repository.business.entity.User;

@Service
public class ServiceSupplierClientProcessController extends
		AbstractAutowiredCrudProcessController<ServiceSupplierClient, ServiceSupplierClientDto, ServiceSupplierClientSearchingDto, IServiceSupplierClientRepository>
		implements IServiceSupplierClientProcessController {
	@Autowired
	private IUserRepository userRepository;

	@Autowired
	private ISecurityUtil<AccessRight> securityUtil;

	@Autowired
	private IContentRepository contentRepository;

	@Autowired
	private IServiceCategoryRepository serviceCategoryRepository;

	@Autowired
	private IObjectConverter<ServiceCategory, ServiceCategoryDto> serviceCategoryConverter;

	public ServiceSupplierClientProcessController() {
		super(ServiceSupplierClient.class, ErrorMessage.UNABLE_TO_FIND_SERVICE_SUPPLIER_CLIENT);
	}

	@Override
	public Page<ServiceSupplierClientDto> getAll(final ServiceSupplierClientSearchingDto searchingData) throws IException {
		final List<Long> categoryIds = new ArrayList<>();
		final Pageable pagable = pagingConverter.convert(searchingData);

		if (searchingData.getCategoryChildren().isEmpty()) {
			if (searchingData.getCategoryParent() != null) {
				final ServiceCategory category = serviceCategoryRepository.findOne(searchingData.getCategoryParent());
				if (category != null) {
					categoryIds.addAll(serviceCategoryRepository.findAllByParent(category).stream().map(item -> item.getId())
							.collect(Collectors.toList()));
				}
			}
		} else {
			serviceCategoryRepository.findAll(searchingData.getCategoryChildren()).forEach(item -> categoryIds.add(item.getId()));
		}

		final Specification<ServiceSupplierClient> specification = (root, query, cb) -> {
			final List<Predicate> predicates = new ArrayList<>();

			if (!categoryIds.isEmpty()) {
				predicates.add(root.join(ServiceSupplierClient_.serviceInfos).join(ServiceInfo_.serviceCategory).get(ServiceCategory_.id)
						.in(categoryIds));
				query.distinct(true);
			}

			return cb.and(predicates.toArray(new Predicate[0]));
		};

		return getDomainEntityRepository().findAll(specification, pagable).map(item -> {
			final ServiceSupplierClientDto dto = getDomainObjectConverter().convert(item);

			final List<ServiceInfo> serviceInfos = item.getServiceInfos();
			final Map<Long, ServiceCategory> map = new HashMap<>();
			double expectedPrice = 0;
			for (final ServiceInfo serviceInfo : serviceInfos) {
				final ServiceCategory serviceCategory = serviceInfo.getServiceCategory();
				if (!map.containsKey(serviceCategory.getId())) {
					map.put(serviceCategory.getId(), serviceCategory);
				}

				if (categoryIds.contains(serviceCategory.getId())) {
					expectedPrice += serviceInfo.getPrice();
				}
			}
			dto.setExpectedPrice(expectedPrice);
			dto.setServiceCategories(serviceCategoryConverter.convert(map.values()));

			return dto;
		});
	}

	@Override
	@Transactional
	public List<ServiceSupplierClientDto> getMe() throws IException {
		final User user = userRepository.findOneByUsername(securityUtil.getCurrentToken().getUsername());

		final List<ServiceSupplierClientDto> result = new ArrayList<>();
		if (user.getServiceSupplierClient() == null) {
			final ServiceSupplierClient createOne = createOne(user);

			result.add(getDomainObjectConverter().convert(createOne));

		} else {
			result.add(getDomainObjectConverter().convert(user.getServiceSupplierClient()));
		}
		return result;
	}

	private ServiceSupplierClient createOne(final User user) {
		final ServiceSupplierClient serviceSupplierClient = new ServiceSupplierClient();
		serviceSupplierClient.setStatus(ServiceSupplierClientStatus.INACTIVE);
		serviceSupplierClient.setType(PersonalType.BUSINESS);
		serviceSupplierClient.setUser(user);

		final Content identityCopy = new Content();
		identityCopy.setContent(new byte[0]);
		identityCopy.setStatus(RecordStatus.ACTIVE);
		identityCopy.setUuid(UUID.randomUUID().toString());
		contentRepository.save(identityCopy);

		serviceSupplierClient.setIdentityCopy(identityCopy.getUuid());

		final Content licenseCopy = new Content();
		licenseCopy.setContent(new byte[0]);
		licenseCopy.setStatus(RecordStatus.ACTIVE);
		licenseCopy.setUuid(UUID.randomUUID().toString());
		contentRepository.save(licenseCopy);

		serviceSupplierClient.setLicenseCopy(licenseCopy.getUuid());

		getDomainEntityRepository().save(serviceSupplierClient);
		return serviceSupplierClient;
	}
}
