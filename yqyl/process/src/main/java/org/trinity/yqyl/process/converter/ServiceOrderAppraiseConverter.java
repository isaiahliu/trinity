package org.trinity.yqyl.process.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.trinity.common.dto.object.LookupDto;
import org.trinity.message.ILookupMessage;
import org.trinity.process.converter.AbstractLookupSupportObjectConverter;
import org.trinity.process.converter.IObjectConverter;
import org.trinity.yqyl.common.message.dto.domain.ServiceOrderAppraiseDto;
import org.trinity.yqyl.common.message.lookup.RecordStatus;
import org.trinity.yqyl.repository.business.entity.ServiceOrderAppraise;

@Component
public class ServiceOrderAppraiseConverter extends AbstractLookupSupportObjectConverter<ServiceOrderAppraise, ServiceOrderAppraiseDto> {
    @Autowired
    public ServiceOrderAppraiseConverter(final IObjectConverter<ILookupMessage<?>, LookupDto> lookupConverter) {
        super(lookupConverter);
    }

    @Override
    protected void convertBackInternal(final ServiceOrderAppraiseDto source, final ServiceOrderAppraise target,
            final CopyPolicy copyPolicy) {
        copyObject(source::getId, target::getServiceOrderId, target::setServiceOrderId, copyPolicy);
        copyObject(source::getAttitudeRate, target::getAttitudeRate, target::setAttitudeRate, copyPolicy);
        copyObject(source::getComment, target::getComment, target::setComment, copyPolicy);
        copyObject(source::getOnTimeRate, target::getOnTimeRate, target::setOnTimeRate, copyPolicy);
        copyObject(source::getQualityRate, target::getQualityRate, target::setQualityRate, copyPolicy);
        copyObject(source::getStaffRate, target::getStaffRate, target::setStaffRate, copyPolicy);

        copyLookup(source::getStatus, target::getStatus, target::setStatus, RecordStatus.class, copyPolicy);
    }

    @Override
    protected void convertInternal(final ServiceOrderAppraise source, final ServiceOrderAppraiseDto target, final CopyPolicy copyPolicy) {
        copyObject(source::getServiceOrderId, target::getId, target::setId, copyPolicy);
        copyObject(source::getAttitudeRate, target::getAttitudeRate, target::setAttitudeRate, copyPolicy);
        copyObject(source::getComment, target::getComment, target::setComment, copyPolicy);
        copyObject(source::getOnTimeRate, target::getOnTimeRate, target::setOnTimeRate, copyPolicy);
        copyObject(source::getQualityRate, target::getQualityRate, target::setQualityRate, copyPolicy);
        copyObject(source::getStaffRate, target::getStaffRate, target::setStaffRate, copyPolicy);

        copyMessage(source::getStatus, target::getStatus, target::setStatus, copyPolicy);
    }

    @Override
    protected ServiceOrderAppraise createFromInstance() {
        return new ServiceOrderAppraise();
    }

    @Override
    protected ServiceOrderAppraiseDto createToInstance() {
        return new ServiceOrderAppraiseDto();
    }
}
