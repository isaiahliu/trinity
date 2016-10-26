package org.trinity.yqyl.process.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.trinity.common.dto.object.LookupDto;
import org.trinity.message.ILookupMessage;
import org.trinity.common.dto.object.RelationshipExpression;
import org.trinity.process.converter.AbstractLookupSupportObjectConverter;
import org.trinity.process.converter.IObjectConverter;
import org.trinity.yqyl.common.message.dto.domain.AccountTransactionDto;
import org.trinity.yqyl.repository.business.entity.AccountTransaction;

@Component
public class AccountTransactionConverter extends AbstractLookupSupportObjectConverter<AccountTransaction, AccountTransactionDto> {
    private static enum AccountTransactionRelationship {
    }

    @Autowired
    public AccountTransactionConverter(final IObjectConverter<ILookupMessage<?>, LookupDto> lookupConverter) {
        super(lookupConverter);
    }

    @Override
    protected void convertBackInternal(final AccountTransactionDto source, final AccountTransaction target, final CopyPolicy copyPolicy) {
        copyObject(source::getId, target::getId, target::setId, copyPolicy);
    }

    @Override
    protected void convertInternal(final AccountTransaction source, final AccountTransactionDto target, final CopyPolicy copyPolicy) {
        copyObject(source::getId, target::getId, target::setId, copyPolicy);
    }

    @Override
    protected AccountTransaction createFromInstance() {
        return new AccountTransaction();
    }

    @Override
    protected AccountTransactionDto createToInstance() {
        return new AccountTransactionDto();
    }

    @Override
    protected void convertRelationshipInternal(final AccountTransaction source, final AccountTransactionDto target,
            final RelationshipExpression relationshipExpression) {
        switch (relationshipExpression.getName(AccountTransactionRelationship.class)) {
        default:
            break;
        }
    }
}
