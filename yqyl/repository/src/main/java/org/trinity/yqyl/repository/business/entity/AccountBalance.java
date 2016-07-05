package org.trinity.yqyl.repository.business.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import org.trinity.repository.entity.AbstractAuditableEntity;

/**
 * The persistent class for the account_balance database table.
 * 
 */
@Entity
@Table(name = "account_balance")
@NamedQuery(name = "AccountBalance.findAll", query = "SELECT a FROM AccountBalance a")
public class AccountBalance extends AbstractAuditableEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "AccountBalance_PK_IdGenerator")
    @TableGenerator(name = "AccountBalance_PK_IdGenerator", table = "id_table", pkColumnName = "type", pkColumnValue = "AccountBalance_PK", valueColumnName = "value", initialValue = 1, allocationSize = 1)
    private Long id;

    private BigDecimal amount;

    private String category;

    private String status;

    // bi-directional many-to-one association to Account
    @ManyToOne
    private Account account;

    // bi-directional many-to-one association to AccountPosting
    @OneToMany(mappedBy = "accountBalance")
    private List<AccountPosting> accountPostings;

    public AccountBalance() {
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

    public Account getAccount() {
        return this.account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public List<AccountPosting> getAccountPostings() {
        return this.accountPostings;
    }

    public void setAccountPostings(List<AccountPosting> accountPostings) {
        this.accountPostings = accountPostings;
    }

    public AccountPosting addAccountPosting(AccountPosting accountPosting) {
        getAccountPostings().add(accountPosting);
        accountPosting.setAccountBalance(this);

        return accountPosting;
    }

    public AccountPosting removeAccountPosting(AccountPosting accountPosting) {
        getAccountPostings().remove(accountPosting);
        accountPosting.setAccountBalance(null);

        return accountPosting;
    }

}
