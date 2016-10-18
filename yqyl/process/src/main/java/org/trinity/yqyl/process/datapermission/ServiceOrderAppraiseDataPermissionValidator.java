package org.trinity.yqyl.process.datapermission;

import org.springframework.stereotype.Component;
import org.trinity.common.exception.IException;
import org.trinity.yqyl.repository.business.entity.ServiceOrderAppraise;

@Component
public class ServiceOrderAppraiseDataPermissionValidator
        extends AbstractDataPermissionValidator<ServiceOrderAppraise> {
    @Override
    public void checkSpecialPermission() throws IException {
    }

    @Override
    public Class<ServiceOrderAppraise> getEntityType() {
        return ServiceOrderAppraise.class;
    }

    @Override
    protected void validateData(final String username, final Long id) throws IException {
    }
}

