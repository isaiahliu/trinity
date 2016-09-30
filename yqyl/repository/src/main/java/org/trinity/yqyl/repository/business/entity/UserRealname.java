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
import org.trinity.yqyl.common.message.lookup.CredentialType;
import org.trinity.yqyl.common.message.lookup.RealnameStatus;

/**
 * The persistent class for the user_realname database table.
 *
 */
@Entity
@Table(name = "user_realname")
@NamedQuery(name = "UserRealname.findAll", query = "SELECT u FROM UserRealname u")
public class UserRealname extends AbstractAuditableEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "credential_copy")
    private String credentialCopy;

    @Column(name = "credential_no")
    private String credentialNo;

    @Column(name = "credential_type")
    private CredentialType credentialType;

    private String name;

    private RealnameStatus status;

    // bi-directional one-to-one association to User
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    public UserRealname() {
    }

    public String getCredentialCopy() {
        return this.credentialCopy;
    }

    public String getCredentialNo() {
        return this.credentialNo;
    }

    public CredentialType getCredentialType() {
        return this.credentialType;
    }

    public String getName() {
        return this.name;
    }

    public RealnameStatus getStatus() {
        return this.status;
    }

    public User getUser() {
        return this.user;
    }

    public Long getUserId() {
        return this.userId;
    }

    public void setCredentialCopy(final String credentialCopy) {
        this.credentialCopy = credentialCopy;
    }

    public void setCredentialNo(final String credentialNo) {
        this.credentialNo = credentialNo;
    }

    public void setCredentialType(final CredentialType credentialType) {
        this.credentialType = credentialType;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public void setStatus(final RealnameStatus status) {
        this.status = status;
    }

    public void setUser(final User user) {
        this.user = user;
    }

    public void setUserId(final Long userId) {
        this.userId = userId;
    }

}
