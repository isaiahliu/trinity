//Cleaned
package org.trinity.yqyl.repository.business.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

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

	private double price;

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

	public Long getId() {
		return this.id;
	}

	public double getPrice() {
		return this.price;
	}

	public ServiceInfo getServiceInfo() {
		return this.serviceInfo;
	}

	public ServiceOrder getServiceOrder() {
		return this.serviceOrder;
	}

	public RecordStatus getStatus() {
		return this.status;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public void setPrice(final double price) {
		this.price = price;
	}

	public void setServiceInfo(final ServiceInfo serviceInfo) {
		this.serviceInfo = serviceInfo;
	}

	public void setServiceOrder(final ServiceOrder serviceOrder) {
		this.serviceOrder = serviceOrder;
	}

	public void setStatus(final RecordStatus status) {
		this.status = status;
	}

}
