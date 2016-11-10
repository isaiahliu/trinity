//Cleaned
package org.trinity.yqyl.repository.business.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.TableGenerator;

import org.trinity.repository.entity.AbstractAuditableEntity;
import org.trinity.yqyl.common.message.lookup.AccessRight;
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
    @OneToOne(mappedBy = "user")
    private Account account;

    // bi-directional many-to-one association to AllowanceSupplierClient
    @OneToMany(mappedBy = "user")
    private List<AllowanceSupplierClient> allowanceSupplierClients;

    // bi-directional many-to-one association to OperatorClient
    @OneToOne(mappedBy = "user")
    private OperatorClient operatorClient;

    // bi-directional many-to-one association to ServiceReceiverClient
    @OneToMany(mappedBy = "user")
    private List<ServiceReceiverClient> serviceReceiverClients;

    // bi-directional one-to-one association to ServiceSupplierClient
    @OneToOne(mappedBy = "user")
    private ServiceSupplierClient serviceSupplierClient;

    // bi-directional many-to-one association to Token
    @OneToMany(mappedBy = "user")
    private List<Token> tokens;

    // bi-directional many-to-many association to UserGroup
    @ElementCollection
    @CollectionTable(name = "user_accessright", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "accessright")
    private List<AccessRight> accessrights;

    // bi-directional many-to-one association to Order
    @OneToMany(mappedBy = "user")
    private List<ServiceOrder> orders;

    // bi-directional one-to-one association to UserRealname
    @OneToOne(mappedBy = "user")
    private UserRealname realname;

    // bi-directional many-to-one association to Yiquan
    @ManyToOne
    @JoinColumn(name = "yiquan_code", referencedColumnName = "code")
    private Yiquan yiquan;

    @Column(name = "yiquan_code", insertable = false, updatable = false)
    private String yiquanCode;

    // bi-directional many-to-one association to ServiceOrderRequirement
    @OneToMany(mappedBy = "user")
    private List<ServiceOrderRequirement> serviceOrderRequirements;

    public User() {
    }

    public AllowanceSupplierClient addAllowanceSupplierClient(final AllowanceSupplierClient allowanceSupplierClient) {
        getAllowanceSupplierClients().add(allowanceSupplierClient);
        allowanceSupplierClient.setUser(this);

        return allowanceSupplierClient;
    }

    public ServiceOrder addOrder(final ServiceOrder order) {
        getOrders().add(order);
        order.setUser(this);

        return order;
    }

    public ServiceOrderRequirement addServiceOrderRequirement(final ServiceOrderRequirement serviceOrderRequirement) {
        getServiceOrderRequirements().add(serviceOrderRequirement);
        serviceOrderRequirement.setUser(this);

        return serviceOrderRequirement;
    }

    public ServiceReceiverClient addServiceReceiverClient(final ServiceReceiverClient serviceReceiverClient) {
        getServiceReceiverClients().add(serviceReceiverClient);
        serviceReceiverClient.setUser(this);

        return serviceReceiverClient;
    }

    public Token addToken(final Token token) {
        getTokens().add(token);
        token.setUser(this);

        return token;
    }

    public List<AccessRight> getAccessrights() {
        return this.accessrights;
    }

    public Account getAccount() {
        return account;
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

    public OperatorClient getOperatorClient() {
        return operatorClient;
    }

    public List<ServiceOrder> getOrders() {
        return this.orders;
    }

    public String getPassword() {
        return this.password;
    }

    public UserRealname getRealname() {
        return this.realname;
    }

    public List<ServiceOrderRequirement> getServiceOrderRequirements() {
        return this.serviceOrderRequirements;
    }

    public List<ServiceReceiverClient> getServiceReceiverClients() {
        return this.serviceReceiverClients;
    }

    public ServiceSupplierClient getServiceSupplierClient() {
        return this.serviceSupplierClient;
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

    public Yiquan getYiquan() {
        return this.yiquan;
    }

    public String getYiquanCode() {
        return yiquanCode;
    }

    public AllowanceSupplierClient removeAllowanceSupplierClient(final AllowanceSupplierClient allowanceSupplierClient) {
        getAllowanceSupplierClients().remove(allowanceSupplierClient);
        allowanceSupplierClient.setUser(null);

        return allowanceSupplierClient;
    }

    public ServiceOrder removeOrder(final ServiceOrder order) {
        getOrders().remove(order);
        order.setUser(null);

        return order;
    }

    public ServiceOrderRequirement removeServiceOrderRequirement(final ServiceOrderRequirement serviceOrderRequirement) {
        getServiceOrderRequirements().remove(serviceOrderRequirement);
        serviceOrderRequirement.setUser(null);

        return serviceOrderRequirement;
    }

    public ServiceReceiverClient removeServiceReceiverClient(final ServiceReceiverClient serviceReceiverClient) {
        getServiceReceiverClients().remove(serviceReceiverClient);
        serviceReceiverClient.setUser(null);

        return serviceReceiverClient;
    }

    public Token removeToken(final Token token) {
        getTokens().remove(token);
        token.setUser(null);

        return token;
    }

    public void setAccessrights(final List<AccessRight> accessrights) {
        this.accessrights = accessrights;
    }

    public void setAccount(final Account account) {
        this.account = account;
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

    public void setOperatorClient(final OperatorClient operatorClient) {
        this.operatorClient = operatorClient;
    }

    public void setOrders(final List<ServiceOrder> orders) {
        this.orders = orders;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public void setRealname(final UserRealname realname) {
        this.realname = realname;
    }

    public void setServiceOrderRequirements(final List<ServiceOrderRequirement> serviceOrderRequirements) {
        this.serviceOrderRequirements = serviceOrderRequirements;
    }

    public void setServiceReceiverClients(final List<ServiceReceiverClient> serviceReceiverClients) {
        this.serviceReceiverClients = serviceReceiverClients;
    }

    public void setServiceSupplierClient(final ServiceSupplierClient serviceSupplierClient) {
        this.serviceSupplierClient = serviceSupplierClient;
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

    public void setYiquan(final Yiquan yiquan) {
        this.yiquan = yiquan;
    }

    public void setYiquanCode(final String yiquanCode) {
        this.yiquanCode = yiquanCode;
    }

}
