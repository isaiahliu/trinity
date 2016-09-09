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
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import org.trinity.repository.entity.AbstractAuditableEntity;
import org.trinity.yqyl.common.message.lookup.RecordStatus;

/**
 * The persistent class for the user_group database table.
 * 
 */
@Entity
@Table(name = "user_group")
@NamedQuery(name = "UserGroup.findAll", query = "SELECT u FROM UserGroup u")
public class UserGroup extends AbstractAuditableEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "UserGroup_PK_IdGenerator")
    @TableGenerator(name = "UserGroup_PK_IdGenerator", table = "id_table", pkColumnName = "type", pkColumnValue = "UserGroup_PK", valueColumnName = "value", initialValue = 1, allocationSize = 1)
    private Long id;

    private String description;

    private String name;

    private RecordStatus status;

    // bi-directional many-to-many association to Role
    @ManyToMany
    @JoinTable(name = "user_group_role", joinColumns = { @JoinColumn(name = "user_group_id") }, inverseJoinColumns = {
            @JoinColumn(name = "role_id") })
    private List<Role> roles;

    // bi-directional many-to-many association to User
    @ManyToMany(mappedBy = "userGroups")
    private List<User> users;

    public UserGroup() {
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

    public RecordStatus getStatus() {
        return this.status;
    }

    public void setStatus(RecordStatus status) {
        this.status = status;
    }

    public List<Role> getRoles() {
        return this.roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public List<User> getUsers() {
        return this.users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

}
