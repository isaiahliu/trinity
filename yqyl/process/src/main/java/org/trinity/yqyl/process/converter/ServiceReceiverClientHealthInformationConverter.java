package org.trinity.yqyl.process.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.trinity.common.dto.object.LookupDto;
import org.trinity.message.ILookupMessage;
import org.trinity.process.converter.AbstractLookupSupportObjectConverter;
import org.trinity.process.converter.IObjectConverter;
import org.trinity.yqyl.common.message.dto.domain.ServiceReceiverClientHealthInformationDto;
import org.trinity.yqyl.repository.business.entity.ServiceReceiverClientHealthInformation;

@Component
public class ServiceReceiverClientHealthInformationConverter
        extends AbstractLookupSupportObjectConverter<ServiceReceiverClientHealthInformation, ServiceReceiverClientHealthInformationDto> {
    @Autowired
    public ServiceReceiverClientHealthInformationConverter(final IObjectConverter<ILookupMessage<?>, LookupDto> lookupConverter) {
        super(lookupConverter);
    }

    @Override
    protected void convertBackInternal(final ServiceReceiverClientHealthInformationDto source,
            final ServiceReceiverClientHealthInformation target, final CopyPolicy copyPolicy) {
    }

    @Override
    protected void convertInternal(final ServiceReceiverClientHealthInformation source,
            final ServiceReceiverClientHealthInformationDto target, final CopyPolicy copyPolicy) {
    }

    @Override
    protected ServiceReceiverClientHealthInformation createFromInstance() {
        return new ServiceReceiverClientHealthInformation();
    }

    @Override
    protected ServiceReceiverClientHealthInformationDto createToInstance() {
        return new ServiceReceiverClientHealthInformationDto();
    }
}
