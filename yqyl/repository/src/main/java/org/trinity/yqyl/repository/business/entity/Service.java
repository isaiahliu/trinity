package org.trinity.yqyl.repository.business.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.TableGenerator;

import org.trinity.repository.entity.AbstractAuditableEntity;
import org.trinity.yqyl.common.message.lookup.ServiceStatus;

/**
 * The persistent class for the service database table.
 * 
 */
@Entity
@NamedQuery(name = "Service.findAll", query = "SELECT s FROM Service s")
public class Service extends AbstractAuditableEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "Service_PK_IdGenerator")
	@TableGenerator(name = "Service_PK_IdGenerator", table = "id_table", pkColumnName = "type", pkColumnValue = "Service_PK", valueColumnName = "value", initialValue = 1, allocationSize = 1)
	private Long id;

	private String uuid;

	private String description;

	private String name;

	private Double price;

	private ServiceStatus status;

	// bi-directional many-to-one association to Favorite
	@OneToMany(mappedBy = "service")
	private List<Favorite> favorites;

	// bi-directional many-to-many association to Order
	@ManyToMany(mappedBy = "services")
	private List<Order> orders;

	@Column(name = "revised_version")
	private Integer revisedVersion;

	public Service() {
	}

	public Favorite addFavorite(final Favorite favorite) {
		getFavorites().add(favorite);
		favorite.setService(this);

		return favorite;
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

	public String getName() {
		return this.name;
	}

	public List<Order> getOrders() {
		return this.orders;
	}

	public Double getPrice() {
		return this.price;
	}

	public Integer getRevisedVersion() {
		return revisedVersion;
	}

	public ServiceStatus getStatus() {
		return this.status;
	}

	public String getUuid() {
		return uuid;
	}

	public Favorite removeFavorite(final Favorite favorite) {
		getFavorites().remove(favorite);
		favorite.setService(null);

		return favorite;
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

	public void setName(final String name) {
		this.name = name;
	}

	public void setOrders(final List<Order> orders) {
		this.orders = orders;
	}

	public void setPrice(final Double price) {
		this.price = price;
	}

	public void setRevisedVersion(final Integer revisedVersion) {
		this.revisedVersion = revisedVersion;
	}

	public void setStatus(final ServiceStatus status) {
		this.status = status;
	}

	public void setUuid(final String uuid) {
		this.uuid = uuid;
	}

}
