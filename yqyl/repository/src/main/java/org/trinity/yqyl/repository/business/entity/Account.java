//Cleaned
package org.trinity.yqyl.repository.business.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
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

	// bi-directional one-to-one association to User
	@OneToOne
	@JoinColumn(name = "user_id")
	private User user;

	// bi-directional many-to-one association to AccountBalance
	@OneToMany(mappedBy = "account")
	private List<AccountBalance> accountBalances;

	public Account() {
	}

	public AccountBalance addAccountBalance(final AccountBalance accountBalance) {
		getAccountBalances().add(accountBalance);
		accountBalance.setAccount(this);

		return accountBalance;
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
		return this.userId;
	}

	public AccountBalance removeAccountBalance(final AccountBalance accountBalance) {
		getAccountBalances().remove(accountBalance);
		accountBalance.setAccount(null);

		return accountBalance;
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
