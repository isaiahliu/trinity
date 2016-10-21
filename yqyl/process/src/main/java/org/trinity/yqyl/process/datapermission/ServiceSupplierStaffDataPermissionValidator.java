package org.trinity.yqyl.process.datapermission;

import org.springframework.stereotype.Component;
import org.trinity.common.exception.IException;
import org.trinity.yqyl.repository.business.entity.ServiceSupplierStaff;

@Component
public class ServiceSupplierStaffDataPermissionValidator
        extends AbstractDataPermissionValidator<ServiceSupplierStaff> {
    @Override
    public void checkSpecialPermission() throws IException {
    }

    @Override
    public Class<ServiceSupplierStaff> getEntityType() {
        return ServiceSupplierStaff.class;
    }

    @Override
    protected void validateData(final String username, final Long id) throws IException {
    }
}
