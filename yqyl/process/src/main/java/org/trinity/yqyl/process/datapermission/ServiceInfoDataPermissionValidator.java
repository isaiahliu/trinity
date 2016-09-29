package org.trinity.yqyl.process.datapermission;

import org.springframework.stereotype.Component;
import org.trinity.common.exception.IException;
import org.trinity.yqyl.repository.business.entity.ServiceInfo;

@Component
public class ServiceInfoDataPermissionValidator extends AbstractDataPermissionValidator<ServiceInfo> {
    @Override
    public void checkSpecialPermission() throws IException {
    }

    @Override
    public Class<ServiceInfo> getEntityType() {
        return ServiceInfo.class;
    }

    @Override
    protected void validateData(final String username, final Long id) throws IException {
    }
}
