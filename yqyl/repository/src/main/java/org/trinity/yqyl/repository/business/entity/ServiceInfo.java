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

	private String description;

	private String name;

	private Double price;

	@Column(name = "image", insertable = true, updatable = true)
	private String image;

	private ServiceStatus status;

	// bi-directional many-to-one association to Favorite
	@OneToMany(mappedBy = "service")
	private List<Favorite> favorites;

	// bi-directional many-to-one association to ServiceCategory
	@ManyToOne
	@JoinColumn(name = "service_category_id")
	private ServiceCategory serviceCategory;

	// bi-directional many-to-one association to ServiceSupplierClient
	@ManyToOne
	@JoinColumn(name = "service_supplier_client_id")
	private ServiceSupplierClient serviceSupplierClient;

	// bi-directional many-to-one association to ServiceSubOrder
	@OneToMany(mappedBy = "serviceInfo")
	private List<ServiceSubOrder> serviceSubOrders;

	public ServiceInfo() {
	}

	public Favorite addFavorite(final Favorite favorite) {
		getFavorites().add(favorite);
		favorite.setService(this);

		return favorite;
	}

	public ServiceSubOrder addServiceSubOrder(final ServiceSubOrder serviceSubOrder) {
		getServiceSubOrders().add(serviceSubOrder);
		serviceSubOrder.setServiceInfo(this);

		return serviceSubOrder;
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

	public Double getPrice() {
		return this.price;
	}

	public ServiceCategory getServiceCategory() {
		return this.serviceCategory;
	}

	public List<ServiceSubOrder> getServiceSubOrders() {
		return this.serviceSubOrders;
	}

	public ServiceSupplierClient getServiceSupplierClient() {
		return this.serviceSupplierClient;
	}

	public ServiceStatus getStatus() {
		return this.status;
	}

	public Favorite removeFavorite(final Favorite favorite) {
		getFavorites().remove(favorite);
		favorite.setService(null);

		return favorite;
	}

	public ServiceSubOrder removeServiceSubOrder(final ServiceSubOrder serviceSubOrder) {
		getServiceSubOrders().remove(serviceSubOrder);
		serviceSubOrder.setServiceInfo(null);

		return serviceSubOrder;
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

	public void setPrice(final Double price) {
		this.price = price;
	}

	public void setServiceCategory(final ServiceCategory serviceCategory) {
		this.serviceCategory = serviceCategory;
	}

	public void setServiceSubOrders(final List<ServiceSubOrder> serviceSubOrders) {
		this.serviceSubOrders = serviceSubOrders;
	}

	public void setServiceSupplierClient(final ServiceSupplierClient serviceSupplierClient) {
		this.serviceSupplierClient = serviceSupplierClient;
	}

	public void setStatus(final ServiceStatus status) {
		this.status = status;
	}

}
