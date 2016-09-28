package org.trinity.yqyl.process.converter;

import org.springframework.stereotype.Component;
import org.trinity.yqyl.common.message.dto.domain.ServiceOrderDto;
import org.trinity.yqyl.repository.business.entity.ServiceOrder;

@Component
public class ServiceOrderConverter extends AbstractLookupSupportObjectConverter<ServiceOrder, ServiceOrderDto> {
@Override
protected void convertBackInternal(final ServiceOrderDto source, final ServiceOrder target, final CopyPolicy copyPolicy) {
}

@Override
protected void convertInternal(final ServiceOrder source, final ServiceOrderDto target, final CopyPolicy copyPolicy) {
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
