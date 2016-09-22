package org.trinity.yqyl.process.datapermission;

import org.springframework.stereotype.Component;
import org.trinity.common.exception.IException;
import org.trinity.yqyl.repository.business.entity.ServiceReceiverClientHealthInformation;

@Component
public class ServiceReceiverClientHealthInformationDataPermissionValidator
        extends AbstractDataPermissionValidator<ServiceReceiverClientHealthInformation> {
    @Override
    public void checkSpecialPermission() throws IException {
    }

    @Override
    public Class<ServiceReceiverClientHealthInformation> getEntityType() {
        return ServiceReceiverClientHealthInformation.class;
    }

    @Override
    protected void validateData(final String username, final Long id) throws IException {
    }
}

