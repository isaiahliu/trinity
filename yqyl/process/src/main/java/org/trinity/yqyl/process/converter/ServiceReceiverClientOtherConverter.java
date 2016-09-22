package org.trinity.yqyl.process.converter;

import org.springframework.stereotype.Component;
import org.trinity.yqyl.common.message.dto.domain.ServiceReceiverClientOtherDto;
import org.trinity.yqyl.repository.business.entity.ServiceReceiverClientOther;

@Component
public class ServiceReceiverClientOtherConverter extends AbstractLookupSupportObjectConverter<ServiceReceiverClientOther, ServiceReceiverClientOtherDto> {
@Override
protected void convertBackInternal(final ServiceReceiverClientOtherDto source, final ServiceReceiverClientOther target, final CopyPolicy copyPolicy) {
}

@Override
protected void convertInternal(final ServiceReceiverClientOther source, final ServiceReceiverClientOtherDto target, final CopyPolicy copyPolicy) {
}

@Override
protected ServiceReceiverClientOther createFromInstance() {
return new ServiceReceiverClientOther();
}

@Override
protected ServiceReceiverClientOtherDto createToInstance() {
return new ServiceReceiverClientOtherDto();
}
}
