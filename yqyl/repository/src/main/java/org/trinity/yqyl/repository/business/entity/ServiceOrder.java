//Cleaned
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
import org.trinity.yqyl.common.message.lookup.OrderStatus;

/**
 * The persistent class for the order database table.
 *
 */
@Entity
@Table(name = "service_order")
@NamedQuery(name = "ServiceOrder.findAll", query = "SELECT o FROM ServiceOrder o")
public class ServiceOrder extends AbstractAuditableEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "ServiceOrder_PK_IdGenerator")
	@TableGenerator(name = "ServiceOrder_PK_IdGenerator", table = "id_table", pkColumnName = "type", pkColumnValue = "ServiceOrder_PK", valueColumnName = "value", initialValue = 1, allocationSize = 1)
	private Long id;

	private Double price;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "service_time")
	private Date serviceTime;

	private OrderStatus status;

	// bi-directional many-to-one association to ServiceSubOrder
	@OneToMany(mappedBy = "serviceOrder")
	private List<ServiceSubOrder> serviceSubOrders;
	private Integer score;

	private String appraise;

	// bi-directional many-to-one association to User
	@ManyToOne
	private User user;

	public ServiceOrder() {
	}

	public ServiceSubOrder addServiceSubOrder(final ServiceSubOrder serviceSubOrder) {
		getServiceSubOrders().add(serviceSubOrder);
		serviceSubOrder.setServiceOrder(this);

		return serviceSubOrder;
	}

	public String getAppraise() {
		return appraise;
	}

	public Long getId() {
		return this.id;
	}

	public Double getPrice() {
		return this.price;
	}

	public Integer getScore() {
		return score;
	}

	public List<ServiceSubOrder> getServiceSubOrders() {
		return this.serviceSubOrders;
	}

	public Date getServiceTime() {
		return serviceTime;
	}

	public OrderStatus getStatus() {
		return this.status;
	}

	public User getUser() {
		return this.user;
	}

	public ServiceSubOrder removeServiceSubOrder(final ServiceSubOrder serviceSubOrder) {
		getServiceSubOrders().remove(serviceSubOrder);
		serviceSubOrder.setServiceOrder(null);

		return serviceSubOrder;
	}

	public void setAppraise(final String appraise) {
		this.appraise = appraise;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public void setPrice(final Double price) {
		this.price = price;
	}

	public void setScore(final Integer score) {
		this.score = score;
	}

	public void setServiceSubOrders(final List<ServiceSubOrder> serviceSubOrders) {
		this.serviceSubOrders = serviceSubOrders;
	}

	public void setServiceTime(final Date serviceTime) {
		this.serviceTime = serviceTime;
	}

	public void setStatus(final OrderStatus status) {
		this.status = status;
	}

	public void setUser(final User user) {
		this.user = user;
	}

}
