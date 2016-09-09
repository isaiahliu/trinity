package org.trinity.yqyl.repository.business.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Currency;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import org.trinity.repository.entity.AbstractAuditableEntity;
import org.trinity.yqyl.common.message.lookup.AccountPostingStatus;

/**
 * The persistent class for the account_posting database table.
 *
 */
@Entity
@Table(name = "account_posting")
@NamedQuery(name = "AccountPosting.findAll", query = "SELECT a FROM AccountPosting a")
public class AccountPosting extends AbstractAuditableEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "AccountPosting_PK_IdGenerator")
    @TableGenerator(name = "AccountPosting_PK_IdGenerator", table = "id_table", pkColumnName = "type", pkColumnValue = "AccountPosting_PK", valueColumnName = "value", initialValue = 1, allocationSize = 1)
    private Long id;

    private BigDecimal amount;

    private Currency currency;

    private AccountPostingStatus status;

    // bi-directional many-to-one association to AccountBalance
    @ManyToOne
    @JoinColumn(name = "account_balance_id")
    private AccountBalance accountBalance;

    // bi-directional many-to-one association to AccountTransaction
    @ManyToOne
    @JoinColumn(name = "account_transaction_id")
    private AccountTransaction accountTransaction;

    public AccountPosting() {
    }

    public AccountBalance getAccountBalance() {
        return this.accountBalance;
    }

    public AccountTransaction getAccountTransaction() {
        return this.accountTransaction;
    }

    public BigDecimal getAmount() {
        return this.amount;
    }

    public Currency getCurrency() {
        return this.currency;
    }

    public Long getId() {
        return this.id;
    }

    public AccountPostingStatus getStatus() {
        return this.status;
    }

    public void setAccountBalance(final AccountBalance accountBalance) {
        this.accountBalance = accountBalance;
    }

    public void setAccountTransaction(final AccountTransaction accountTransaction) {
        this.accountTransaction = accountTransaction;
    }

    public void setAmount(final BigDecimal amount) {
        this.amount = amount;
    }

    public void setCurrency(final Currency currency) {
        this.currency = currency;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public void setStatus(final AccountPostingStatus status) {
        this.status = status;
    }

}
