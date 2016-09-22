package org.trinity.yqyl.process.datapermission;

import org.springframework.stereotype.Component;
import org.trinity.common.exception.IException;
import org.trinity.yqyl.repository.business.entity.ServiceReceiverClientInterest;

@Component
public class ServiceReceiverClientInterestDataPermissionValidator
        extends AbstractDataPermissionValidator<ServiceReceiverClientInterest> {
    @Override
    public void checkSpecialPermission() throws IException {
    }

    @Override
    public Class<ServiceReceiverClientInterest> getEntityType() {
        return ServiceReceiverClientInterest.class;
    }

    @Override
    protected void validateData(final String username, final Long id) throws IException {
    }
}

