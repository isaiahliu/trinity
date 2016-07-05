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
	@JoinTable(name = "user_user_group", joinColumns = {
			@JoinColumn(name = "user_id") }, inverseJoinColumns = {
					@JoinColumn(name = "user_group_id") })
	private List<UserGroup> userGroups;

	public User() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserStatus getStatus() {
		return this.status;
	}

	public void setStatus(UserStatus status) {
		this.status = status;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<Account> getAccounts() {
		return this.accounts;
	}

	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}

	public Account addAccount(Account account) {
		getAccounts().add(account);
		account.setUser(this);

		return account;
	}

	public Account removeAccount(Account account) {
		getAccounts().remove(account);
		account.setUser(null);

		return account;
	}

	public List<AllowanceSupplierClient> getAllowanceSupplierClients() {
		return this.allowanceSupplierClients;
	}

	public void setAllowanceSupplierClients(
			List<AllowanceSupplierClient> allowanceSupplierClients) {
		this.allowanceSupplierClients = allowanceSupplierClients;
	}

	public AllowanceSupplierClient addAllowanceSupplierClient(
			AllowanceSupplierClient allowanceSupplierClient) {
		getAllowanceSupplierClients().add(allowanceSupplierClient);
		allowanceSupplierClient.setUser(this);

		return allowanceSupplierClient;
	}

	public AllowanceSupplierClient removeAllowanceSupplierClient(
			AllowanceSupplierClient allowanceSupplierClient) {
		getAllowanceSupplierClients().remove(allowanceSupplierClient);
		allowanceSupplierClient.setUser(null);

		return allowanceSupplierClient;
	}

	public List<ServiceReceiverClient> getServiceReceiverClients() {
		return this.serviceReceiverClients;
	}

	public void setServiceReceiverClients(List<ServiceReceiverClient> serviceReceiverClients) {
		this.serviceReceiverClients = serviceReceiverClients;
	}

	public ServiceReceiverClient addServiceReceiverClient(
			ServiceReceiverClient serviceReceiverClient) {
		getServiceReceiverClients().add(serviceReceiverClient);
		serviceReceiverClient.setUser(this);

		return serviceReceiverClient;
	}

	public ServiceReceiverClient removeServiceReceiverClient(
			ServiceReceiverClient serviceReceiverClient) {
		getServiceReceiverClients().remove(serviceReceiverClient);
		serviceReceiverClient.setUser(null);

		return serviceReceiverClient;
	}

	public List<ServiceSupplierClient> getServiceSupplierClients() {
		return this.serviceSupplierClients;
	}

	public void setServiceSupplierClients(List<ServiceSupplierClient> serviceSupplierClients) {
		this.serviceSupplierClients = serviceSupplierClients;
	}

	public ServiceSupplierClient addServiceSupplierClient(
			ServiceSupplierClient serviceSupplierClient) {
		getServiceSupplierClients().add(serviceSupplierClient);
		serviceSupplierClient.setUser(this);

		return serviceSupplierClient;
	}

	public ServiceSupplierClient removeServiceSupplierClient(
			ServiceSupplierClient serviceSupplierClient) {
		getServiceSupplierClients().remove(serviceSupplierClient);
		serviceSupplierClient.setUser(null);

		return serviceSupplierClient;
	}

	public List<Token> getTokens() {
		return this.tokens;
	}

	public void setTokens(List<Token> tokens) {
		this.tokens = tokens;
	}

	public Token addToken(Token token) {
		getTokens().add(token);
		token.setUser(this);

		return token;
	}

	public Token removeToken(Token token) {
		getTokens().remove(token);
		token.setUser(null);

		return token;
	}

	public List<UserGroup> getUserGroups() {
		return this.userGroups;
	}

	public void setUserGroups(List<UserGroup> userGroups) {
		this.userGroups = userGroups;
	}

}
