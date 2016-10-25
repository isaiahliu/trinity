package org.trinity.yqyl.process.converter;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.trinity.common.dto.object.LookupDto;
import org.trinity.message.ILookupMessage;
import org.trinity.process.converter.AbstractLookupSupportObjectConverter;
import org.trinity.process.converter.IObjectConverter;
import org.trinity.yqyl.common.message.dto.domain.ServiceOrderRequirementDto;
import org.trinity.yqyl.common.message.lookup.FlagStatus;
import org.trinity.yqyl.common.message.lookup.ServiceOrderRequirementStatus;
import org.trinity.yqyl.repository.business.entity.ServiceOrderRequirement;

@Component
public class ServiceOrderRequirementConverter
        extends AbstractLookupSupportObjectConverter<ServiceOrderRequirement, ServiceOrderRequirementDto> {
    @Autowired
    public ServiceOrderRequirementConverter(final IObjectConverter<ILookupMessage<?>, LookupDto> lookupConverter) {
        super(lookupConverter);
    }

    @Override
    protected void convertBackInternal(final ServiceOrderRequirementDto source, final ServiceOrderRequirement target,
            final CopyPolicy copyPolicy) {
        copyObject(source::getId, target::getId, target::setId, copyPolicy);
        copyObject(source::getPrice, target::getPrice, target::setPrice, copyPolicy);
        copyObject(source::getAddress, target::getAddress, target::setAddress, copyPolicy);
        copyObject(source::getPhone, target::getPhone, target::setPhone, copyPolicy);
        copyObject(source::getComment, target::getComment, target::setComment, copyPolicy);
        copyLookup(source::getStatus, target::getStatus, target::setStatus, ServiceOrderRequirementStatus.class, copyPolicy);
        copyLookup(source::getAnnounceStatus, target::getAnnounceStatus, target::setAnnounceStatus, FlagStatus.class, copyPolicy);
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
    protected void convertInternal(final ServiceOrderRequirement source, final ServiceOrderRequirementDto target,
            final CopyPolicy copyPolicy) {
        copyObject(source::getId, target::getId, target::setId, copyPolicy);
        copyObject(source::getPrice, target::getPrice, target::setPrice, copyPolicy);
        copyObject(source::getAddress, target::getAddress, target::setAddress, copyPolicy);
        copyObject(source::getPhone, target::getPhone, target::setPhone, copyPolicy);
        copyObject(source::getComment, target::getComment, target::setComment, copyPolicy);
        copyMessage(source::getStatus, target::getStatus, target::setStatus, copyPolicy);
        copyMessage(source::getAnnounceStatus, target::getAnnounceStatus, target::setAnnounceStatus, copyPolicy);

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
    protected ServiceOrderRequirement createFromInstance() {
        return new ServiceOrderRequirement();
    }

    @Override
    protected ServiceOrderRequirementDto createToInstance() {
        return new ServiceOrderRequirementDto();
    }
}
