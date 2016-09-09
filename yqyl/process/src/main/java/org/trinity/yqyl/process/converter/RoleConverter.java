package org.trinity.yqyl.process.converter;

import org.springframework.stereotype.Component;
import org.trinity.yqyl.common.message.dto.domain.RoleDto;
import org.trinity.yqyl.repository.business.entity.Role;

@Component
public class RoleConverter extends AbstractLookupSupportObjectConverter<Role, RoleDto> {
@Override
protected void convertBackInternal(final RoleDto source, final Role target, final CopyPolicy copyPolicy) {
}

@Override
protected void convertInternal(final Role source, final RoleDto target, final CopyPolicy copyPolicy) {
}

@Override
protected Role createFromInstance() {
return new Role();
}

@Override
protected RoleDto createToInstance() {
return new RoleDto();
}
}
