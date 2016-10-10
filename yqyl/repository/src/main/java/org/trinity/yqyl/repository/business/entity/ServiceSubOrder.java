//Cleaned
package org.trinity.yqyl.repository.business.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.trinity.repository.entity.AbstractAuditableEntity;
import org.trinity.yqyl.common.message.lookup.RecordStatus;

/**
 * The persistent class for the service_sub_order database table.
 *
 */
@Entity
@Table(name = "service_sub_order")
@NamedQuery(name = "ServiceSubOrder.findAll", query = "SELECT s FROM ServiceSubOrder s")
public class ServiceSubOrder extends AbstractAuditableEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "ServiceSubOrder_PK_IdGenerator")
	@TableGenerator(name = "ServiceSubOrder_PK_IdGenerator", table = "id_table", pkColumnName = "type", pkColumnValue = "ServiceSubOrder_PK", valueColumnName = "value", initialValue = 1, allocationSize = 1)
	private Long id;

	private Double price;

	private String phone;

	private String address;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "service_time")
	private Date serviceTime;

	private RecordStatus status;

	// bi-directional many-to-one association to ServiceOrder
	@ManyToOne
	@JoinColumn(name = "service_order_id")
	private ServiceOrder serviceOrder;

	// bi-directional many-to-one association to ServiceInfo
	@ManyToOne
	@JoinColumn(name = "service_id")
	private ServiceInfo serviceInfo;

	public ServiceSubOrder() {
	}

	public String getAddress() {
		return address;
	}

	public Long getId() {
		return this.id;
	}

	public String getPhone() {
		return phone;
	}

	public Double getPrice() {
		return this.price;
	}

	public ServiceInfo getServiceInfo() {
		return this.serviceInfo;
	}

	public ServiceOrder getServiceOrder() {
		return this.serviceOrder;
	}

	public Date getServiceTime() {
		return serviceTime;
	}

	public RecordStatus getStatus() {
		return this.status;
	}

	public void setAddress(final String address) {
		this.address = address;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public void setPhone(final String phone) {
		this.phone = phone;
	}

	public void setPrice(final Double price) {
		this.price = price;
	}

	public void setServiceInfo(final ServiceInfo serviceInfo) {
		this.serviceInfo = serviceInfo;
	}

	public void setServiceOrder(final ServiceOrder serviceOrder) {
		this.serviceOrder = serviceOrder;
	}

	public void setServiceTime(final Date serviceTime) {
		this.serviceTime = serviceTime;
	}

	public void setStatus(final RecordStatus status) {
		this.status = status;
	}

}
