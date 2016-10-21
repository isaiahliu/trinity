package org.trinity.yqyl.process.converter;

import org.springframework.stereotype.Component;
import org.trinity.yqyl.common.message.dto.domain.ServiceSupplierStaffDto;
import org.trinity.yqyl.common.message.lookup.StaffStatus;
import org.trinity.yqyl.repository.business.entity.ServiceSupplierStaff;

@Component
public class ServiceSupplierStaffConverter extends AbstractLookupSupportObjectConverter<ServiceSupplierStaff, ServiceSupplierStaffDto> {
    @Override
    protected void convertBackInternal(final ServiceSupplierStaffDto source, final ServiceSupplierStaff target,
            final CopyPolicy copyPolicy) {
        copyObject(source::getId, target::getId, target::setId, copyPolicy);
        copyObject(source::getComment, target::getComment, target::setComment, copyPolicy);
        copyObject(source::getDob, target::getDob, target::setDob, copyPolicy);
        copyObject(source::getIdentityCard, target::getIdentityCard, target::setIdentityCard, copyPolicy);
        copyObject(source::getName, target::getName, target::setName, copyPolicy);
        copyObject(source::getPhoneNo, target::getPhoneNo, target::setPhoneNo, copyPolicy);
        copyObject(source::getPhoto, target::getPhoto, target::setPhoto, copyPolicy);
        copyLookup(source::getStatus, target::getStatus, target::setStatus, StaffStatus.class, copyPolicy);
    }

    @Override
    protected void convertInternal(final ServiceSupplierStaff source, final ServiceSupplierStaffDto target, final CopyPolicy copyPolicy) {
        copyObject(source::getId, target::getId, target::setId, copyPolicy);
        copyObject(source::getComment, target::getComment, target::setComment, copyPolicy);
        copyObject(source::getDob, target::getDob, target::setDob, copyPolicy);
        copyObject(source::getIdentityCard, target::getIdentityCard, target::setIdentityCard, copyPolicy);
        copyObject(source::getName, target::getName, target::setName, copyPolicy);
        copyObject(source::getPhoneNo, target::getPhoneNo, target::setPhoneNo, copyPolicy);
        copyObject(source::getPhoto, target::getPhoto, target::setPhoto, copyPolicy);
        copyMessage(source::getStatus, target::getStatus, target::setStatus, copyPolicy);
    }

    @Override
    protected ServiceSupplierStaff createFromInstance() {
        return new ServiceSupplierStaff();
    }

    @Override
    protected ServiceSupplierStaffDto createToInstance() {
        return new ServiceSupplierStaffDto();
    }
}
