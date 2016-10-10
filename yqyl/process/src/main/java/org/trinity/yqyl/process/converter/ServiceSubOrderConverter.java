package org.trinity.yqyl.process.converter;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Component;
import org.trinity.yqyl.common.message.dto.domain.ServiceSubOrderDto;
import org.trinity.yqyl.common.message.lookup.RecordStatus;
import org.trinity.yqyl.repository.business.entity.ServiceSubOrder;

@Component
public class ServiceSubOrderConverter extends AbstractLookupSupportObjectConverter<ServiceSubOrder, ServiceSubOrderDto> {
    @Override
    protected void convertBackInternal(final ServiceSubOrderDto source, final ServiceSubOrder target, final CopyPolicy copyPolicy) {
        copyObject(source::getId, target::getId, target::setId, copyPolicy);
        copyObject(source::getPrice, target::getPrice, target::setPrice, copyPolicy);
        copyObject(source::getAddress, target::getAddress, target::setAddress, copyPolicy);
        copyObject(source::getPhone, target::getPhone, target::setPhone, copyPolicy);
        copyLookup(source::getStatus, target::getStatus, target::setStatus, RecordStatus.class, copyPolicy);

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
    protected void convertInternal(final ServiceSubOrder source, final ServiceSubOrderDto target, final CopyPolicy copyPolicy) {
        copyObject(source::getId, target::getId, target::setId, copyPolicy);
        copyObject(source::getPrice, target::getPrice, target::setPrice, copyPolicy);
        copyObject(source::getAddress, target::getAddress, target::setAddress, copyPolicy);
        copyObject(source::getPhone, target::getPhone, target::setPhone, copyPolicy);
        copyMessage(source::getStatus, target::getStatus, target::setStatus, copyPolicy);

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
    protected ServiceSubOrder createFromInstance() {
        return new ServiceSubOrder();
    }

    @Override
    protected ServiceSubOrderDto createToInstance() {
        return new ServiceSubOrderDto();
    }
}
