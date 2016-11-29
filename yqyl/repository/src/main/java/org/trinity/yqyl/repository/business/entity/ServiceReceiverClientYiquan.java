//Cleaned
package org.trinity.yqyl.repository.business.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;

import org.trinity.repository.entity.AbstractAuditableEntity;
import org.trinity.yqyl.common.message.lookup.RecordStatus;

/**
 * The persistent class for the yiquan database table.
 *
 */
@Entity
@NamedQuery(name = "ServiceReceiverClientYiquan.findAll", query = "SELECT y FROM ServiceReceiverClientYiquan y")
public class ServiceReceiverClientYiquan extends AbstractAuditableEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "service_receiver_client_id")
    private Long serviceReceiverClientId;

    private String cellphone;

    @Column(unique = true)
    private String code;

    private RecordStatus status;

    // bi-directional many-to-one association to User
    @OneToOne
    @JoinColumn(name = "service_receiver_client_id")
    private ServiceReceiverClient serviceReceiverClient;

    public ServiceReceiverClientYiquan() {
    }

    public String getCellphone() {
        return this.cellphone;
    }

    public String getCode() {
        return this.code;
    }

    public ServiceReceiverClient getServiceReceiverClient() {
        return serviceReceiverClient;
    }

    public Long getServiceReceiverClientId() {
        return serviceReceiverClientId;
    }

    public RecordStatus getStatus() {
        return this.status;
    }

    public void setCellphone(final String cellphone) {
        this.cellphone = cellphone;
    }

    public void setCode(final String code) {
        this.code = code;
    }

    public void setServiceReceiverClient(final ServiceReceiverClient serviceReceiverClient) {
        this.serviceReceiverClient = serviceReceiverClient;
    }

    public void setServiceReceiverClientId(final Long serviceReceiverClientId) {
        this.serviceReceiverClientId = serviceReceiverClientId;
    }

    public void setStatus(final RecordStatus status) {
        this.status = status;
    }
}
