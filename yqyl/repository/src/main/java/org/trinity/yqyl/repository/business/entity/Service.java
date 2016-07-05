package org.trinity.yqyl.repository.business.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.TableGenerator;

import org.trinity.repository.entity.AbstractAuditableEntity;

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

	private String description;

	private String name;

	private BigDecimal price;

	private String status;

	// bi-directional many-to-many association to Order
	@ManyToMany(mappedBy = "services")
	private List<Order> orders;

	public Service() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getPrice() {
		return this.price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<Order> getOrders() {
		return this.orders;
	}

	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}

}
