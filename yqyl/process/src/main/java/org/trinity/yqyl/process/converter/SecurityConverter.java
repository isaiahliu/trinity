package org.trinity.yqyl.process.converter;

import org.springframework.stereotype.Component;
import org.trinity.yqyl.common.message.dto.domain.SecurityDto;
import org.trinity.yqyl.repository.business.entity.User;

@Component
public class SecurityConverter extends AbstractLookupSupportObjectConverter<User, SecurityDto> {
    @Override
    protected void convertBackInternal(final SecurityDto source, final User target, final CopyPolicy copyPolicy) {
        copyObject(source::getUsername, target::getUsername, target::setUsername, copyPolicy);
        copyObject(source::getPassword, target::getPassword, target::setPassword, copyPolicy);
    }

    @Override
    protected void convertInternal(final User source, final SecurityDto target, final CopyPolicy copyPolicy) {
        copyObject(source::getUsername, target::getUsername, target::setUsername, copyPolicy);
        copyObject(source::getPassword, target::getPassword, target::setPassword, copyPolicy);
    }

    @Override
    protected User createFromInstance() {
        return new User();
    }

    @Override
    protected SecurityDto createToInstance() {
        return new SecurityDto();
    }

}