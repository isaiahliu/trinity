//Cleaned
package org.trinity.yqyl.repository.business.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.TableGenerator;

import org.trinity.repository.entity.AbstractAuditableEntity;
import org.trinity.yqyl.common.message.lookup.RecordStatus;

/**
 * The persistent class for the role database table.
 * 
 */
@Entity
@NamedQuery(name = "Role.findAll", query = "SELECT r FROM Role r")
public class Role extends AbstractAuditableEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "Role_PK_IdGenerator")
	@TableGenerator(name = "Role_PK_IdGenerator", table = "id_table", pkColumnName = "type", pkColumnValue = "Role_PK", valueColumnName = "value", initialValue = 1, allocationSize = 1)
	private Long id;

	private String description;

	private String name;

	private RecordStatus status;

	// bi-directional many-to-many association to Accessright
	@ManyToMany
	@JoinTable(name = "role_accessright", joinColumns = { @JoinColumn(name = "role_id") }, inverseJoinColumns = {
			@JoinColumn(name = "access_right_id") })
	private List<Accessright> accessrights;

	// bi-directional many-to-many association to UserGroup
	@ManyToMany(mappedBy = "roles")
	private List<User> users;

	public Role() {
	}

	public List<Accessright> getAccessrights() {
		return this.accessrights;
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

	public RecordStatus getStatus() {
		return this.status;
	}

	public List<User> getUsers() {
		return this.users;
	}

	public void setAccessrights(final List<Accessright> accessrights) {
		this.accessrights = accessrights;
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

	public void setStatus(final RecordStatus status) {
		this.status = status;
	}

	public void setUsers(final List<User> users) {
		this.users = users;
	}

}
