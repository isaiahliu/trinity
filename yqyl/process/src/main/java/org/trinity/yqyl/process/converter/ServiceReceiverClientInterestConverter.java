package org.trinity.yqyl.process.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.trinity.common.dto.object.LookupDto;
import org.trinity.message.ILookupMessage;
import org.trinity.process.converter.AbstractLookupSupportObjectConverter;
import org.trinity.process.converter.IObjectConverter;
import org.trinity.yqyl.common.message.dto.domain.ServiceReceiverClientInterestDto;
import org.trinity.yqyl.repository.business.entity.ServiceReceiverClientInterest;

@Component
public class ServiceReceiverClientInterestConverter
        extends AbstractLookupSupportObjectConverter<ServiceReceiverClientInterest, ServiceReceiverClientInterestDto> {
    @Autowired
    public ServiceReceiverClientInterestConverter(final IObjectConverter<ILookupMessage<?>, LookupDto> lookupConverter) {
        super(lookupConverter);
    }

    @Override
    protected void convertBackInternal(final ServiceReceiverClientInterestDto source, final ServiceReceiverClientInterest target,
            final CopyPolicy copyPolicy) {
    }

    @Override
    protected void convertInternal(final ServiceReceiverClientInterest source, final ServiceReceiverClientInterestDto target,
            final CopyPolicy copyPolicy) {
    }

    @Override
    protected ServiceReceiverClientInterest createFromInstance() {
        return new ServiceReceiverClientInterest();
    }

    @Override
    protected ServiceReceiverClientInterestDto createToInstance() {
        return new ServiceReceiverClientInterestDto();
    }
}
