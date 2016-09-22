package org.trinity.yqyl.process.converter;

import org.springframework.stereotype.Component;
import org.trinity.yqyl.common.message.dto.domain.ServiceReceiverClientHealthIndicatorDto;
import org.trinity.yqyl.repository.business.entity.ServiceReceiverClientHealthIndicator;

@Component
public class ServiceReceiverClientHealthIndicatorConverter extends AbstractLookupSupportObjectConverter<ServiceReceiverClientHealthIndicator, ServiceReceiverClientHealthIndicatorDto> {
@Override
protected void convertBackInternal(final ServiceReceiverClientHealthIndicatorDto source, final ServiceReceiverClientHealthIndicator target, final CopyPolicy copyPolicy) {
}

@Override
protected void convertInternal(final ServiceReceiverClientHealthIndicator source, final ServiceReceiverClientHealthIndicatorDto target, final CopyPolicy copyPolicy) {
}

@Override
protected ServiceReceiverClientHealthIndicator createFromInstance() {
return new ServiceReceiverClientHealthIndicator();
}

@Override
protected ServiceReceiverClientHealthIndicatorDto createToInstance() {
return new ServiceReceiverClientHealthIndicatorDto();
}
}
