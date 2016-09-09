package org.trinity.yqyl.process.converter;

import org.springframework.stereotype.Component;
import org.trinity.yqyl.common.message.dto.domain.AccountTransactionDto;
import org.trinity.yqyl.repository.business.entity.AccountTransaction;

@Component
public class AccountTransactionConverter extends AbstractLookupSupportObjectConverter<AccountTransaction, AccountTransactionDto> {
@Override
protected void convertBackInternal(final AccountTransactionDto source, final AccountTransaction target, final CopyPolicy copyPolicy) {
}

@Override
protected void convertInternal(final AccountTransaction source, final AccountTransactionDto target, final CopyPolicy copyPolicy) {
}

@Override
protected AccountTransaction createFromInstance() {
return new AccountTransaction();
}

@Override
protected AccountTransactionDto createToInstance() {
return new AccountTransactionDto();
}
}
