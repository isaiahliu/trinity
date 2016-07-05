package org.trinity.yqyl.repository.business.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.TableGenerator;

import org.trinity.repository.entity.AbstractAuditableEntity;

/**
 * The persistent class for the account database table.
 * 
 */
@Entity
@NamedQuery(name = "Account.findAll", query = "SELECT a FROM Account a")
public class Account extends AbstractAuditableEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "Account_PK_IdGenerator")
	@TableGenerator(name = "Account_PK_IdGenerator", table = "id_table", pkColumnName = "type", pkColumnValue = "Account_PK", valueColumnName = "value", initialValue = 1, allocationSize = 1)
	private Long id;

	private String status;

	// bi-directional many-to-one association to User
	@ManyToOne
	private User user;

	// bi-directional many-to-one association to AccountBalance
	@OneToMany(mappedBy = "account")
	private List<AccountBalance> accountBalances;

	public Account() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<AccountBalance> getAccountBalances() {
		return this.accountBalances;
	}

	public void setAccountBalances(List<AccountBalance> accountBalances) {
		this.accountBalances = accountBalances;
	}

	public AccountBalance addAccountBalance(AccountBalance accountBalance) {
		getAccountBalances().add(accountBalance);
		accountBalance.setAccount(this);

		return accountBalance;
	}

	public AccountBalance removeAccountBalance(AccountBalance accountBalance) {
		getAccountBalances().remove(accountBalance);
		accountBalance.setAccount(null);

		return accountBalance;
	}

}
