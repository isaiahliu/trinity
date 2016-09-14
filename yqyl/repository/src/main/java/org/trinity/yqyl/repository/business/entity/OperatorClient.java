package org.trinity.yqyl.repository.business.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import org.trinity.repository.entity.AbstractAuditableEntity;
import org.trinity.yqyl.common.message.lookup.OperatorClientStatus;

/**
 * The persistent class for the operator_client database table.
 * 
 */
@Entity
@Table(name = "operator_client")
@NamedQuery(name = "OperatorClient.findAll", query = "SELECT o FROM OperatorClient o")
public class OperatorClient extends AbstractAuditableEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "OperatorClient_PK_IdGenerator")
	@TableGenerator(name = "OperatorClient_PK_IdGenerator", table = "id_table", pkColumnName = "type", pkColumnValue = "OperatorClient_PK", valueColumnName = "value", initialValue = 1, allocationSize = 1)
	private Long id;

	private OperatorClientStatus status;

	@Column(name = "staff_no")
	private String staffNo;

	private String name;

	// bi-directional many-to-one association to User
	@ManyToOne
	private User user;

	public OperatorClient() {
	}

	public Long getId() {
		return this.id;
	}

	public String getName() {
		return name;
	}

	public String getStaffNo() {
		return staffNo;
	}

	public OperatorClientStatus getStatus() {
		return this.status;
	}

	public User getUser() {
		return this.user;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public void setStaffNo(final String staffNo) {
		this.staffNo = staffNo;
	}

	public void setStatus(final OperatorClientStatus status) {
		this.status = status;
	}

	public void setUser(final User user) {
		this.user = user;
	}

}
