package org.trinity.yqyl.process.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.trinity.common.dto.object.LookupDto;
import org.trinity.common.dto.object.RelationshipExpression;
import org.trinity.common.util.Tuple2;
import org.trinity.message.ILookupMessage;
import org.trinity.process.converter.AbstractLookupSupportObjectConverter;
import org.trinity.process.converter.IObjectConverter;
import org.trinity.yqyl.common.message.dto.domain.AccountPostingDto;
import org.trinity.yqyl.common.message.dto.domain.AccountTransactionDto;
import org.trinity.yqyl.common.message.lookup.AccountPostingStatus;
import org.trinity.yqyl.repository.business.entity.AccountPosting;
import org.trinity.yqyl.repository.business.entity.AccountTransaction;

@Component
public class AccountPostingConverter extends AbstractLookupSupportObjectConverter<AccountPosting, AccountPostingDto> {
    private static enum AccountPostingRelationship {
        TRANSACTION,
        NA
    }

    private IObjectConverter<AccountTransaction, AccountTransactionDto> accountTransactionConverter;

    @Autowired
    public AccountPostingConverter(final IObjectConverter<Tuple2<ILookupMessage<?>, String[]>, LookupDto> lookupConverter) {
        super(lookupConverter);
    }

    @Override
    protected void convertBackInternal(final AccountPostingDto source, final AccountPosting target, final CopyPolicy copyPolicy) {
        copyObject(source::getId, target::getId, target::setId, copyPolicy);
        copyLookup(source::getStatus, target::getStatus, target::setStatus, AccountPostingStatus.class, copyPolicy);
        copyObject(source::getAmount, target::getAmount, target::setAmount, copyPolicy);
    }

    @Override
    protected void convertInternal(final AccountPosting source, final AccountPostingDto target, final CopyPolicy copyPolicy) {
        copyObject(source::getId, target::getId, target::setId, copyPolicy);
        copyMessage(source::getStatus, target::getStatus, target::setStatus, copyPolicy);
        copyObject(source::getAmount, target::getAmount, target::setAmount, copyPolicy);
    }

    @Override
    protected void convertRelationshipInternal(final AccountPosting source, final AccountPostingDto target,
            final RelationshipExpression relationshipExpression) {
        switch (relationshipExpression.getName(AccountPostingRelationship.class)) {
        case TRANSACTION:
            copyRelationship(source::getAccountTransaction, target::setTransaction, accountTransactionConverter, relationshipExpression);
            break;
        case NA:
        default:
            break;
        }
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
