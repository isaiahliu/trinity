//Cleaned
package org.trinity.yqyl.repository.business.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import org.trinity.repository.entity.AbstractAuditableEntity;
import org.trinity.yqyl.common.message.lookup.RecordStatus;
import org.trinity.yqyl.common.message.lookup.AccountCategory;

/**
 * The persistent class for the account_transaction database table.
 * 
 */
@Entity
@Table(name = "account_transaction")
@NamedQuery(name = "AccountTransaction.findAll", query = "SELECT a FROM AccountTransaction a")
public class AccountTransaction extends AbstractAuditableEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "AccountTransaction_PK_IdGenerator")
	@TableGenerator(name = "AccountTransaction_PK_IdGenerator", table = "id_table", pkColumnName = "type", pkColumnValue = "AccountTransaction_PK", valueColumnName = "value", initialValue = 1, allocationSize = 1)
	private Long id;

	private AccountCategory category;

	private RecordStatus status;

	// bi-directional many-to-one association to AccountPosting
	@OneToMany(mappedBy = "accountTransaction")
	private List<AccountPosting> accountPostings;

	public AccountTransaction() {
	}

	public AccountPosting addAccountPosting(final AccountPosting accountPosting) {
		getAccountPostings().add(accountPosting);
		accountPosting.setAccountTransaction(this);

		return accountPosting;
	}

	public List<AccountPosting> getAccountPostings() {
		return this.accountPostings;
	}

	public AccountCategory getCategory() {
		return this.category;
	}

	public Long getId() {
		return this.id;
	}

	public RecordStatus getStatus() {
		return this.status;
	}

	public AccountPosting removeAccountPosting(final AccountPosting accountPosting) {
		getAccountPostings().remove(accountPosting);
		accountPosting.setAccountTransaction(null);

		return accountPosting;
	}

	public void setAccountPostings(final List<AccountPosting> accountPostings) {
		this.accountPostings = accountPostings;
	}

	public void setCategory(final AccountCategory category) {
		this.category = category;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public void setStatus(final RecordStatus status) {
		this.status = status;
	}

}
