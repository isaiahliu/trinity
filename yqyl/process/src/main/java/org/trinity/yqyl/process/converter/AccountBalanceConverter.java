package org.trinity.yqyl.process.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.trinity.common.dto.object.LookupDto;
import org.trinity.message.ILookupMessage;
import org.trinity.common.dto.object.RelationshipExpression;
import org.trinity.process.converter.AbstractLookupSupportObjectConverter;
import org.trinity.process.converter.IObjectConverter;
import org.trinity.yqyl.common.message.dto.domain.AccountBalanceDto;
import org.trinity.yqyl.repository.business.entity.AccountBalance;

@Component
public class AccountBalanceConverter extends AbstractLookupSupportObjectConverter<AccountBalance, AccountBalanceDto> {
    private static enum AccountBalanceRelationship {
    }

    @Autowired
    public AccountBalanceConverter(final IObjectConverter<ILookupMessage<?>, LookupDto> lookupConverter) {
        super(lookupConverter);
    }

    @Override
    protected void convertBackInternal(final AccountBalanceDto source, final AccountBalance target, final CopyPolicy copyPolicy) {
        copyObject(source::getId, target::getId, target::setId, copyPolicy);
    }

    @Override
    protected void convertInternal(final AccountBalance source, final AccountBalanceDto target, final CopyPolicy copyPolicy) {
        copyObject(source::getId, target::getId, target::setId, copyPolicy);
    }

    @Override
    protected AccountBalance createFromInstance() {
        return new AccountBalance();
    }

    @Override
    protected AccountBalanceDto createToInstance() {
        return new AccountBalanceDto();
    }

    @Override
    protected void convertRelationshipInternal(final AccountBalance source, final AccountBalanceDto target,
            final RelationshipExpression relationshipExpression) {
        switch (relationshipExpression.getName(AccountBalanceRelationship.class)) {
        default:
            break;
        }
    }
}
