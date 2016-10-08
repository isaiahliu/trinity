//Cleaned
package org.trinity.yqyl.repository.business.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import org.trinity.repository.entity.AbstractAuditableEntity;
import org.trinity.yqyl.common.message.lookup.ServiceStatus;

/**
 * The persistent class for the service database table.
 *
 */
@Entity
@Table(name = "service_info")
@NamedQuery(name = "ServiceInfo.findAll", query = "SELECT s FROM ServiceInfo s")
public class ServiceInfo extends AbstractAuditableEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "Service_PK_IdGenerator")
	@TableGenerator(name = "Service_PK_IdGenerator", table = "id_table", pkColumnName = "type", pkColumnValue = "Service_PK", valueColumnName = "value", initialValue = 1, allocationSize = 1)
	private Long id;

	private String uuid;

	private String description;

	private String name;

	private Double price;

	@Column(name = "image", insertable = true, updatable = true)
	private String image;

	private ServiceStatus status;

	// bi-directional many-to-one association to Favorite
	@OneToMany(mappedBy = "service")
	private List<Favorite> favorites;

	// bi-directional many-to-one association to Order
	@OneToMany(mappedBy = "service")
	private List<ServiceOrder> orders;

	// bi-directional many-to-one association to User
	@ManyToOne
	private User user;

	@Column(name = "revised_version")
	private Integer revisedVersion;

	// bi-directional many-to-many association to ServiceCategory
	@ManyToMany
	@JoinTable(name = "service_info_service_category", joinColumns = { @JoinColumn(name = "service_info_id") }, inverseJoinColumns = {
			@JoinColumn(name = "service_category_id") })
	private List<ServiceCategory> serviceCategories;

	public ServiceInfo() {
	}

	public Favorite addFavorite(final Favorite favorite) {
		getFavorites().add(favorite);
		favorite.setService(this);

		return favorite;
	}

	public ServiceOrder addOrder(final ServiceOrder order) {
		getOrders().add(order);
		order.setService(this);

		return order;
	}

	public String getDescription() {
		return this.description;
	}

	public List<Favorite> getFavorites() {
		return this.favorites;
	}

	public Long getId() {
		return this.id;
	}

	public String getImage() {
		return image;
	}

	public String getName() {
		return this.name;
	}

	public List<ServiceOrder> getOrders() {
		return this.orders;
	}

	public Double getPrice() {
		return this.price;
	}

	public Integer getRevisedVersion() {
		return revisedVersion;
	}

	public List<ServiceCategory> getServiceCategories() {
		return this.serviceCategories;
	}

	public ServiceStatus getStatus() {
		return this.status;
	}

	public User getUser() {
		return this.user;
	}

	public String getUuid() {
		return uuid;
	}

	public Favorite removeFavorite(final Favorite favorite) {
		getFavorites().remove(favorite);
		favorite.setService(null);

		return favorite;
	}

	public ServiceOrder removeOrder(final ServiceOrder order) {
		getOrders().remove(order);
		order.setService(null);

		return order;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public void setFavorites(final List<Favorite> favorites) {
		this.favorites = favorites;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public void setImage(final String image) {
		this.image = image;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public void setOrders(final List<ServiceOrder> orders) {
		this.orders = orders;
	}

	public void setPrice(final Double price) {
		this.price = price;
	}

	public void setRevisedVersion(final Integer revisedVersion) {
		this.revisedVersion = revisedVersion;
	}

	public void setServiceCategories(final List<ServiceCategory> serviceCategories) {
		this.serviceCategories = serviceCategories;
	}

	public void setStatus(final ServiceStatus status) {
		this.status = status;
	}

	public void setUser(final User user) {
		this.user = user;
	}

	public void setUuid(final String uuid) {
		this.uuid = uuid;
	}

}
