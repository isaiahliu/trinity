package org.trinity.yqyl.process.converter;

import org.springframework.stereotype.Component;
import org.trinity.yqyl.common.message.dto.domain.AccountPostingDto;
import org.trinity.yqyl.repository.business.entity.AccountPosting;

@Component
public class AccountPostingConverter extends AbstractLookupSupportObjectConverter<AccountPosting, AccountPostingDto> {
@Override
protected void convertBackInternal(final AccountPostingDto source, final AccountPosting target, final CopyPolicy copyPolicy) {
}

@Override
protected void convertInternal(final AccountPosting source, final AccountPostingDto target, final CopyPolicy copyPolicy) {
}

@Override
protected AccountPosting createFromInstance() {
return new AccountPosting();
}

@Override
protected AccountPostingDto createToInstance() {
return new AccountPostingDto();
}
}