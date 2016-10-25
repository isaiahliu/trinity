package org.trinity.yqyl.process.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.trinity.common.dto.object.LookupDto;
import org.trinity.message.ILookupMessage;
import org.trinity.process.converter.AbstractLookupSupportObjectConverter;
import org.trinity.process.converter.IObjectConverter;
import org.trinity.yqyl.common.message.dto.domain.ServiceSupplierClientDto;
import org.trinity.yqyl.common.message.lookup.PersonalType;
import org.trinity.yqyl.common.message.lookup.ServiceSupplierClientStatus;
import org.trinity.yqyl.repository.business.entity.ServiceSupplierClient;

@Component
public class ServiceSupplierClientConverter extends AbstractLookupSupportObjectConverter<ServiceSupplierClient, ServiceSupplierClientDto> {
    @Autowired
    public ServiceSupplierClientConverter(final IObjectConverter<ILookupMessage<?>, LookupDto> lookupConverter) {
        super(lookupConverter);
    }

    @Override
    protected void convertBackInternal(final ServiceSupplierClientDto source, final ServiceSupplierClient target,
            final CopyPolicy copyPolicy) {
        copyObject(source::getId, target::getUserId, target::setUserId, copyPolicy);
        copyLookup(source::getStatus, target::getStatus, target::setStatus, ServiceSupplierClientStatus.class, copyPolicy);
        copyLookup(source::getType, target::getType, target::setType, PersonalType.class, copyPolicy);
        copyObject(source::getEmail, target::getEmail, target::setEmail, copyPolicy);
        copyObject(source::getIdentity, target::getIdentity, target::setIdentity, copyPolicy);
        copyObject(source::getLogo, target::getLogo, target::setLogo, copyPolicy);
        copyObject(source::getAddress, target::getAddress, target::setAddress, copyPolicy);
        copyObject(source::getName, target::getName, target::setName, copyPolicy);
        copyObject(source::getDescription, target::getDescription, target::setDescription, copyPolicy);
    }

    @Override
    protected void convertInternal(final ServiceSupplierClient source, final ServiceSupplierClientDto target, final CopyPolicy copyPolicy) {
        copyObject(source::getUserId, target::getId, target::setId, copyPolicy);
        copyMessage(source::getStatus, target::getStatus, target::setStatus, copyPolicy);
        copyMessage(source::getType, target::getType, target::setType, copyPolicy);
        copyObject(source::getEmail, target::getEmail, target::setEmail, copyPolicy);
        copyObject(source::getIdentity, target::getIdentity, target::setIdentity, copyPolicy);
        copyObject(source::getName, target::getName, target::setName, copyPolicy);
        copyObject(source::getAddress, target::getAddress, target::setAddress, copyPolicy);
        copyObject(source::getIdentityCopy, target::getIdentityCopy, target::setIdentityCopy, copyPolicy);
        copyObject(source::getLogo, target::getLogo, target::setLogo, copyPolicy);
        copyObject(source::getLicenseCopy, target::getLicenseCopy, target::setLicenseCopy, copyPolicy);
        copyObject(source::getDescription, target::getDescription, target::setDescription, copyPolicy);
    }

    @Override
    protected ServiceSupplierClient createFromInstance() {
        return new ServiceSupplierClient();
    }

    @Override
    protected ServiceSupplierClientDto createToInstance() {
        return new ServiceSupplierClientDto();
    }

}
