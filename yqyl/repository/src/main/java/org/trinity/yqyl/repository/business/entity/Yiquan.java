//Cleaned
package org.trinity.yqyl.repository.business.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.TableGenerator;

import org.trinity.repository.entity.AbstractAuditableEntity;
import org.trinity.yqyl.common.message.lookup.RecordStatus;

/**
 * The persistent class for the yiquan database table.
 *
 */
@Entity
@NamedQuery(name = "Yiquan.findAll", query = "SELECT y FROM Yiquan y")
public class Yiquan extends AbstractAuditableEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "Yiquan_PK_IdGenerator")
    @TableGenerator(name = "Yiquan_PK_IdGenerator", table = "id_table", pkColumnName = "type", pkColumnValue = "Yiquan_PK", valueColumnName = "value", initialValue = 1, allocationSize = 1)
    private Long id;

    private double amount;

    private String cellphone;

    private String code;

    private RecordStatus status;

    // bi-directional many-to-one association to User
    @OneToMany(mappedBy = "yiquan")
    private List<User> users;

    public Yiquan() {
    }

    public User addUser(final User user) {
        getUsers().add(user);
        user.setYiquan(this);

        return user;
    }

    public double getAmount() {
        return this.amount;
    }

    public String getCellphone() {
        return this.cellphone;
    }

    public String getCode() {
        return this.code;
    }

    public Long getId() {
        return this.id;
    }

    public RecordStatus getStatus() {
        return this.status;
    }

    public List<User> getUsers() {
        return this.users;
    }

    public User removeUser(final User user) {
        getUsers().remove(user);
        user.setYiquan(null);

        return user;
    }

    public void setAmount(final double amount) {
        this.amount = amount;
    }

    public void setCellphone(final String cellphone) {
        this.cellphone = cellphone;
    }

    public void setCode(final String code) {
        this.code = code;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public void setStatus(final RecordStatus status) {
        this.status = status;
    }

    public void setUsers(final List<User> users) {
        this.users = users;
    }
}
