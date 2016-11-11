package org.trinity.yqyl.process.controller;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.trinity.common.accessright.ISecurityUtil.CheckMode;
import org.trinity.common.exception.IException;
import org.trinity.message.exception.GeneralErrorMessage;
import org.trinity.process.converter.IObjectConverter;
import org.trinity.yqyl.common.message.dto.domain.ServiceOrderDto;
import org.trinity.yqyl.common.message.dto.domain.ServiceOrderOperationDto;
import org.trinity.yqyl.common.message.dto.domain.ServiceOrderSearchingDto;
import org.trinity.yqyl.common.message.exception.ErrorMessage;
import org.trinity.yqyl.common.message.lookup.AccessRight;
import org.trinity.yqyl.common.message.lookup.OrderOperation;
import org.trinity.yqyl.common.message.lookup.OrderStatus;
import org.trinity.yqyl.common.message.lookup.PaymentMethod;
import org.trinity.yqyl.common.message.lookup.RecordStatus;
import org.trinity.yqyl.common.message.lookup.ServiceOrderRequirementStatus;
import org.trinity.yqyl.process.controller.base.AbstractAutowiredCrudProcessController;
import org.trinity.yqyl.process.controller.base.IServiceOrderProcessController;
import org.trinity.yqyl.repository.business.dataaccess.IContentRepository;
import org.trinity.yqyl.repository.business.dataaccess.IServiceInfoRepository;
import org.trinity.yqyl.repository.business.dataaccess.IServiceOrderOperationRepository;
import org.trinity.yqyl.repository.business.dataaccess.IServiceOrderRepository;
import org.trinity.yqyl.repository.business.dataaccess.IServiceOrderRequirementRepository;
import org.trinity.yqyl.repository.business.dataaccess.IServiceSupplierStaffRepository;
import org.trinity.yqyl.repository.business.dataaccess.IUserRepository;
import org.trinity.yqyl.repository.business.entity.Content;
import org.trinity.yqyl.repository.business.entity.ServiceInfo;
import org.trinity.yqyl.repository.business.entity.ServiceOrder;
import org.trinity.yqyl.repository.business.entity.ServiceOrderOperation;
import org.trinity.yqyl.repository.business.entity.ServiceOrderRequirement;
import org.trinity.yqyl.repository.business.entity.User;

