package org.trinity.yqyl.process.datapermission;

import org.springframework.stereotype.Component;
import org.trinity.common.exception.IException;
import org.trinity.yqyl.repository.business.entity.ServiceSubOrder;

@Component
public class ServiceSubOrderDataPermissionValidator
        extends AbstractDataPermissionValidator<ServiceSubOrder> {
    @Override
    public void checkSpecialPermission() throws IException {
    }

    @Override
    public Class<ServiceSubOrder> getEntityType() {
        return ServiceSubOrder.class;
    }

    @Override
    protected void validateData(final String username, final Long id) throws IException {
    }
}

