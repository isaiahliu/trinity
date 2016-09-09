package org.trinity.yqyl.repository.business.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import org.trinity.repository.entity.AbstractAuditableEntity;
import org.trinity.yqyl.common.message.lookup.ClientStatus;
import org.trinity.yqyl.common.message.lookup.PersonalType;

/**
 * The persistent class for the allowance_supplier_client database table.
 * 
 */
@Entity
@Table(name = "allowance_supplier_client")
@NamedQuery(name = "AllowanceSupplierClient.findAll", query = "SELECT a FROM AllowanceSupplierClient a")
public class AllowanceSupplierClient extends AbstractAuditableEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "AllowanceSupplierClient_PK_IdGenerator")
    @TableGenerator(name = "AllowanceSupplierClient_PK_IdGenerator", table = "id_table", pkColumnName = "type", pkColumnValue = "AllowanceSupplierClient_PK", valueColumnName = "value", initialValue = 1, allocationSize = 1)
    private Long id;

    private ClientStatus status;

    private PersonalType type;

    // bi-directional many-to-one association to User
    @ManyToOne
    private User user;

    public AllowanceSupplierClient() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ClientStatus getStatus() {
        return this.status;
    }

    public void setStatus(ClientStatus status) {
        this.status = status;
    }

    public PersonalType getType() {
        return this.type;
    }

    public void setType(PersonalType type) {
        this.type = type;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
