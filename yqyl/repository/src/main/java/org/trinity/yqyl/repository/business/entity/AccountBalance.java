//Cleaned
package org.trinity.yqyl.repository.business.entity;

import java.io.Serializable;
import java.util.Currency;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;

import org.trinity.repository.entity.AbstractAuditableEntity;
import org.trinity.yqyl.common.message.lookup.AccountBalanceStatus;
import org.trinity.yqyl.common.message.lookup.AccountCategory;

/**
 * The persistent class for the account_balance database table.
 *
 */
@Embeddable
public class AccountBalance extends AbstractAuditableEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    private Double amount;

    private AccountCategory category;

    private Currency currency;

    private AccountBalanceStatus status;

    @ElementCollection
    @CollectionTable(name = "account_posting", joinColumns = { @JoinColumn(name = "account_id"), @JoinColumn(name = "category") })
    private List<AccountPosting> accountPostings;

    public AccountBalance() {
    }

    public List<AccountPosting> getAccountPostings() {
        return accountPostings;
    }

    public Double getAmount() {
        return amount;
    }

    public AccountCategory getCategory() {
        return this.category;
    }

    public Currency getCurrency() {
        return this.currency;
    }

    public AccountBalanceStatus getStatus() {
        return this.status;
    }

    public void setAccountPostings(final List<AccountPosting> accountPostings) {
        this.accountPostings = accountPostings;
    }

    public void setAmount(final Double amount) {
        this.amount = amount;
    }

    public void setCategory(final AccountCategory category) {
        this.category = category;
    }

    public void setCurrency(final Currency currency) {
        this.currency = currency;
    }

    public void setStatus(final AccountBalanceStatus status) {
        this.status = status;
    }
}
