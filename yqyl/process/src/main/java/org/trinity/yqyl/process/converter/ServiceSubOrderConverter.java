package org.trinity.yqyl.process.converter;

import org.springframework.stereotype.Component;
import org.trinity.yqyl.common.message.dto.domain.ServiceSubOrderDto;
import org.trinity.yqyl.repository.business.entity.ServiceSubOrder;

@Component
public class ServiceSubOrderConverter extends AbstractLookupSupportObjectConverter<ServiceSubOrder, ServiceSubOrderDto> {
@Override
protected void convertBackInternal(final ServiceSubOrderDto source, final ServiceSubOrder target, final CopyPolicy copyPolicy) {
}

@Override
protected void convertInternal(final ServiceSubOrder source, final ServiceSubOrderDto target, final CopyPolicy copyPolicy) {
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
