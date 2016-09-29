package org.trinity.yqyl.process.datapermission;

import org.springframework.stereotype.Component;
import org.trinity.common.exception.IException;
import org.trinity.yqyl.repository.business.entity.ServiceReceiverClientHealthIndicator;

@Component
public class ServiceReceiverClientHealthIndicatorDataPermissionValidator
        extends AbstractDataPermissionValidator<ServiceReceiverClientHealthIndicator> {
    @Override
    public void checkSpecialPermission() throws IException {
    }

    @Override
    public Class<ServiceReceiverClientHealthIndicator> getEntityType() {
        return ServiceReceiverClientHealthIndicator.class;
    }

    @Override
    protected void validateData(final String username, final Long id) throws IException {
    }
}
