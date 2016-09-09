package org.trinity.yqyl.process.converter;

import org.springframework.stereotype.Component;
import org.trinity.yqyl.common.message.dto.domain.ServiceReceiverClientDto;
import org.trinity.yqyl.common.message.lookup.Gender;
import org.trinity.yqyl.common.message.lookup.ServiceReceiverClientStatus;
import org.trinity.yqyl.repository.business.entity.ServiceReceiverClient;

@Component
public class ServiceReceiverClientConverter extends AbstractLookupSupportObjectConverter<ServiceReceiverClient, ServiceReceiverClientDto> {
    private static final String DATE_FORMAT = "yyyy/MM/dd";

    @Override
    protected void convertBackInternal(final ServiceReceiverClientDto source, final ServiceReceiverClient target,
            final CopyPolicy copyPolicy) {
        copyObject(source::getId, target::getId, target::setId, copyPolicy);
        copyLookup(source::getStatus, target::getStatus, target::setStatus, ServiceReceiverClientStatus.class, copyPolicy);
        copyLookup(source::getGender, target::getGender, target::setGender, Gender.class, copyPolicy);
        copyObject(source::getAddress, target::getAddress, target::setAddress, copyPolicy);
        copyObject(source::getCellphoneNo, target::getCellphoneNo, target::setCellphoneNo, copyPolicy);
        copyDateString(source::getDob, target::getDob, target::setDob, DATE_FORMAT, copyPolicy);
        copyObject(source::getEmail, target::getEmail, target::setEmail, copyPolicy);
        copyObject(source::getHomephoneNo, target::getHomephoneNo, target::setHomephoneNo, copyPolicy);
        copyObject(source::getIdentityCard, target::getIdentityCard, target::setIdentityCard, copyPolicy);
        copyObject(source::getName, target::getName, target::setName, copyPolicy);
        copyObject(source::getYijinCode, target::getYijinCode, target::setYijinCode, copyPolicy);
    }

    @Override
    protected void convertInternal(final ServiceReceiverClient source, final ServiceReceiverClientDto target, final CopyPolicy copyPolicy) {
        copyObject(source::getId, target::getId, target::setId, copyPolicy);
        copyMessage(source::getStatus, target::getStatus, target::setStatus, copyPolicy);
        copyMessage(source::getGender, target::getGender, target::setGender, copyPolicy);
        copyObject(source::getAddress, target::getAddress, target::setAddress, copyPolicy);
        copyObject(source::getCellphoneNo, target::getCellphoneNo, target::setCellphoneNo, copyPolicy);
        copyDate(source::getDob, target::getDob, target::setDob, DATE_FORMAT, copyPolicy);
        copyObject(source::getEmail, target::getEmail, target::setEmail, copyPolicy);
        copyObject(source::getHomephoneNo, target::getHomephoneNo, target::setHomephoneNo, copyPolicy);
        copyObject(source::getIdentityCard, target::getIdentityCard, target::setIdentityCard, copyPolicy);
        copyObject(source::getName, target::getName, target::setName, copyPolicy);
        copyObject(source::getYijinCode, target::getYijinCode, target::setYijinCode, copyPolicy);
    }

    @Override
    protected ServiceReceiverClient createFromInstance() {
        return new ServiceReceiverClient();
    }

    @Override
    protected ServiceReceiverClientDto createToInstance() {
        return new ServiceReceiverClientDto();
    }
}
