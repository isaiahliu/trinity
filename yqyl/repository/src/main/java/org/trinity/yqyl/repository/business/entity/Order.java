//Cleaned
package org.trinity.yqyl.repository.business.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.TableGenerator;

import org.trinity.repository.entity.AbstractAuditableEntity;
import org.trinity.yqyl.common.message.lookup.OrderStatus;

/**
 * The persistent class for the order database table.
 *
 */
@Entity
@NamedQuery(name = "Order.findAll", query = "SELECT o FROM Order o")
public class Order extends AbstractAuditableEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "Order_PK_IdGenerator")
    @TableGenerator(name = "Order_PK_IdGenerator", table = "id_table", pkColumnName = "type", pkColumnValue = "Order_PK", valueColumnName = "value", initialValue = 1, allocationSize = 1)
    private Long id;

    private BigDecimal price;

    private OrderStatus status;
    // bi-directional many-to-one association to Service
    @ManyToOne
    private Service service;

    // bi-directional many-to-one association to User
    @ManyToOne
    private User user;

    // bi-directional many-to-many association to Service
    @ManyToMany
    @JoinTable(name = "order_service", joinColumns = { @JoinColumn(name = "order_id") }, inverseJoinColumns = {
            @JoinColumn(name = "service_id") })
    private List<Service> services;

    public Order() {
    }

    public Long getId() {
        return this.id;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public Service getService() {
        return this.service;
    }

    public List<Service> getServices() {
        return this.services;
    }

    public OrderStatus getStatus() {
        return this.status;
    }

    public User getUser() {
        return this.user;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public void setPrice(final BigDecimal price) {
        this.price = price;
    }

    public void setService(final Service service) {
        this.service = service;
    }

    public void setServices(final List<Service> services) {
        this.services = services;
    }

    public void setStatus(final OrderStatus status) {
        this.status = status;
    }

    public void setUser(final User user) {
        this.user = user;
    }

}
