package org.trinity.yqyl.process.converter;

import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.trinity.common.dto.object.LookupDto;
import org.trinity.message.ILookupMessage;
import org.trinity.process.converter.AbstractLookupSupportObjectConverter;
import org.trinity.process.converter.IObjectConverter;
import org.trinity.yqyl.common.message.dto.domain.ServiceSupplierStaffDto;
import org.trinity.yqyl.common.message.lookup.StaffStatus;
import org.trinity.yqyl.repository.business.entity.ServiceSupplierStaff;

@Component
public class ServiceSupplierStaffConverter extends AbstractLookupSupportObjectConverter<ServiceSupplierStaff, ServiceSupplierStaffDto> {
    @Autowired
    public ServiceSupplierStaffConverter(final IObjectConverter<ILookupMessage<?>, LookupDto> lookupConverter) {
        super(lookupConverter);
    }

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

        if (source.getDob() != null) {
            final Calendar dob = Calendar.getInstance();
            dob.setTime(source.getDob());

            final Calendar now = Calendar.getInstance();
            now.setTime(new Date());

            final int currentYear = now.get(Calendar.YEAR);
            final int doy = dob.get(Calendar.YEAR);
            int age = 0;
            if (currentYear > doy) {
                age = currentYear - doy;
                final int currentMonth = now.get(Calendar.MONTH);
                final int dom = dob.get(Calendar.MONTH);
                if (currentMonth < dom || (currentMonth == dom && now.get(Calendar.DAY_OF_MONTH) < dob.get(Calendar.DAY_OF_MONTH))) {
                    age -= 1;
                }
            }
            target.setAge(age);
        }
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
