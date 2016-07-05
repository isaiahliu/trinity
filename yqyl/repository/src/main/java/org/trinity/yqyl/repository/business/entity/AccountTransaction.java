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

    private String category;

    private String status;

    // bi-directional many-to-one association to AccountPosting
    @OneToMany(mappedBy = "accountTransaction")
    private List<AccountPosting> accountPostings;

    public AccountTransaction() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategory() {
        return this.category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<AccountPosting> getAccountPostings() {
        return this.accountPostings;
    }

    public void setAccountPostings(List<AccountPosting> accountPostings) {
        this.accountPostings = accountPostings;
    }

    public AccountPosting addAccountPosting(AccountPosting accountPosting) {
        getAccountPostings().add(accountPosting);
        accountPosting.setAccountTransaction(this);

        return accountPosting;
    }

    public AccountPosting removeAccountPosting(AccountPosting accountPosting) {
        getAccountPostings().remove(accountPosting);
        accountPosting.setAccountTransaction(null);

        return accountPosting;
    }

}
