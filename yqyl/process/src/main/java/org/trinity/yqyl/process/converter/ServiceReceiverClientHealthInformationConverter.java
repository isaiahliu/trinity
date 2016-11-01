package org.trinity.yqyl.process.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.trinity.common.dto.object.LookupDto;
import org.trinity.common.dto.object.RelationshipExpression;
import org.trinity.message.ILookupMessage;
import org.trinity.process.converter.AbstractLookupSupportObjectConverter;
import org.trinity.process.converter.IObjectConverter;
import org.trinity.yqyl.common.message.dto.domain.ServiceReceiverClientHealthInformationDto;
import org.trinity.yqyl.repository.business.entity.ServiceReceiverClientHealthInformation;

@Component
public class ServiceReceiverClientHealthInformationConverter
        extends AbstractLookupSupportObjectConverter<ServiceReceiverClientHealthInformation, ServiceReceiverClientHealthInformationDto> {
    private static enum ServiceReceiverClientHealthInformationRelationship {
        NA
    }

    @Autowired
    public ServiceReceiverClientHealthInformationConverter(final IObjectConverter<ILookupMessage<?>, LookupDto> lookupConverter) {
        super(lookupConverter);
    }

    @Override
    protected void convertBackInternal(final ServiceReceiverClientHealthInformationDto source,
            final ServiceReceiverClientHealthInformation target, final CopyPolicy copyPolicy) {
        copyObject(source::getId, target::getServiceReceiverClientId, target::setServiceReceiverClientId, copyPolicy);
    }

    @Override
    protected void convertInternal(final ServiceReceiverClientHealthInformation source,
            final ServiceReceiverClientHealthInformationDto target, final CopyPolicy copyPolicy) {
        copyObject(source::getServiceReceiverClientId, target::getId, target::setId, copyPolicy);
    }

    @Override
    protected void convertRelationshipInternal(final ServiceReceiverClientHealthInformation source,
            final ServiceReceiverClientHealthInformationDto target, final RelationshipExpression relationshipExpression) {
        switch (relationshipExpression.getName(ServiceReceiverClientHealthInformationRelationship.class)) {
        default:
            break;
        }
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
