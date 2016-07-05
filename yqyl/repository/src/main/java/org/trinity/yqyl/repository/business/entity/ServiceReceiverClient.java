package org.trinity.yqyl.repository.business.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import org.trinity.repository.entity.AbstractAuditableEntity;

/**
 * The persistent class for the service_receiver_client database table.
 * 
 */
@Entity
@Table(name = "service_receiver_client")
@NamedQuery(name = "ServiceReceiverClient.findAll", query = "SELECT s FROM ServiceReceiverClient s")
public class ServiceReceiverClient extends AbstractAuditableEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "ServiceReceiverClient_PK_IdGenerator")
    @TableGenerator(name = "ServiceReceiverClient_PK_IdGenerator", table = "id_table", pkColumnName = "type", pkColumnValue = "ServiceReceiverClient_PK", valueColumnName = "value", initialValue = 1, allocationSize = 1)
    private Long id;

    private String status;

    // bi-directional many-to-one association to Order
    @OneToMany(mappedBy = "serviceReceiverClient")
    private List<Order> orders;

    // bi-directional many-to-one association to User
    @ManyToOne
    private User user;

    public ServiceReceiverClient() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Order> getOrders() {
        return this.orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public Order addOrder(Order order) {
        getOrders().add(order);
        order.setServiceReceiverClient(this);

        return order;
    }

    public Order removeOrder(Order order) {
        getOrders().remove(order);
        order.setServiceReceiverClient(null);

        return order;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
