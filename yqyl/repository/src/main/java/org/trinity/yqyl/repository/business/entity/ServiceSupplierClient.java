package org.trinity.yqyl.repository.business.entity;

import java.io.Serializable;
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
 * The persistent class for the service_supplier_client database table.
 * 
 */
@Entity
@Table(name = "service_supplier_client")
@NamedQuery(name = "ServiceSupplierClient.findAll", query = "SELECT s FROM ServiceSupplierClient s")
public class ServiceSupplierClient extends AbstractAuditableEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "ServiceSupplierClient_PK_IdGenerator")
	@TableGenerator(name = "ServiceSupplierClient_PK_IdGenerator", table = "id_table", pkColumnName = "type", pkColumnValue = "ServiceSupplierClient_PK", valueColumnName = "value", initialValue = 1, allocationSize = 1)
	private Long id;

	private String status;

	private String type;

	// bi-directional many-to-one association to Order
	@OneToMany(mappedBy = "serviceSupplierClient")
	private List<Order> orders;

	// bi-directional many-to-one association to User
	@ManyToOne
	private User user;

	public ServiceSupplierClient() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<Order> getOrders() {
		return this.orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

	public Order addOrder(Order order) {
		getOrders().add(order);
		order.setServiceSupplierClient(this);

		return order;
	}

	public Order removeOrder(Order order) {
		getOrders().remove(order);
		order.setServiceSupplierClient(null);

		return order;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
