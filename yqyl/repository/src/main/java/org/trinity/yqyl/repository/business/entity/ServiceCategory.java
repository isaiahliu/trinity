package org.trinity.yqyl.repository.business.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import org.trinity.repository.entity.AbstractAuditableEntity;
import org.trinity.yqyl.common.message.lookup.RecordStatus;

/**
 * The persistent class for the service_category database table.
 * 
 */
@Entity
@Table(name = "service_category")
@NamedQuery(name = "ServiceCategory.findAll", query = "SELECT s FROM ServiceCategory s")
public class ServiceCategory extends AbstractAuditableEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "ServiceCategory_PK_IdGenerator")
	@TableGenerator(name = "ServiceCategory_PK_IdGenerator", table = "id_table", pkColumnName = "type", pkColumnValue = "ServiceCategory_PK", valueColumnName = "value", initialValue = 1, allocationSize = 1)
	private Long id;

	@Lob
	private String description;

	private String name;

	private RecordStatus status;

	// bi-directional many-to-one association to Service
	@OneToMany(mappedBy = "serviceCategory")
	private List<Service> services;

	public ServiceCategory() {
	}

	public Service addService(final Service service) {
		getServices().add(service);
		service.setServiceCategory(this);

		return service;
	}

	public String getDescription() {
		return this.description;
	}

	public Long getId() {
		return this.id;
	}

	public String getName() {
		return this.name;
	}

	public List<Service> getServices() {
		return this.services;
	}

	public RecordStatus getStatus() {
		return this.status;
	}

	public Service removeService(final Service service) {
		getServices().remove(service);
		service.setServiceCategory(null);

		return service;
	}

	public void setDescription(final String description) {
		this.description = description;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public void setServices(final List<Service> services) {
		this.services = services;
	}

	public void setStatus(final RecordStatus status) {
		this.status = status;
	}

}
