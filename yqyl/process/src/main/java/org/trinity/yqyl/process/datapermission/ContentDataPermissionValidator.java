package org.trinity.yqyl.process.datapermission;

import org.springframework.stereotype.Component;
import org.trinity.common.exception.IException;
import org.trinity.yqyl.repository.business.entity.Content;

@Component
public class ContentDataPermissionValidator
        extends AbstractDataPermissionValidator<Content> {
    @Override
    public void checkSpecialPermission() throws IException {
    }

    @Override
    public Class<Content> getEntityType() {
        return Content.class;
    }

    @Override
    protected void validateData(final String username, final Long id) throws IException {
    }
}

