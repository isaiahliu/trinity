//Cleaned
package org.trinity.yqyl.repository.business.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;

import org.trinity.repository.entity.AbstractAuditableEntity;
import org.trinity.yqyl.common.message.lookup.AccountStatus;

/**
 * The persistent class for the account database table.
 *
 */
@Entity
@NamedQuery(name = "Account.findAll", query = "SELECT a FROM Account a")
public class Account extends AbstractAuditableEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "user_id")
    private Long userId;

    private AccountStatus status;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ElementCollection
    @CollectionTable(name = "account_balance", joinColumns = @JoinColumn(name = "account_id"))
    private List<AccountBalance> accountBalances;

    public Account() {
    }

    public List<AccountBalance> getAccountBalances() {
        return this.accountBalances;
    }

    public AccountStatus getStatus() {
        return this.status;
    }

    public User getUser() {
        return this.user;
    }

    public Long getUserId() {
        return userId;
    }

    public void setAccountBalances(final List<AccountBalance> accountBalances) {
        this.accountBalances = accountBalances;
    }

    public void setStatus(final AccountStatus status) {
        this.status = status;
    }

    public void setUser(final User user) {
        this.user = user;
    }

    public void setUserId(final Long userId) {
        this.userId = userId;
    }
}
