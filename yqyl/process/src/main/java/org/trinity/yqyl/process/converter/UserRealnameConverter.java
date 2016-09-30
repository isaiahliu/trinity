package org.trinity.yqyl.process.converter;

import org.springframework.stereotype.Component;
import org.trinity.yqyl.common.message.dto.domain.UserRealnameDto;
import org.trinity.yqyl.repository.business.entity.UserRealname;

@Component
public class UserRealnameConverter extends AbstractLookupSupportObjectConverter<UserRealname, UserRealnameDto> {
@Override
protected void convertBackInternal(final UserRealnameDto source, final UserRealname target, final CopyPolicy copyPolicy) {
}

@Override
protected void convertInternal(final UserRealname source, final UserRealnameDto target, final CopyPolicy copyPolicy) {
}

@Override
protected UserRealname createFromInstance() {
return new UserRealname();
}

@Override
protected UserRealnameDto createToInstance() {
return new UserRealnameDto();
}
}
