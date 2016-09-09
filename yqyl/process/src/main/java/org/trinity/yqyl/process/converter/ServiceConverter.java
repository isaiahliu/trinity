package org.trinity.yqyl.process.converter;

import org.springframework.stereotype.Component;
import org.trinity.yqyl.common.message.dto.domain.ServiceDto;
import org.trinity.yqyl.repository.business.entity.Service;

@Component
public class ServiceConverter extends AbstractLookupSupportObjectConverter<Service, ServiceDto> {
@Override
protected void convertBackInternal(final ServiceDto source, final Service target, final CopyPolicy copyPolicy) {
}

@Override
protected void convertInternal(final Service source, final ServiceDto target, final CopyPolicy copyPolicy) {
}

@Override
protected Service createFromInstance() {
return new Service();
}

@Override
protected ServiceDto createToInstance() {
return new ServiceDto();
}
}
