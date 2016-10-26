package org.trinity.yqyl.process.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.trinity.common.dto.object.LookupDto;
import org.trinity.common.dto.object.RelationshipExpression;
import org.trinity.message.ILookupMessage;
import org.trinity.process.converter.AbstractLookupSupportObjectConverter;
import org.trinity.process.converter.IObjectConverter;
import org.trinity.yqyl.common.message.dto.domain.ServiceInfoDto;
import org.trinity.yqyl.common.message.lookup.PaymentMethod;
import org.trinity.yqyl.common.message.lookup.PaymentType;
import org.trinity.yqyl.common.message.lookup.ServiceStatus;
import org.trinity.yqyl.repository.business.entity.ServiceInfo;

@Component
public class ServiceInfoConverter extends AbstractLookupSupportObjectConverter<ServiceInfo, ServiceInfoDto> {
    private static enum ServiceInfoRelationship {
    }

    @Autowired
    public ServiceInfoConverter(final IObjectConverter<ILookupMessage<?>, LookupDto> lookupConverter) {
        super(lookupConverter);
    }

    @Override
    protected void convertBackInternal(final ServiceInfoDto source, final ServiceInfo target, final CopyPolicy copyPolicy) {
        copyObject(source::getId, target::getId, target::setId, copyPolicy);
        copyObject(source::getName, target::getName, target::setName, copyPolicy);
        copyObject(source::getDescription, target::getDescription, target::setDescription, copyPolicy);
        copyObject(source::getPrice, target::getPrice, target::setPrice, copyPolicy);
        copyObject(source::getImage, target::getImage, target::setImage, copyPolicy);
        copyLookup(source::getStatus, target::getStatus, target::setStatus, ServiceStatus.class, copyPolicy);
        copyLookup(source::getPaymentMethod, target::getPaymentMethod, target::setPaymentMethod, PaymentMethod.class, copyPolicy);
        copyLookup(source::getPaymentType, target::getPaymentType, target::setPaymentType, PaymentType.class, copyPolicy);
    }

    @Override
    protected void convertInternal(final ServiceInfo source, final ServiceInfoDto target, final CopyPolicy copyPolicy) {
        copyObject(source::getId, target::getId, target::setId, copyPolicy);
        copyObject(source::getName, target::getName, target::setName, copyPolicy);
        copyObject(source::getDescription, target::getDescription, target::setDescription, copyPolicy);
        copyObject(source::getPrice, target::getPrice, target::setPrice, copyPolicy);
        copyObject(source::getImage, target::getImage, target::setImage, copyPolicy);
        copyMessage(source::getStatus, target::getStatus, target::setStatus, copyPolicy);
        copyMessage(source::getPaymentMethod, target::getPaymentMethod, target::setPaymentMethod, copyPolicy);
        copyMessage(source::getPaymentType, target::getPaymentType, target::setPaymentType, copyPolicy);
    }

    @Override
    protected void convertRelationshipInternal(final ServiceInfo source, final ServiceInfoDto target,
            final RelationshipExpression relationshipExpression) {
        switch (relationshipExpression.getName(ServiceInfoRelationship.class)) {
        default:
            break;
        }
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
