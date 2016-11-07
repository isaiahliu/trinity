package org.trinity.yqyl.process.controller;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.trinity.common.exception.IException;
import org.trinity.message.exception.GeneralErrorMessage;
import org.trinity.yqyl.common.message.dto.domain.ServiceOrderDto;
import org.trinity.yqyl.common.message.dto.domain.ServiceOrderSearchingDto;
import org.trinity.yqyl.common.message.exception.ErrorMessage;
import org.trinity.yqyl.common.message.lookup.OrderStatus;
import org.trinity.yqyl.common.message.lookup.PaymentMethod;
import org.trinity.yqyl.common.message.lookup.RecordStatus;
import org.trinity.yqyl.common.message.lookup.ServiceOrderRequirementStatus;
import org.trinity.yqyl.process.controller.base.AbstractAutowiredCrudProcessController;
import org.trinity.yqyl.process.controller.base.IServiceOrderProcessController;
import org.trinity.yqyl.repository.business.dataaccess.IContentRepository;
import org.trinity.yqyl.repository.business.dataaccess.IServiceInfoRepository;
import org.trinity.yqyl.repository.business.dataaccess.IServiceOrderRepository;
import org.trinity.yqyl.repository.business.dataaccess.IServiceOrderRequirementRepository;
import org.trinity.yqyl.repository.business.dataaccess.IServiceSupplierStaffRepository;
import org.trinity.yqyl.repository.business.dataaccess.IUserRepository;
import org.trinity.yqyl.repository.business.entity.Content;
import org.trinity.yqyl.repository.business.entity.ServiceInfo;
import org.trinity.yqyl.repository.business.entity.ServiceOrder;
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
    private IContentRepository contentRepository;

    @Autowired
    private IServiceOrderRequirementRepository serviceOrderRequirementRepository;

    @Autowired
    private IServiceSupplierStaffRepository serviceSupplierStaffRepository;

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
        serviceOrder.setPaymentMethod(serviceInfo.getPaymentMethod());
        serviceOrder.setPaymentType(serviceInfo.getPaymentType());
        serviceOrder.setServiceInfo(serviceInfo);

        getDomainEntityRepository().save(serviceOrder);

        return getDomainObjectConverter().convert(serviceOrder);
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

        contentRepository.save(content);
        getDomainEntityRepository().save(order);

        return order.getReceipt();
    }

    @Override
    protected boolean canAccessAllStatus() {
        return true;
    }

    @Override
    protected void updateRelationship(final ServiceOrder entity, final ServiceOrderDto dto) throws IException {
        if (dto.getStaff() != null && dto.getStaff().getId() > 0) {
            entity.setServiceSupplierStaff(serviceSupplierStaffRepository.findOne(dto.getStaff().getId()));
        }
    }
}
