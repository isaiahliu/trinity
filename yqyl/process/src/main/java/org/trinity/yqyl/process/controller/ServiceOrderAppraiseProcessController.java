package org.trinity.yqyl.process.controller;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.trinity.common.accessright.ISecurityUtil.CheckMode;
import org.trinity.common.exception.IException;
import org.trinity.yqyl.common.message.dto.domain.ServiceOrderAppraiseDto;
import org.trinity.yqyl.common.message.dto.domain.ServiceOrderAppraiseSearchingDto;
import org.trinity.yqyl.common.message.exception.ErrorMessage;
import org.trinity.yqyl.common.message.lookup.AccessRight;
import org.trinity.yqyl.common.message.lookup.OrderOperation;
import org.trinity.yqyl.common.message.lookup.OrderStatus;
import org.trinity.yqyl.common.message.lookup.RecordStatus;
import org.trinity.yqyl.process.controller.base.AbstractAutowiredCrudProcessController;
import org.trinity.yqyl.process.controller.base.IServiceOrderAppraiseProcessController;
import org.trinity.yqyl.repository.business.dataaccess.IServiceOrderAppraiseRepository;
import org.trinity.yqyl.repository.business.dataaccess.IServiceOrderOperationRepository;
import org.trinity.yqyl.repository.business.dataaccess.IServiceOrderRepository;
import org.trinity.yqyl.repository.business.entity.ServiceOrder;
import org.trinity.yqyl.repository.business.entity.ServiceOrderAppraise;
import org.trinity.yqyl.repository.business.entity.ServiceOrderOperation;

@Service
public class ServiceOrderAppraiseProcessController extends
		AbstractAutowiredCrudProcessController<ServiceOrderAppraise, ServiceOrderAppraiseDto, ServiceOrderAppraiseSearchingDto, IServiceOrderAppraiseRepository>
		implements IServiceOrderAppraiseProcessController {
	@Autowired
	private IServiceOrderRepository serviceOrderRepository;

	@Autowired
	private IServiceOrderOperationRepository serviceOrderOperationRepository;

	@Override
	@Transactional(rollbackOn = IException.class)
	public List<ServiceOrderAppraiseDto> addAll(final List<ServiceOrderAppraiseDto> data) throws IException {
		for (final ServiceOrderAppraiseDto dto : data) {
			final ServiceOrder serviceOrder = serviceOrderRepository.findOne(dto.getId());

			if (!serviceOrder.getUser().getUsername().equals(getSecurityUtil().getCurrentToken().getUsername())) {
				throw getExceptionFactory().createException(ErrorMessage.INSUFFICIENT_ACCESSRIGHT);
			}

			if (serviceOrder.getStatus() != OrderStatus.AWAITING_APPRAISE) {
				throw getExceptionFactory().createException(ErrorMessage.INCORRECT_SERVICE_ORDER_STATUS);
			}

			final ServiceOrderAppraise serviceOrderAppraise = getDomainObjectConverter().convertBack(dto);
			serviceOrderAppraise.setStatus(RecordStatus.ACTIVE);

			serviceOrder.setStatus(OrderStatus.SETTLED);

			ServiceOrderOperation operation = new ServiceOrderOperation();
			operation.setOperation(OrderOperation.APPRAISED);
			operation.setOperator(getSecurityUtil().getCurrentToken().getUsername());
			operation.setOrderStatus(OrderStatus.SETTLED);
			operation.setStatus(RecordStatus.ACTIVE);
			operation.setServiceOrder(serviceOrder);
			operation.setTimestamp(new Date());

			serviceOrderOperationRepository.save(operation);

			operation = new ServiceOrderOperation();
			operation.setOperation(OrderOperation.SETTLED);
			operation.setOperator(getSecurityUtil().getCurrentToken().getUsername());
			operation.setOrderStatus(OrderStatus.SETTLED);
			operation.setStatus(RecordStatus.ACTIVE);
			operation.setServiceOrder(serviceOrder);
			operation.setTimestamp(new Date());

			serviceOrderOperationRepository.save(operation);

			getDomainEntityRepository().save(serviceOrderAppraise);
			serviceOrderRepository.save(serviceOrder);
		}

		return data;
	}

	@Override
	public void reply(final List<ServiceOrderAppraiseDto> data) throws IException {
		final List<ServiceOrderAppraise> entities = data.stream().map(item -> {
			final ServiceOrderAppraise entity = getDomainEntityRepository().findOne(item.getId());

			String username = null;
			try {
				username = getSecurityUtil().getCurrentToken().getUsername();
			} catch (final IException e1) {
				return entity;
			}

			final boolean isSupplier = entity.getServiceOrder().getServiceInfo().getServiceSupplierClient().getUser().getUsername()
					.equals(username);
			final boolean isAdmin = getSecurityUtil().hasAccessRight(CheckMode.ANY, AccessRight.ADMINISTRATOR);

			if (!isSupplier && !isAdmin) {
				return entity;
			}
			entity.setReply(item.getReply());

			final ServiceOrderOperation serviceOrderOperation = new ServiceOrderOperation();

			try {
				serviceOrderOperation.setOperator(getSecurityUtil().getCurrentToken().getUsername());
			} catch (final IException e) {
			}

			serviceOrderOperation.setOperation(OrderOperation.REPLYED);
			serviceOrderOperation.setOrderStatus(OrderStatus.SETTLED);
			serviceOrderOperation.setServiceOrder(entity.getServiceOrder());
			serviceOrderOperation.setStatus(RecordStatus.ACTIVE);
			serviceOrderOperation.setTimestamp(new Date());

			serviceOrderOperationRepository.save(serviceOrderOperation);

			return entity;
		}).collect(Collectors.toList());

		getDomainEntityRepository().save(entities);
	}

	@Override
	protected boolean canAccessAllStatus() {
		return getSecurityUtil().hasAccessRight(CheckMode.ANY, AccessRight.ADMINISTRATOR, AccessRight.SERVICE_SUPPLIER);
	}
}
