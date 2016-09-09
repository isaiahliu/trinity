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
import org.trinity.yqyl.common.message.lookup.PersonalType;
import org.trinity.yqyl.common.message.lookup.ServiceSupplierClientStatus;

/**
 * The persistent class for the service_supplier_client database table.
 *
 */
@Entity
@Table(name = "service_supplier_client")
@NamedQuery(name = "ServiceSupplierClient.findAll", query = "SELECT s FROM ServiceSupplierClient s")
public class ServiceSupplierClient extends AbstractAuditableEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "ServiceSupplierClient_PK_IdGenerator")
    @TableGenerator(name = "ServiceSupplierClient_PK_IdGenerator", table = "id_table", pkColumnName = "type", pkColumnValue = "ServiceSupplierClient_PK", valueColumnName = "value", initialValue = 1, allocationSize = 1)
    private Long id;

    private String email;

    private String identity;

    private String name;

    private ServiceSupplierClientStatus status;

    private PersonalType type;

    // bi-directional many-to-one association to User
    @ManyToOne
    private User user;

    @Column(name = "identity_copy", insertable = true, updatable = true)
    private String identityCopy;

    @Column(name = "license_copy", insertable = true, updatable = true)
    private String licenseCopy;

    public ServiceSupplierClient() {
    }

    public String getEmail() {
        return this.email;
    }

    public Long getId() {
        return this.id;
    }

    public String getIdentity() {
        return this.identity;
    }

    public String getIdentityCopy() {
        return identityCopy;
    }

    public String getLicenseCopy() {
        return licenseCopy;
    }

    public String getName() {
        return this.name;
    }

    public ServiceSupplierClientStatus getStatus() {
        return this.status;
    }

    public PersonalType getType() {
        return this.type;
    }

    public User getUser() {
        return this.user;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public void setIdentity(final String identity) {
        this.identity = identity;
    }

    public void setIdentityCopy(final String identityCopy) {
        this.identityCopy = identityCopy;
    }

    public void setLicenseCopy(final String licenseCopy) {
        this.licenseCopy = licenseCopy;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public void setStatus(final ServiceSupplierClientStatus status) {
        this.status = status;
    }

    public void setType(final PersonalType type) {
        this.type = type;
    }

    public void setUser(final User user) {
        this.user = user;
    }

}
