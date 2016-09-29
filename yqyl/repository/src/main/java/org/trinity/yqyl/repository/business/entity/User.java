//Cleaned
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

    private String cellphone;

    private String email;

    // bi-directional many-to-one association to Account
    @OneToMany(mappedBy = "user")
    private List<Account> accounts;

    // bi-directional many-to-one association to AllowanceSupplierClient
    @OneToMany(mappedBy = "user")
    private List<AllowanceSupplierClient> allowanceSupplierClients;

    // bi-directional many-to-one association to OperatorClient
    @OneToMany(mappedBy = "user")
    private List<OperatorClient> operatorClients;

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
    @JoinTable(name = "user_role", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = { @JoinColumn(name = "role_id") })
    private List<Role> roles;

    // bi-directional many-to-one association to Order
    @OneToMany(mappedBy = "user")
    private List<ServiceOrder> orders;

    // bi-directional many-to-one association to Service
    @OneToMany(mappedBy = "user")
    private List<ServiceInfo> services;

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

    public OperatorClient addOperatorClient(final OperatorClient operatorClient) {
        getOperatorClients().add(operatorClient);
        operatorClient.setUser(this);

        return operatorClient;
    }

    public ServiceOrder addOrder(final ServiceOrder order) {
        getOrders().add(order);
        order.setUser(this);

        return order;
    }

    public ServiceInfo addService(final ServiceInfo service) {
        getServices().add(service);
        service.setUser(this);

        return service;
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

    public String getCellphone() {
        return cellphone;
    }

    public String getEmail() {
        return email;
    }

    public Long getId() {
        return this.id;
    }

    public List<OperatorClient> getOperatorClients() {
        return this.operatorClients;
    }

    public List<ServiceOrder> getOrders() {
        return this.orders;
    }

    public String getPassword() {
        return this.password;
    }

    public List<Role> getRoles() {
        return this.roles;
    }

    public List<ServiceReceiverClient> getServiceReceiverClients() {
        return this.serviceReceiverClients;
    }

    public List<ServiceInfo> getServices() {
        return this.services;
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

    public String getUsername() {
        return this.username;
    }

    public Account removeAccount(final Account account) {
        getAccounts().remove(account);
        account.setUser(null);

        return account;
    }

    public AllowanceSupplierClient removeAllowanceSupplierClient(final AllowanceSupplierClient allowanceSupplierClient) {
        getAllowanceSupplierClients().remove(allowanceSupplierClient);
        allowanceSupplierClient.setUser(null);

        return allowanceSupplierClient;
    }

    public OperatorClient removeOperatorClient(final OperatorClient operatorClient) {
        getOperatorClients().remove(operatorClient);
        operatorClient.setUser(null);

        return operatorClient;
    }

    public ServiceOrder removeOrder(final ServiceOrder order) {
        getOrders().remove(order);
        order.setUser(null);

        return order;
    }

    public ServiceInfo removeService(final ServiceInfo service) {
        getServices().remove(service);
        service.setUser(null);

        return service;
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

    public void setCellphone(final String cellphone) {
        this.cellphone = cellphone;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public void setOperatorClients(final List<OperatorClient> operatorClients) {
        this.operatorClients = operatorClients;
    }

    public void setOrders(final List<ServiceOrder> orders) {
        this.orders = orders;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public void setRoles(final List<Role> roles) {
        this.roles = roles;
    }

    public void setServiceReceiverClients(final List<ServiceReceiverClient> serviceReceiverClients) {
        this.serviceReceiverClients = serviceReceiverClients;
    }

    public void setServices(final List<ServiceInfo> services) {
        this.services = services;
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

    public void setUsername(final String username) {
        this.username = username;
    }

}
