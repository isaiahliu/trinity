//Cleaned
package org.trinity.yqyl.repository.business.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.TableGenerator;

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
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "Account_PK_IdGenerator")
    @TableGenerator(name = "Account_PK_IdGenerator", table = "id_table", pkColumnName = "type", pkColumnValue = "Account_PK", valueColumnName = "value", initialValue = 1, allocationSize = 1)
    private Long id;

    private AccountStatus status;

    // bi-directional one-to-one association to User
    @OneToOne
    @JoinColumn(name = "service_receiver_client_id")
    private ServiceReceiverClient serviceReceiverClient;

    // bi-directional many-to-one association to AccountBalance
    @OneToMany(mappedBy = "account")
    private List<AccountBalance> balances;

    public Account() {
    }

    public AccountBalance addBalance(final AccountBalance balance) {
        getBalances().add(balance);
        balance.setAccount(this);

        return balance;
    }

    public List<AccountBalance> getBalances() {
        return this.balances;
    }

    public Long getId() {
        return id;
    }

    public ServiceReceiverClient getServiceReceiverClient() {
        return serviceReceiverClient;
    }

    public AccountStatus getStatus() {
        return this.status;
    }

    public AccountBalance removeBalance(final AccountBalance balance) {
        getBalances().remove(balance);
        balance.setAccount(null);

        return balance;
    }

    public void setBalances(final List<AccountBalance> balances) {
        this.balances = balances;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public void setServiceReceiverClient(final ServiceReceiverClient serviceReceiverClient) {
        this.serviceReceiverClient = serviceReceiverClient;
    }

    public void setStatus(final AccountStatus status) {
        this.status = status;
    }
}
