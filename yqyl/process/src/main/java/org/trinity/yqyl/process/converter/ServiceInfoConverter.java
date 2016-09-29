package org.trinity.yqyl.process.converter;

import org.springframework.stereotype.Component;
import org.trinity.yqyl.common.message.dto.domain.ServiceInfoDto;
import org.trinity.yqyl.common.message.lookup.ServiceStatus;
import org.trinity.yqyl.repository.business.entity.ServiceInfo;

@Component
public class ServiceInfoConverter extends AbstractLookupSupportObjectConverter<ServiceInfo, ServiceInfoDto> {
    @Override
    protected void convertBackInternal(final ServiceInfoDto source, final ServiceInfo target, final CopyPolicy copyPolicy) {
        copyObject(source::getId, target::getId, target::setId, copyPolicy);
        copyObject(source::getName, target::getName, target::setName, copyPolicy);
        copyObject(source::getDescription, target::getDescription, target::setDescription, copyPolicy);
        copyObject(source::getPrice, target::getPrice, target::setPrice, copyPolicy);
        copyLookup(source::getStatus, target::getStatus, target::setStatus, ServiceStatus.class, copyPolicy);
    }

    @Override
    protected void convertInternal(final ServiceInfo source, final ServiceInfoDto target, final CopyPolicy copyPolicy) {
        copyObject(source::getId, target::getId, target::setId, copyPolicy);
        copyObject(source::getName, target::getName, target::setName, copyPolicy);
        copyObject(source::getDescription, target::getDescription, target::setDescription, copyPolicy);
        copyObject(source::getPrice, target::getPrice, target::setPrice, copyPolicy);
        copyMessage(source::getStatus, target::getStatus, target::setStatus, copyPolicy);
    }

    @Override
    protected ServiceInfo createFromInstance() {
        return new ServiceInfo();
    }

    @Override
    protected ServiceInfoDto createToInstance() {
        return new ServiceInfoDto();
    }
}
