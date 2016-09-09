package org.trinity.yqyl.process.datapermission;

import org.springframework.stereotype.Component;
import org.trinity.common.exception.IException;
import org.trinity.yqyl.repository.business.entity.Announcement;

@Component
public class AnnouncementDataPermissionValidator extends AbstractDataPermissionValidator<Announcement> {
	@Override
	public void checkSpecialPermission() throws IException {
	}

	@Override
	public Class<Announcement> getEntityType() {
		return Announcement.class;
	}

	@Override
	protected void validateData(final String username, final Long id) throws IException {
	}
}
