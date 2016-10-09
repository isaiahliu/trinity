//Cleaned
package org.trinity.yqyl.repository.business.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import org.trinity.repository.entity.AbstractAuditableEntity;
import org.trinity.yqyl.common.message.lookup.RecordStatus;

/**
 * The persistent class for the service_category database table.
 *
 */
@Entity
@Table(name = "service_category")
@NamedQuery(name = "ServiceCategory.findAll", query = "SELECT s FROM ServiceCategory s")
public class ServiceCategory extends AbstractAuditableEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "ServiceCategory_PK_IdGenerator")
    @TableGenerator(name = "ServiceCategory_PK_IdGenerator", table = "id_table", pkColumnName = "type", pkColumnValue = "ServiceCategory_PK", valueColumnName = "value", initialValue = 1, allocationSize = 1)
    private Long id;

    @Lob
    private String description;

    private String name;

    private RecordStatus status;

    // bi-directional many-to-one association to ServiceCategory
    @ManyToOne
    @JoinColumn(name = "parent_service_category_id")
    private ServiceCategory parent;

    // bi-directional many-to-one association to ServiceCategory
    @OneToMany(mappedBy = "parent")
    private List<ServiceCategory> children;
    // bi-directional many-to-one association to ServiceInfo
    @OneToMany(mappedBy = "serviceCategory")
    private List<ServiceInfo> serviceInfos;

    public ServiceCategory() {
    }

    public ServiceCategory addChildren(final ServiceCategory children) {
        getChildren().add(children);
        children.setParent(this);

        return children;
    }

    public ServiceInfo addServiceInfo(final ServiceInfo serviceInfo) {
        getServiceInfos().add(serviceInfo);
        serviceInfo.setServiceCategory(this);

        return serviceInfo;
    }

    public List<ServiceCategory> getChildren() {
        return this.children;
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

    public ServiceCategory getParent() {
        return this.parent;
    }

    public List<ServiceInfo> getServiceInfos() {
        return this.serviceInfos;
    }

    public RecordStatus getStatus() {
        return this.status;
    }

    public ServiceCategory removeChildren(final ServiceCategory children) {
        getChildren().remove(children);
        children.setParent(null);

        return children;
    }

    public ServiceInfo removeServiceInfo(final ServiceInfo serviceInfo) {
        getServiceInfos().remove(serviceInfo);
        serviceInfo.setServiceCategory(null);

        return serviceInfo;
    }

    public void setChildren(final List<ServiceCategory> children) {
        this.children = children;
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

    public void setParent(final ServiceCategory parent) {
        this.parent = parent;
    }

    public void setServiceInfos(final List<ServiceInfo> serviceInfos) {
        this.serviceInfos = serviceInfos;
    }

    public void setStatus(final RecordStatus status) {
        this.status = status;
    }

}
