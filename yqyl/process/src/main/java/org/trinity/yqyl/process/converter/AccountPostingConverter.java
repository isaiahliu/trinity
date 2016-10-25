package org.trinity.yqyl.process.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.trinity.common.dto.object.LookupDto;
import org.trinity.message.ILookupMessage;
import org.trinity.process.converter.AbstractLookupSupportObjectConverter;
import org.trinity.process.converter.IObjectConverter;
import org.trinity.yqyl.common.message.dto.domain.AccountPostingDto;
import org.trinity.yqyl.repository.business.entity.AccountPosting;

@Component
public class AccountPostingConverter extends AbstractLookupSupportObjectConverter<AccountPosting, AccountPostingDto> {
    @Autowired
    public AccountPostingConverter(final IObjectConverter<ILookupMessage<?>, LookupDto> lookupConverter) {
        super(lookupConverter);
    }

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