@Service
public class ServiceOrderProcessController
        extends AbstractAutowiredCrudProcessController<ServiceOrder, ServiceOrderDto, ServiceOrderSearchingDto, IServiceOrderRepository>
        implements IServiceOrderProcessController {

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IServiceInfoRepository serviceInfoRepository;

    @Autowired
    private IObjectConverter<ServiceOrderOperation, ServiceOrderOperationDto> serviceOrderOperationConverter;

    @Autowired
    private IContentRepository contentRepository;

    @Autowired
    private IServiceOrderRequirementRepository serviceOrderRequirementRepository;

    @Autowired
    private IServiceSupplierStaffRepository serviceSupplierStaffRepository;

    @Autowired
    private IServiceOrderOperationRepository serviceOrderOperationRepository;

    @Override
    public List<ServiceOrderDto> cancelOrder(final List<ServiceOrderDto> data) {
        final List<ServiceOrder> entities = data.stream().map(item -> {
            final ServiceOrder entity = getDomainEntityRepository().findOne(item.getId());

            String username = null;
            try {
                username = getSecurityUtil().getCurrentToken().getUsername();
            } catch (final IException e1) {
                return entity;
            }

            final boolean isSupplier = entity.getServiceInfo().getServiceSupplierClient().getUser().getUsername().equals(username);
            final boolean isReceiver = entity.getUser().getUsername().equals(username);
            final boolean isAdmin = getSecurityUtil().hasAccessRight(CheckMode.ANY, AccessRight.ADMINISTRATOR);

            if (!isSupplier && !isReceiver && !isAdmin) {
                return entity;
            }

            boolean statusValidationPassed = true;
            final ServiceOrderOperation serviceOrderOperation = new ServiceOrderOperation();

            switch (entity.getStatus()) {
            case UNPROCESSED:
                if (isReceiver) {
                    entity.setStatus(OrderStatus.CANCELLED);
                    serviceOrderOperation.setOperation(OrderOperation.RECEIVER_CANCELLED);
                    serviceOrderOperation.setOrderStatus(OrderStatus.CANCELLED);
                    serviceOrderOperation.setParams(item.getOperations().get(0).getParams().get(0));
                } else {
                    statusValidationPassed = false;
                }
                break;
            case IN_PROGRESS:
                if (isReceiver) {
                    entity.setStatus(OrderStatus.CANCEL_REQUESTED);
                    serviceOrderOperation.setOperation(OrderOperation.CANCEL_REQUEST);
                    serviceOrderOperation.setOrderStatus(OrderStatus.CANCEL_REQUESTED);
                    serviceOrderOperation.setParams(item.getOperations().get(0).getParams().get(0));
                } else {
                    statusValidationPassed = false;
                }
                break;
            case CANCEL_REQUESTED:
                if (isSupplier) {
                    entity.setStatus(OrderStatus.CANCELLED);
                    serviceOrderOperation.setOperation(OrderOperation.SUPPLIER_CANCELLED);
                    serviceOrderOperation.setOrderStatus(OrderStatus.CANCELLED);
                } else {
                    statusValidationPassed = false;
                }
                break;
            default:
                statusValidationPassed = false;
            }

            if (!statusValidationPassed) {
                if (isAdmin) {
                    entity.setStatus(OrderStatus.CANCELLED);
                    serviceOrderOperation.setOperation(OrderOperation.ADMIN_CANCELLED);
                    serviceOrderOperation.setOrderStatus(OrderStatus.CANCELLED);
                } else {
                    return entity;
                }
            }

            try {
                serviceOrderOperation.setOperator(getSecurityUtil().getCurrentToken().getUsername());
            } catch (final IException e) {
            }

            serviceOrderOperation.setServiceOrder(entity);
            serviceOrderOperation.setStatus(RecordStatus.ACTIVE);
            serviceOrderOperation.setTimestamp(new Date());

            serviceOrderOperationRepository.save(serviceOrderOperation);

            return entity;
        }).collect(Collectors.toList());

        return getDomainObjectConverter().convert(getDomainEntityRepository().save(entities));
    }

    @Override
    public List<ServiceOrderDto> changePrice(final List<ServiceOrderDto> data) throws IException {
        final List<ServiceOrder> entities = data.stream().map(item -> {
            final ServiceOrder entity = getDomainEntityRepository().findOne(item.getId());
            boolean isSupplier = false;
            try {
                isSupplier = entity.getServiceInfo().getServiceSupplierClient().getUser().getUsername()
                        .equals(getSecurityUtil().getCurrentToken().getUsername());
            } catch (final IException e1) {
                return entity;
            }
            if (!isSupplier || entity.getStatus() != OrderStatus.IN_PROGRESS && entity.getStatus() != OrderStatus.UNPROCESSED) {
                if (!getSecurityUtil().hasAccessRight(CheckMode.ANY, AccessRight.SUPER_USER)) {
                    return entity;
                }
            }
            final String[] params = new String[] { entity.getExpectedPaymentAmount().toString(),
                    item.getExpectedPaymentAmount().toString() };

            entity.setExpectedPaymentAmount(item.getExpectedPaymentAmount());

            final ServiceOrderOperation serviceOrderOperation = new ServiceOrderOperation();
            serviceOrderOperation.setOperation(OrderOperation.CHANGE_PRICE);
            serviceOrderOperation.setParams(String.join(",", params));
            try {
                serviceOrderOperation.setOperator(getSecurityUtil().getCurrentToken().getUsername());
            } catch (final IException e) {
            }
            serviceOrderOperation.setOrderStatus(entity.getStatus());
            serviceOrderOperation.setServiceOrder(entity);
            serviceOrderOperation.setStatus(RecordStatus.ACTIVE);
            serviceOrderOperation.setTimestamp(new Date());

            serviceOrderOperationRepository.save(serviceOrderOperation);

            return entity;
        }).collect(Collectors.toList());

        return getDomainObjectConverter().convert(getDomainEntityRepository().save(entities));
    }

    @Override
    @Transactional
    public ServiceOrderDto proposeOrder(final ServiceOrderDto serviceOrderDto) throws IException {
        final User user = userRepository.findOneByUsername(getSecurityUtil().getCurrentToken().getUsername());

        final ServiceOrder serviceOrder = getDomainObjectConverter().convertBack(serviceOrderDto);
        serviceOrder.setId(null);
        serviceOrder.setPrice(0d);
        serviceOrder.setProposalTime(new Date());
        serviceOrder.setStatus(OrderStatus.UNPROCESSED);
        serviceOrder.setUser(user);

        final ServiceInfo serviceInfo = serviceInfoRepository.findOne(serviceOrderDto.getServiceInfo().getId());

        serviceOrder.setPrice(serviceInfo.getPrice());
        serviceOrder.setExpectedPaymentAmount(serviceInfo.getPrice());
        serviceOrder.setActualPaymentAmount(0d);
        serviceOrder.setPaymentMethod(serviceInfo.getPaymentMethod());
        serviceOrder.setPaymentType(serviceInfo.getPaymentType());
        serviceOrder.setServiceInfo(serviceInfo);

        getDomainEntityRepository().save(serviceOrder);

        final ServiceOrderOperation operation = new ServiceOrderOperation();
        operation.setOperation(OrderOperation.PROPOSAL);
        operation.setOperator(getSecurityUtil().getCurrentToken().getUsername());
        operation.setOrderStatus(OrderStatus.UNPROCESSED);
        operation.setStatus(RecordStatus.ACTIVE);
        operation.setServiceOrder(serviceOrder);
        operation.setTimestamp(new Date());

        serviceOrderOperationRepository.save(operation);

        return getDomainObjectConverter().convert(serviceOrder);
    }

    @Override
    @Transactional
    public List<ServiceOrderDto> rejectCancelOrder(final List<ServiceOrderDto> data) throws IException {
        final List<ServiceOrder> entities = data.stream().map(item -> {
            final ServiceOrder entity = getDomainEntityRepository().findOne(item.getId());

            String username = null;
            try {
                username = getSecurityUtil().getCurrentToken().getUsername();
            } catch (final IException e1) {
                return entity;
            }

            final boolean isSupplier = entity.getServiceInfo().getServiceSupplierClient().getUser().getUsername().equals(username);
            final boolean isAdmin = getSecurityUtil().hasAccessRight(CheckMode.ANY, AccessRight.ADMINISTRATOR);

            if (!isSupplier && !isAdmin) {
                return entity;
            }

            final ServiceOrderOperation serviceOrderOperation = new ServiceOrderOperation();

            switch (entity.getStatus()) {
            case CANCEL_REQUESTED:
                entity.setStatus(OrderStatus.IN_PROGRESS);
                serviceOrderOperation.setOperation(OrderOperation.CANCEL_REJECTED);
                serviceOrderOperation.setOrderStatus(OrderStatus.IN_PROGRESS);
                break;
            default:
                return entity;
            }

            try {
                serviceOrderOperation.setOperator(getSecurityUtil().getCurrentToken().getUsername());
            } catch (final IException e) {
            }

            serviceOrderOperation.setServiceOrder(entity);
            serviceOrderOperation.setStatus(RecordStatus.ACTIVE);
            serviceOrderOperation.setTimestamp(new Date());

            serviceOrderOperationRepository.save(serviceOrderOperation);

            return entity;
        }).collect(Collectors.toList());

        return getDomainObjectConverter().convert(getDomainEntityRepository().save(entities));
    }

    @Override
    @Transactional
    public void releaseOrder(final List<ServiceOrderDto> data) throws IException {
        for (final ServiceOrderDto serviceOrderDto : data) {
            final ServiceOrder serviceOrder = getDomainEntityRepository().findOne(serviceOrderDto.getId());

            if (serviceOrder.getStatus() != OrderStatus.REQUEST_GRABBED) {
                throw getExceptionFactory().createException(ErrorMessage.INCORRECT_SERVICE_ORDER_STATUS);
            }
            final ServiceOrderRequirement serviceOrderRequirement = serviceOrder.getServiceOrderRequirement();

            serviceOrder.setStatus(OrderStatus.REQUEST_FAILED);
            if (serviceOrderRequirement != null) {
                serviceOrderRequirement.setStatus(ServiceOrderRequirementStatus.ACTIVE);

                serviceOrderRequirementRepository.save(serviceOrderRequirement);
            }

            serviceOrder.setServiceOrderRequirement(null);

            getDomainEntityRepository().save(serviceOrder);
        }
    }

    @Override
    @Transactional
    public List<ServiceOrderDto> sendTxCode(final List<ServiceOrderDto> data) throws IException {
        final List<ServiceOrder> entities = data.stream().map(item -> {
            final ServiceOrder entity = getDomainEntityRepository().findOne(item.getId());

            if (entity.getStatus() != OrderStatus.IN_PROGRESS) {
                if (!getSecurityUtil().hasAccessRight(CheckMode.ANY, AccessRight.SUPER_USER)) {
                    return entity;
                }
            } else {
                entity.setStatus(OrderStatus.AWAITING_APPRAISE);
            }

            // TODO Pick up tx amount by txCode from POS API.
            // final PaymentMethod paymentMethod = item.getPaymentMethod();
            // final String txCode = item.getTransactionCode();
            final double amount = 20d;

            entity.setExpectedPaymentAmount(amount);
            entity.setActualPaymentAmount(amount);

            final ServiceOrderOperation serviceOrderOperation = new ServiceOrderOperation();
            serviceOrderOperation.setOperation(OrderOperation.PAYED);
            serviceOrderOperation.setParams(item.getTransaction().getCode());
            try {
                serviceOrderOperation.setOperator(getSecurityUtil().getCurrentToken().getUsername());
            } catch (final IException e) {
            }
            serviceOrderOperation.setOrderStatus(OrderStatus.AWAITING_APPRAISE);
            serviceOrderOperation.setServiceOrder(entity);
            serviceOrderOperation.setStatus(RecordStatus.ACTIVE);
            serviceOrderOperation.setTimestamp(new Date());

            serviceOrderOperationRepository.save(serviceOrderOperation);

            return entity;
        }).collect(Collectors.toList());

        return getDomainObjectConverter().convert(getDomainEntityRepository().save(entities));
    }

    @Override
    @Transactional
    public String uploadReceipt(final ServiceOrderDto serviceOrderDto) throws IException {
        final ServiceOrder order = getDomainEntityRepository().findOne(serviceOrderDto.getId());
        if (order == null) {
            throw getExceptionFactory().createException(GeneralErrorMessage.UNABLE_TO_FIND_INSTANCE);
        }

        Content content;
        if (StringUtils.isEmpty(order.getReceipt())) {
            content = new Content();
            content.setUuid(UUID.randomUUID().toString());
            content.setStatus(RecordStatus.ACTIVE);
            order.setReceipt(content.getUuid());
        } else {
            content = contentRepository.findOneByUuid(order.getReceipt());
        }

        if (order.getServiceInfo().getPaymentMethod() == PaymentMethod.POS) {
            order.setStatus(OrderStatus.AWAITING_APPRAISE);
        }

        content.setContent(serviceOrderDto.getReceiptContent());

        final ServiceOrderOperation serviceOrderOperation = new ServiceOrderOperation();
        serviceOrderOperation.setOperation(OrderOperation.RECEIPT_UPLOADED);
        serviceOrderOperation.setOperator(getSecurityUtil().getCurrentToken().getUsername());
        serviceOrderOperation.setOrderStatus(OrderStatus.AWAITING_APPRAISE);
        serviceOrderOperation.setServiceOrder(order);
        serviceOrderOperation.setStatus(RecordStatus.ACTIVE);
        serviceOrderOperation.setTimestamp(new Date());

        contentRepository.save(content);
        getDomainEntityRepository().save(order);
        serviceOrderOperationRepository.save(serviceOrderOperation);

        return order.getReceipt();
    }

    @Override
    protected void addRelatedTables(final ServiceOrder entity, final ServiceOrderDto dto) throws IException {
        super.addRelatedTables(entity, dto);
    }

    @Override
    protected void addRelationshipFields(final ServiceOrder entity, final ServiceOrderDto dto) throws IException {
        super.addRelationshipFields(entity, dto);
    }

    @Override
    protected boolean canAccessAllStatus() {
        return true;
    }

    @Override
    protected void updateRelatedTables(final ServiceOrder entity, final ServiceOrderDto dto) throws IException {
        super.updateRelatedTables(entity, dto);

        if (!dto.getOperations().isEmpty()) {
            serviceOrderOperationRepository.save(dto.getOperations().stream().map(item -> {
                final ServiceOrderOperation serviceOrderOperation = serviceOrderOperationConverter.convertBack(item);
                serviceOrderOperation.setServiceOrder(entity);
                serviceOrderOperation.setStatus(RecordStatus.ACTIVE);
                serviceOrderOperation.setTimestamp(new Date());
                serviceOrderOperation.setOrderStatus(entity.getStatus());
                try {
                    serviceOrderOperation.setOperator(getSecurityUtil().getCurrentToken().getUsername());
                } catch (final IException e) {
                }

                return serviceOrderOperation;
            }).collect(Collectors.toList()));

        }
    }

    @Override
    protected void updateRelationshipFields(final ServiceOrder entity, final ServiceOrderDto dto) throws IException {
        if (dto.getStaff() != null && dto.getStaff().getId() > 0) {
            entity.setServiceSupplierStaff(serviceSupplierStaffRepository.findOne(dto.getStaff().getId()));
        }
    }

}
