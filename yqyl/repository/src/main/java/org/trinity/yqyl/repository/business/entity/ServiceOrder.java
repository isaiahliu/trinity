//Cleaned
package org.trinity.yqyl.repository.business.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.trinity.repository.entity.AbstractAuditableEntity;
import org.trinity.yqyl.common.message.lookup.OrderStatus;

/**
 * The persistent class for the order database table.
 *
 */
@Entity
@Table(name = "service_order")
@NamedQuery(name = "ServiceOrder.findAll", query = "SELECT o FROM ServiceOrder o")
public class ServiceOrder extends AbstractAuditableEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    private String phone;

    private String address;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "service_time")
    private Date serviceTime;

    // bi-directional many-to-one association to ServiceInfo
    @ManyToOne
    @JoinColumn(name = "service_info_id")
    private ServiceInfo serviceInfo;

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "ServiceOrder_PK_IdGenerator")
    @TableGenerator(name = "ServiceOrder_PK_IdGenerator", table = "id_table", pkColumnName = "type", pkColumnValue = "ServiceOrder_PK", valueColumnName = "value", initialValue = 1, allocationSize = 1)
    private Long id;

    private Double price;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "proposal_time")
    private Date proposalTime;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "approval_time")
    private Date approvalTime;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "settled_time")
    private Date settledTime;

    private OrderStatus status;

    // bi-directional one-to-one association to ServiceOrderAppraise
    @OneToOne(mappedBy = "serviceOrder")
    private ServiceOrderAppraise appraise;

    // bi-directional many-to-one association to User
    @ManyToOne
    private User user;

    public ServiceOrder() {
    }

    public String getAddress() {
        return address;
    }

    public ServiceOrderAppraise getAppraise() {
        return this.appraise;
    }

    public Date getApprovalTime() {
        return approvalTime;
    }

    public Long getId() {
        return this.id;
    }

    public String getPhone() {
        return phone;
    }

    public Double getPrice() {
        return this.price;
    }

    public Date getProposalTime() {
        return proposalTime;
    }

    public ServiceInfo getServiceInfo() {
        return this.serviceInfo;
    }

    public Date getServiceTime() {
        return serviceTime;
    }

    public Date getSettledTime() {
        return settledTime;
    }

    public OrderStatus getStatus() {
        return this.status;
    }

    public User getUser() {
        return this.user;
    }

    public void setAddress(final String address) {
        this.address = address;
    }

    public void setAppraise(final ServiceOrderAppraise appraise) {
        this.appraise = appraise;
    }

    public void setApprovalTime(final Date approvalTime) {
        this.approvalTime = approvalTime;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public void setPhone(final String phone) {
        this.phone = phone;
    }

    public void setPrice(final Double price) {
        this.price = price;
    }

    public void setProposalTime(final Date proposalTime) {
        this.proposalTime = proposalTime;
    }

    public void setServiceInfo(final ServiceInfo serviceInfo) {
        this.serviceInfo = serviceInfo;
    }

    public void setServiceTime(final Date serviceTime) {
        this.serviceTime = serviceTime;
    }

    public void setSettledTime(final Date settledTime) {
        this.settledTime = settledTime;
    }

    public void setStatus(final OrderStatus status) {
        this.status = status;
    }

    public void setUser(final User user) {
        this.user = user;
    }
}
