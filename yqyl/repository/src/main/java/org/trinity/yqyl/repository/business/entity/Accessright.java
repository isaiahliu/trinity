package org.trinity.yqyl.repository.business.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.TableGenerator;

import org.trinity.repository.entity.AbstractAuditableEntity;
import org.trinity.yqyl.common.message.lookup.AccessRight;

/**
 * The persistent class for the accessright database table.
 * 
 */
@Entity
@NamedQuery(name = "Accessright.findAll", query = "SELECT a FROM Accessright a")
public class Accessright extends AbstractAuditableEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "Accessright_PK_IdGenerator")
    @TableGenerator(name = "Accessright_PK_IdGenerator", table = "id_table", pkColumnName = "type", pkColumnValue = "Accessright_PK", valueColumnName = "value", initialValue = 1, allocationSize = 1)
    private Long id;

    private String description;

    private AccessRight name;

    private String status;

    // bi-directional many-to-many association to Role
    @ManyToMany(mappedBy = "accessrights")
    private List<Role> roles;

    public Accessright() {
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

    public AccessRight getName() {
        return this.name;
    }

    public void setName(AccessRight name) {
        this.name = name;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Role> getRoles() {
        return this.roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

}
