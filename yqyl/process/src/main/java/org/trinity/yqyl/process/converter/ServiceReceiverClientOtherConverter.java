package org.trinity.yqyl.process.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.trinity.common.dto.object.LookupDto;
import org.trinity.common.dto.object.RelationshipExpression;
import org.trinity.message.ILookupMessage;
import org.trinity.process.converter.AbstractLookupSupportObjectConverter;
import org.trinity.process.converter.IObjectConverter;
import org.trinity.yqyl.common.message.dto.domain.ServiceReceiverClientOtherDto;
import org.trinity.yqyl.repository.business.entity.ServiceReceiverClientOther;

@Component
public class ServiceReceiverClientOtherConverter
        extends AbstractLookupSupportObjectConverter<ServiceReceiverClientOther, ServiceReceiverClientOtherDto> {
    private static enum ServiceReceiverClientOtherRelationship {
    }

    @Autowired
    public ServiceReceiverClientOtherConverter(final IObjectConverter<ILookupMessage<?>, LookupDto> lookupConverter) {
        super(lookupConverter);
    }

    @Override
    protected void convertBackInternal(final ServiceReceiverClientOtherDto source, final ServiceReceiverClientOther target,
            final CopyPolicy copyPolicy) {
        copyObject(source::getId, target::getServiceReceiverClientId, target::setServiceReceiverClientId, copyPolicy);
    }

    @Override
    protected void convertInternal(final ServiceReceiverClientOther source, final ServiceReceiverClientOtherDto target,
            final CopyPolicy copyPolicy) {
        copyObject(source::getServiceReceiverClientId, target::getId, target::setId, copyPolicy);
    }

    @Override
    protected void convertRelationshipInternal(final ServiceReceiverClientOther source, final ServiceReceiverClientOtherDto target,
            final RelationshipExpression relationshipExpression) {
        switch (relationshipExpression.getName(ServiceReceiverClientOtherRelationship.class)) {
        default:
            break;
        }
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
