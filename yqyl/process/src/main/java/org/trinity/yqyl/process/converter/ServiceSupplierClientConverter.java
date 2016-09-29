package org.trinity.yqyl.process.converter;

import org.springframework.stereotype.Component;
import org.trinity.yqyl.common.message.dto.domain.ServiceSupplierClientDto;
import org.trinity.yqyl.common.message.lookup.PersonalType;
import org.trinity.yqyl.common.message.lookup.ServiceSupplierClientStatus;
import org.trinity.yqyl.repository.business.entity.ServiceSupplierClient;

@Component
public class ServiceSupplierClientConverter extends AbstractLookupSupportObjectConverter<ServiceSupplierClient, ServiceSupplierClientDto> {

    @Override
    protected void convertBackInternal(final ServiceSupplierClientDto source, final ServiceSupplierClient target,
            final CopyPolicy copyPolicy) {
        copyObject(source::getId, target::getUserId, target::setUserId, copyPolicy);
        copyLookup(source::getStatus, target::getStatus, target::setStatus, ServiceSupplierClientStatus.class, copyPolicy);
        copyLookup(source::getType, target::getType, target::setType, PersonalType.class, copyPolicy);
        copyObject(source::getEmail, target::getEmail, target::setEmail, copyPolicy);
        copyObject(source::getIdentity, target::getIdentity, target::setIdentity, copyPolicy);
        copyObject(source::getAddress, target::getAddress, target::setAddress, copyPolicy);
        copyObject(source::getName, target::getName, target::setName, copyPolicy);
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

        copyObject(source::getLicenseCopy, target::getLicenseCopy, target::setLicenseCopy, copyPolicy);
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
