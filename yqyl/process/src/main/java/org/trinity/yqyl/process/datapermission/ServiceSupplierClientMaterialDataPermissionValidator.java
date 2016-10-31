package org.trinity.yqyl.process.datapermission;

import org.springframework.stereotype.Component;
import org.trinity.common.exception.IException;
import org.trinity.yqyl.repository.business.entity.ServiceSupplierClientMaterial;

@Component
public class ServiceSupplierClientMaterialDataPermissionValidator
        extends AbstractDataPermissionValidator<ServiceSupplierClientMaterial> {
    @Override
    public void checkSpecialPermission() throws IException {
    }

    @Override
    public Class<ServiceSupplierClientMaterial> getEntityType() {
        return ServiceSupplierClientMaterial.class;
    }

    @Override
    protected void validateData(final String username, final Long id) throws IException {
    }
}

