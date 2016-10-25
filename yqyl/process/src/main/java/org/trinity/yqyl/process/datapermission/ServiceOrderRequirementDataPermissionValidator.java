package org.trinity.yqyl.process.datapermission;

import org.springframework.stereotype.Component;
import org.trinity.common.exception.IException;
import org.trinity.yqyl.repository.business.entity.ServiceOrderRequirement;

@Component
public class ServiceOrderRequirementDataPermissionValidator
        extends AbstractDataPermissionValidator<ServiceOrderRequirement> {
    @Override
    public void checkSpecialPermission() throws IException {
    }

    @Override
    public Class<ServiceOrderRequirement> getEntityType() {
        return ServiceOrderRequirement.class;
    }

    @Override
    protected void validateData(final String username, final Long id) throws IException {
    }
}

