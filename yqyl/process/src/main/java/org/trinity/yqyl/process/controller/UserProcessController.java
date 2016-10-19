package org.trinity.yqyl.process.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.trinity.common.accessright.ISecurityUtil;
import org.trinity.common.accessright.ISecurityUtil.CheckMode;
import org.trinity.common.exception.IException;
import org.trinity.yqyl.common.message.dto.domain.UserDto;
import org.trinity.yqyl.common.message.dto.domain.UserSearchingDto;
import org.trinity.yqyl.common.message.exception.ErrorMessage;
import org.trinity.yqyl.common.message.lookup.AccessRight;
import org.trinity.yqyl.common.message.lookup.UserStatus;
import org.trinity.yqyl.process.controller.base.AbstractAutowiredCrudProcessController;
import org.trinity.yqyl.process.controller.base.IUserProcessController;
import org.trinity.yqyl.repository.business.dataaccess.IUserRepository;
import org.trinity.yqyl.repository.business.entity.User;

@Service
public class UserProcessController extends AbstractAutowiredCrudProcessController<User, UserDto, UserSearchingDto, IUserRepository>
		implements IUserProcessController {
	@Autowired
	private ISecurityUtil<AccessRight> securityUtil;

	public UserProcessController() {
		super(User.class, ErrorMessage.UNABLE_TO_FIND_USER);
	}

	@Override
	public void changePassword(final Long id, final String oldPassword, final String newPassword) throws IException {
		final User user = getDomainEntityRepository().findOne(id);

		validateDataPermission(getDomainObjectConverter().convert(user));

		if (!user.getPassword().equals(oldPassword)) {
			throw getExceptionFactory().createException(ErrorMessage.WRONG_PASSWORD);
		}

		user.setPassword(newPassword);

		getDomainEntityRepository().save(user);
	}

	@Override
	public List<UserDto> getMe() throws IException {
		final String username = securityUtil.getCurrentToken().getUsername();

		final User user = getDomainEntityRepository().findOneByUsername(username);
		final UserDto userDto = getDomainObjectConverter().convert(user);
		userDto.setPassword("");

		final List<UserDto> result = new ArrayList<>();
		result.add(userDto);
		return result;
	}

	@Override
	protected void addRelationship(final User entity, final UserDto dto) {
		entity.setStatus(UserStatus.ACTIVE);
	}

	@Override
	protected void validateDataPermission(final UserDto dto) throws IException {
		final String currentUser = securityUtil.getCurrentToken().getUsername();

		final User user = getDomainEntityRepository().findOne(dto.getId());

		if (!securityUtil.hasAccessRight(CheckMode.ANY, AccessRight.ADMINISTRATOR)) {
			if (!currentUser.equals(user.getUsername())) {
				throw getExceptionFactory().createException(ErrorMessage.UNABLE_TO_ACCESS_USER, user.getUsername());
			}
		}
	}
}
