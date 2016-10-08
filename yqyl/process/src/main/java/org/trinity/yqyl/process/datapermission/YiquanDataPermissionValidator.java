package org.trinity.yqyl.process.datapermission;

import org.springframework.stereotype.Component;
import org.trinity.common.exception.IException;
import org.trinity.yqyl.repository.business.entity.Yiquan;

@Component
public class YiquanDataPermissionValidator
        extends AbstractDataPermissionValidator<Yiquan> {
    @Override
    public void checkSpecialPermission() throws IException {
    }

    @Override
    public Class<Yiquan> getEntityType() {
        return Yiquan.class;
    }

    @Override
    protected void validateData(final String username, final Long id) throws IException {
    }
}

