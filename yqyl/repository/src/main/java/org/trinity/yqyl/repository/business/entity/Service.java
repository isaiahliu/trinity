package org.trinity.yqyl.repository.business.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.TableGenerator;

import org.trinity.repository.entity.AbstractAuditableEntity;
import org.trinity.yqyl.common.message.lookup.ServiceStatus;

/**
 * The persistent class for the service database table.
 * 
 */
@Entity
@NamedQuery(name = "Service.findAll", query = "SELECT s FROM Service s")
public class Service extends AbstractAuditableEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "Service_PK_IdGenerator")
    @TableGenerator(name = "Service_PK_IdGenerator", table = "id_table", pkColumnName = "type", pkColumnValue = "Service_PK", valueColumnName = "value", initialValue = 1, allocationSize = 1)
    private Long id;

    private String description;

    private String name;

    private BigDecimal price;

    private ServiceStatus status;

    // bi-directional many-to-one association to Favorite
    @OneToMany(mappedBy = "service")
    private List<Favorite> favorites;

    // bi-directional many-to-many association to Order
    @ManyToMany(mappedBy = "services")
    private List<Order> orders;

    // bi-directional many-to-one association to Service
    @ManyToOne
    @JoinColumn(name = "revised_service_id")
    private Service service;

    // bi-directional many-to-one association to Service
    @OneToMany(mappedBy = "service")
    private List<Service> services;

    public Service() {
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

    public BigDecimal getPrice() {
        return this.price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public ServiceStatus getStatus() {
        return this.status;
    }

    public void setStatus(ServiceStatus status) {
        this.status = status;
    }

    public List<Favorite> getFavorites() {
        return this.favorites;
    }

    public void setFavorites(List<Favorite> favorites) {
        this.favorites = favorites;
    }

    public Favorite addFavorite(Favorite favorite) {
        getFavorites().add(favorite);
        favorite.setService(this);

        return favorite;
    }

    public Favorite removeFavorite(Favorite favorite) {
        getFavorites().remove(favorite);
        favorite.setService(null);

        return favorite;
    }

    public List<Order> getOrders() {
        return this.orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public Service getService() {
        return this.service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public List<Service> getServices() {
        return this.services;
    }

    public void setServices(List<Service> services) {
        this.services = services;
    }

    public Service addService(Service service) {
        getServices().add(service);
        service.setService(this);

        return service;
    }

    public Service removeService(Service service) {
        getServices().remove(service);
        service.setService(null);

        return service;
    }

}
