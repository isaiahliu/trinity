package org.trinity.yqyl.process.datapermission;

import org.springframework.stereotype.Component;
import org.trinity.common.exception.IException;
import org.trinity.yqyl.repository.business.entity.ServiceReceiverClientOther;

@Component
public class ServiceReceiverClientOtherDataPermissionValidator
        extends AbstractDataPermissionValidator<ServiceReceiverClientOther> {
    @Override
    public void checkSpecialPermission() throws IException {
    }

    @Override
    public Class<ServiceReceiverClientOther> getEntityType() {
        return ServiceReceiverClientOther.class;
    }

    @Override
    protected void validateData(final String username, final Long id) throws IException {
    }
}
