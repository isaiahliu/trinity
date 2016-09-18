package org.trinity.yqyl.process.converter;

import org.springframework.stereotype.Component;
import org.trinity.yqyl.common.message.dto.domain.UserDto;
import org.trinity.yqyl.common.message.lookup.UserStatus;
import org.trinity.yqyl.repository.business.entity.User;

@Component
public class UserConverter extends AbstractLookupSupportObjectConverter<User, UserDto> {
	@Override
	protected void convertBackInternal(final UserDto source, final User target, final CopyPolicy copyPolicy) {
		copyObject(source::getId, target::getId, target::setId, copyPolicy);
		copyObject(source::getUsername, target::getUsername, target::setUsername, copyPolicy);
		copyObject(source::getPassword, target::getPassword, target::setPassword, copyPolicy);
		copyObject(source::getCellphone, target::getCellphone, target::setCellphone, copyPolicy);
		copyObject(source::getEmail, target::getEmail, target::setEmail, copyPolicy);
		copyLookup(source::getStatus, target::getStatus, target::setStatus, UserStatus.class, copyPolicy);
	}

	@Override
	protected void convertInternal(final User source, final UserDto target, final CopyPolicy copyPolicy) {
		copyObject(source::getId, target::getId, target::setId, copyPolicy);
		copyObject(source::getUsername, target::getUsername, target::setUsername, copyPolicy);
		copyObject(source::getPassword, target::getPassword, target::setPassword, copyPolicy);
		copyObject(source::getCellphone, target::getCellphone, target::setCellphone, copyPolicy);
		copyObject(source::getEmail, target::getEmail, target::setEmail, copyPolicy);
		copyMessage(source::getStatus, target::getStatus, target::setStatus, copyPolicy);
	}

	@Override
	protected User createFromInstance() {
		return new User();
	}

	@Override
	protected UserDto createToInstance() {
		return new UserDto();
	}
}