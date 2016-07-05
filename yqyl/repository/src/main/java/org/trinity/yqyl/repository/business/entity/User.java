package org.trinity.yqyl.repository.business.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.TableGenerator;

import org.trinity.repository.entity.AbstractAuditableEntity;
import org.trinity.yqyl.common.message.lookup.UserStatus;

/**
 * The persistent class for the user database table.
 *
 */
@Entity
@NamedQuery(name = "User.findAll", query = "SELECT u FROM User u")
public class User extends AbstractAuditableEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "User_PK_IdGenerator")
    @TableGenerator(name = "User_PK_IdGenerator", table = "id_table", pkColumnName = "type", pkColumnValue = "User_PK", valueColumnName = "value", initialValue = 1, allocationSize = 1)
    private Long id;

    private String password;

    private UserStatus status;

    private String username;

    // bi-directional many-to-one association to Account
    @OneToMany(mappedBy = "user")
    private List<Account> accounts;

    // bi-directional many-to-one association to AllowanceSupplierClient
    @OneToMany(mappedBy = "user")
    private List<AllowanceSupplierClient> allowanceSupplierClients;

    // bi-directional many-to-one association to ServiceReceiverClient
    @OneToMany(mappedBy = "user")
    private List<ServiceReceiverClient> serviceReceiverClients;

    // bi-directional many-to-one association to ServiceSupplierClient
    @OneToMany(mappedBy = "user")
    private List<ServiceSupplierClient> serviceSupplierClients;

    // bi-directional many-to-one association to Token
    @OneToMany(mappedBy = "user")
    private List<Token> tokens;

    // bi-directional many-to-many association to UserGroup
    @ManyToMany
    @JoinTable(name = "user_user_group", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = {
            @JoinColumn(name = "user_group_id") })
    private List<UserGroup> userGroups;

    public User() {
    }

    public Account addAccount(final Account account) {
        getAccounts().add(account);
        account.setUser(this);

        return account;
    }

    public AllowanceSupplierClient addAllowanceSupplierClient(final AllowanceSupplierClient allowanceSupplierClient) {
        getAllowanceSupplierClients().add(allowanceSupplierClient);
        allowanceSupplierClient.setUser(this);

        return allowanceSupplierClient;
    }

    public ServiceReceiverClient addServiceReceiverClient(final ServiceReceiverClient serviceReceiverClient) {
        getServiceReceiverClients().add(serviceReceiverClient);
        serviceReceiverClient.setUser(this);

        return serviceReceiverClient;
    }

    public ServiceSupplierClient addServiceSupplierClient(final ServiceSupplierClient serviceSupplierClient) {
        getServiceSupplierClients().add(serviceSupplierClient);
        serviceSupplierClient.setUser(this);

        return serviceSupplierClient;
    }

    public Token addToken(final Token token) {
        getTokens().add(token);
        token.setUser(this);

        return token;
    }

    public List<Account> getAccounts() {
        return this.accounts;
    }

    public List<AllowanceSupplierClient> getAllowanceSupplierClients() {
        return this.allowanceSupplierClients;
    }

    public Long getId() {
        return this.id;
    }

    public String getPassword() {
        return this.password;
    }

    public List<ServiceReceiverClient> getServiceReceiverClients() {
        return this.serviceReceiverClients;
    }

    public List<ServiceSupplierClient> getServiceSupplierClients() {
        return this.serviceSupplierClients;
    }

    public UserStatus getStatus() {
        return this.status;
    }

    public List<Token> getTokens() {
        return this.tokens;
    }

    public List<UserGroup> getUserGroups() {
        return this.userGroups;
    }

    public String getUsername() {
        return this.username;
    }

    public Account removeAccount(final Account account) {
        getAccounts().remove(account);
        account.setUser(null);

        return account;
    }

    public AllowanceSupplierClient removeAllowanceSupplierClient(
            final AllowanceSupplierClient allowanceSupplierClient) {
        getAllowanceSupplierClients().remove(allowanceSupplierClient);
        allowanceSupplierClient.setUser(null);

        return allowanceSupplierClient;
    }

    public ServiceReceiverClient removeServiceReceiverClient(final ServiceReceiverClient serviceReceiverClient) {
        getServiceReceiverClients().remove(serviceReceiverClient);
        serviceReceiverClient.setUser(null);

        return serviceReceiverClient;
    }

    public ServiceSupplierClient removeServiceSupplierClient(final ServiceSupplierClient serviceSupplierClient) {
        getServiceSupplierClients().remove(serviceSupplierClient);
        serviceSupplierClient.setUser(null);

        return serviceSupplierClient;
    }

    public Token removeToken(final Token token) {
        getTokens().remove(token);
        token.setUser(null);

        return token;
    }

    public void setAccounts(final List<Account> accounts) {
        this.accounts = accounts;
    }

    public void setAllowanceSupplierClients(final List<AllowanceSupplierClient> allowanceSupplierClients) {
        this.allowanceSupplierClients = allowanceSupplierClients;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public void setServiceReceiverClients(final List<ServiceReceiverClient> serviceReceiverClients) {
        this.serviceReceiverClients = serviceReceiverClients;
    }

    public void setServiceSupplierClients(final List<ServiceSupplierClient> serviceSupplierClients) {
        this.serviceSupplierClients = serviceSupplierClients;
    }

    public void setStatus(final UserStatus status) {
        this.status = status;
    }

    public void setTokens(final List<Token> tokens) {
        this.tokens = tokens;
    }

    public void setUserGroups(final List<UserGroup> userGroups) {
        this.userGroups = userGroups;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

}
