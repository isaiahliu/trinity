//Cleaned
package org.trinity.yqyl.repository.business.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.trinity.repository.entity.AbstractAuditableEntity;
import org.trinity.yqyl.common.message.lookup.AccountPostingStatus;
import org.trinity.yqyl.common.message.lookup.Direction;

/**
 * The persistent class for the account_posting database table.
 *
 */
@Embeddable
public class AccountPosting extends AbstractAuditableEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    private AccountPostingStatus status;

    private Direction direction;

    @ManyToOne
    @JoinColumn(name = "account_transaction_id")
    private AccountTransaction accountTransaction;

    public AccountPosting() {
    }

    public AccountTransaction getAccountTransaction() {
        return accountTransaction;
    }

    public Direction getDirection() {
        return direction;
    }

    public AccountPostingStatus getStatus() {
        return this.status;
    }

    public void setAccountTransaction(final AccountTransaction accountTransaction) {
        this.accountTransaction = accountTransaction;
    }

    public void setDirection(final Direction direction) {
        this.direction = direction;
    }

    public void setStatus(final AccountPostingStatus status) {
        this.status = status;
    }

}
