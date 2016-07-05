package org.trinity.yqyl.repository.business.entity;

import java.io.Serializable;
import java.math.BigDecimal;

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

	private String status;

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

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getAmount() {
		return this.amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public AccountBalance getAccountBalance() {
		return this.accountBalance;
	}

	public void setAccountBalance(AccountBalance accountBalance) {
		this.accountBalance = accountBalance;
	}

	public AccountTransaction getAccountTransaction() {
		return this.accountTransaction;
	}

	public void setAccountTransaction(AccountTransaction accountTransaction) {
		this.accountTransaction = accountTransaction;
	}

}
