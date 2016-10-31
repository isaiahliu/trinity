package org.trinity.yqyl.process.datapermission;

import org.springframework.stereotype.Component;
import org.trinity.common.exception.IException;
import org.trinity.yqyl.repository.business.entity.ServiceSupplierClientAccount;

@Component
public class ServiceSupplierClientAccountDataPermissionValidator
        extends AbstractDataPermissionValidator<ServiceSupplierClientAccount> {
    @Override
    public void checkSpecialPermission() throws IException {
    }

    @Override
    public Class<ServiceSupplierClientAccount> getEntityType() {
        return ServiceSupplierClientAccount.class;
    }

    @Override
    protected void validateData(final String username, final Long id) throws IException {
    }
}

