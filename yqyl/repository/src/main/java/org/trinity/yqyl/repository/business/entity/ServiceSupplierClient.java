//Cleaned
package org.trinity.yqyl.repository.business.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

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
    @Column(name = "user_id")
    private Long userId;

    private String email;

    private String identity;

    private String name;

    private String address;

    private ServiceSupplierClientStatus status;

    private PersonalType type;

    // bi-directional one-to-one association to User
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "identity_copy", insertable = true, updatable = true)
    private String identityCopy;

    @Column(name = "license_copy", insertable = true, updatable = true)
    private String licenseCopy;

    public ServiceSupplierClient() {
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return this.email;
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

    public Long getUserId() {
        return userId;
    }

    public void setAddress(final String address) {
        this.address = address;
    }

    public void setEmail(final String email) {
        this.email = email;
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

    public void setUserId(final Long userId) {
        this.userId = userId;
    }

}
