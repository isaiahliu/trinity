package org.trinity.yqyl.process.converter;

import org.springframework.stereotype.Component;
import org.trinity.yqyl.common.message.dto.domain.AccountDto;
import org.trinity.yqyl.repository.business.entity.Account;

@Component
public class AccountConverter extends AbstractLookupSupportObjectConverter<Account, AccountDto> {
@Override
protected void convertBackInternal(final AccountDto source, final Account target, final CopyPolicy copyPolicy) {
}

@Override
protected void convertInternal(final Account source, final AccountDto target, final CopyPolicy copyPolicy) {
}

@Override
protected Account createFromInstance() {
return new Account();
}

@Override
protected AccountDto createToInstance() {
return new AccountDto();
}
}
