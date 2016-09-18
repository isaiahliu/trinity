package org.trinity.yqyl.repository.business.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.trinity.repository.entity.AbstractAuditableEntity;
import org.trinity.yqyl.common.message.lookup.Gender;
import org.trinity.yqyl.common.message.lookup.ServiceReceiverClientStatus;

/**
 * The persistent class for the service_receiver_client database table.
 *
 */
@Entity
@Table(name = "service_receiver_client")
@NamedQuery(name = "ServiceReceiverClient.findAll", query = "SELECT s FROM ServiceReceiverClient s")
public class ServiceReceiverClient extends AbstractAuditableEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "ServiceReceiverClient_PK_IdGenerator")
	@TableGenerator(name = "ServiceReceiverClient_PK_IdGenerator", table = "id_table", pkColumnName = "type", pkColumnValue = "ServiceReceiverClient_PK", valueColumnName = "value", initialValue = 1, allocationSize = 1)
	private Long id;

	private String address;

	@Column(name = "health_status")
	private String healthStatus;

	@Column(name = "cellphone_no")
	private String cellphoneNo;

	@Temporal(TemporalType.DATE)
	private Date dob;

	private String email;

	private Gender gender;

	@Column(name = "homephone_no")
	private String homephoneNo;

	@Column(name = "identity_card")
	private String identityCard;

	private String name;

	@Column(name = "yijin_code")
	private String yijinCode;

	private ServiceReceiverClientStatus status;

	// bi-directional many-to-one association to Favorite
	@OneToMany(mappedBy = "serviceReceiverClient")
	private List<Favorite> favorites;

	// bi-directional many-to-one association to Order
	@OneToMany(mappedBy = "serviceReceiverClient")
	private List<Order> orders;

	// bi-directional many-to-one association to User
	@ManyToOne
	private User user;

	public ServiceReceiverClient() {
	}

	public Favorite addFavorite(final Favorite favorite) {
		getFavorites().add(favorite);
		favorite.setServiceReceiverClient(this);

		return favorite;
	}

	public Order addOrder(final Order order) {
		getOrders().add(order);
		order.setServiceReceiverClient(this);

		return order;
	}

	public String getAddress() {
		return address;
	}

	public String getCellphoneNo() {
		return cellphoneNo;
	}

	public Date getDob() {
		return dob;
	}

	public String getEmail() {
		return email;
	}

	public List<Favorite> getFavorites() {
		return this.favorites;
	}

	public Gender getGender() {
		return gender;
	}

	public String getHealthStatus() {
		return healthStatus;
	}

	public String getHomephoneNo() {
		return homephoneNo;
	}

	public Long getId() {
		return this.id;
	}

	public String getIdentityCard() {
		return identityCard;
	}

	public String getName() {
		return name;
	}

	public List<Order> getOrders() {
		return this.orders;
	}

	public ServiceReceiverClientStatus getStatus() {
		return this.status;
	}

	public User getUser() {
		return this.user;
	}

	public String getYijinCode() {
		return yijinCode;
	}

	public Favorite removeFavorite(final Favorite favorite) {
		getFavorites().remove(favorite);
		favorite.setServiceReceiverClient(null);

		return favorite;
	}

	public Order removeOrder(final Order order) {
		getOrders().remove(order);
		order.setServiceReceiverClient(null);

		return order;
	}

	public void setAddress(final String address) {
		this.address = address;
	}

	public void setCellphoneNo(final String cellphoneNo) {
		this.cellphoneNo = cellphoneNo;
	}

	public void setDob(final Date dob) {
		this.dob = dob;
	}

	public void setEmail(final String email) {
		this.email = email;
	}

	public void setFavorites(final List<Favorite> favorites) {
		this.favorites = favorites;
	}

	public void setGender(final Gender gender) {
		this.gender = gender;
	}

	public void setHealthStatus(final String healthStatus) {
		this.healthStatus = healthStatus;
	}

	public void setHomephoneNo(final String homephoneNo) {
		this.homephoneNo = homephoneNo;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public void setIdentityCard(final String identityCard) {
		this.identityCard = identityCard;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public void setOrders(final List<Order> orders) {
		this.orders = orders;
	}

	public void setStatus(final ServiceReceiverClientStatus status) {
		this.status = status;
	}

	public void setUser(final User user) {
		this.user = user;
	}

	public void setYijinCode(final String yijinCode) {
		this.yijinCode = yijinCode;
	}

}
