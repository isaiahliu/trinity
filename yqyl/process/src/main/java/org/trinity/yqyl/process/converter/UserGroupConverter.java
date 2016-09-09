package org.trinity.yqyl.process.converter;

import org.springframework.stereotype.Component;
import org.trinity.yqyl.common.message.dto.domain.UserGroupDto;
import org.trinity.yqyl.repository.business.entity.UserGroup;

@Component
public class UserGroupConverter extends AbstractLookupSupportObjectConverter<UserGroup, UserGroupDto> {
@Override
protected void convertBackInternal(final UserGroupDto source, final UserGroup target, final CopyPolicy copyPolicy) {
}

@Override
protected void convertInternal(final UserGroup source, final UserGroupDto target, final CopyPolicy copyPolicy) {
}

@Override
protected UserGroup createFromInstance() {
return new UserGroup();
}

@Override
protected UserGroupDto createToInstance() {
return new UserGroupDto();
}
}
