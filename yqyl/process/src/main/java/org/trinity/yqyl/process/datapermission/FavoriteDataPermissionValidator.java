package org.trinity.yqyl.process.datapermission;

import org.springframework.stereotype.Component;
import org.trinity.common.exception.IException;
import org.trinity.yqyl.repository.business.entity.Favorite;

@Component
public class FavoriteDataPermissionValidator
        extends AbstractDataPermissionValidator<Favorite> {
    @Override
    public void checkSpecialPermission() throws IException {
    }

    @Override
    public Class<Favorite> getEntityType() {
        return Favorite.class;
    }

    @Override
    protected void validateData(final String username, final Long id) throws IException {
    }
}

