package org.trinity.yqyl.process.converter;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Component;
import org.trinity.yqyl.common.message.dto.domain.ServiceOrderDto;
import org.trinity.yqyl.common.message.lookup.OrderStatus;
import org.trinity.yqyl.repository.business.entity.ServiceOrder;

@Component
public class ServiceOrderConverter extends AbstractLookupSupportObjectConverter<ServiceOrder, ServiceOrderDto> {
    @Override
    protected void convertBackInternal(final ServiceOrderDto source, final ServiceOrder target, final CopyPolicy copyPolicy) {
        copyObject(source::getId, target::getId, target::setId, copyPolicy);
        copyObject(source::getPrice, target::getPrice, target::setPrice, copyPolicy);
        copyObject(source::getProposalTime, target::getProposalTime, target::setProposalTime, copyPolicy);
        copyObject(source::getApprovalTime, target::getApprovalTime, target::setApprovalTime, copyPolicy);
        copyObject(source::getSettledTime, target::getSettledTime, target::setSettledTime, copyPolicy);
        copyLookup(source::getStatus, target::getStatus, target::setStatus, OrderStatus.class, copyPolicy);
        copyObject(source::getAddress, target::getAddress, target::setAddress, copyPolicy);
        copyObject(source::getPhone, target::getPhone, target::setPhone, copyPolicy);
        copyObject(() -> {
            final Date serviceDate = source.getServiceDate();
            if (serviceDate == null) {
                return null;
            }

            if (source.getServiceHour() != null) {
                final Calendar calendar = Calendar.getInstance();
                calendar.setTime(serviceDate);
                calendar.set(Calendar.HOUR_OF_DAY, source.getServiceHour());

                return calendar.getTime();
            }

            return serviceDate;
        }, target::getServiceTime, target::setServiceTime, copyPolicy);
    }

    @Override
    protected void convertInternal(final ServiceOrder source, final ServiceOrderDto target, final CopyPolicy copyPolicy) {
        copyObject(source::getId, target::getId, target::setId, copyPolicy);
        copyObject(source::getPrice, target::getPrice, target::setPrice, copyPolicy);
        copyObject(source::getProposalTime, target::getProposalTime, target::setProposalTime, copyPolicy);
        copyObject(source::getApprovalTime, target::getApprovalTime, target::setApprovalTime, copyPolicy);
        copyObject(source::getSettledTime, target::getSettledTime, target::setSettledTime, copyPolicy);
        copyMessage(source::getStatus, target::getStatus, target::setStatus, copyPolicy);
        copyObject(source::getAddress, target::getAddress, target::setAddress, copyPolicy);
        copyObject(source::getPhone, target::getPhone, target::setPhone, copyPolicy);

        copyObject(source::getServiceTime, target::getServiceDate, target::setServiceDate, copyPolicy);
        copyObject(() -> {
            final Date serviceTime = source.getServiceTime();
            if (serviceTime == null) {
                return null;
            }

            final Calendar calendar = Calendar.getInstance();
            calendar.setTime(serviceTime);
            return calendar.get(Calendar.HOUR_OF_DAY);
        }, target::getServiceHour, target::setServiceHour, copyPolicy);
    }

    @Override
    protected ServiceOrder createFromInstance() {
        return new ServiceOrder();
    }

    @Override
    protected ServiceOrderDto createToInstance() {
        return new ServiceOrderDto();
    }
}
