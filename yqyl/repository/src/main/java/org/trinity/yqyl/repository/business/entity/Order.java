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

    private String status;

    // bi-directional many-to-many association to Service
    @ManyToMany
    @JoinTable(name = "order_service", joinColumns = { @JoinColumn(name = "order_id") }, inverseJoinColumns = {
            @JoinColumn(name = "service_id") })
    private List<Service> services;

    // bi-directional many-to-one association to ServiceReceiverClient
    @ManyToOne
    @JoinColumn(name = "service_receiver_client_id")
    private ServiceReceiverClient serviceReceiverClient;

    // bi-directional many-to-one association to ServiceSupplierClient
    @ManyToOne
    @JoinColumn(name = "service_supplier_client_id")
    private ServiceSupplierClient serviceSupplierClient;

    public Order() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Service> getServices() {
        return this.services;
    }

    public void setServices(List<Service> services) {
        this.services = services;
    }

    public ServiceReceiverClient getServiceReceiverClient() {
        return this.serviceReceiverClient;
    }

    public void setServiceReceiverClient(ServiceReceiverClient serviceReceiverClient) {
        this.serviceReceiverClient = serviceReceiverClient;
    }

    public ServiceSupplierClient getServiceSupplierClient() {
        return this.serviceSupplierClient;
    }

    public void setServiceSupplierClient(ServiceSupplierClient serviceSupplierClient) {
        this.serviceSupplierClient = serviceSupplierClient;
    }

}
