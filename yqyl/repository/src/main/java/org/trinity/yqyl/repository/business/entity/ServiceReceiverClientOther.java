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
import org.trinity.yqyl.common.message.lookup.RecordStatus;

/**
 * The persistent class for the service_receiver_client_other database table.
 *
 */
@Entity
@Table(name = "service_receiver_client_other")
@NamedQuery(name = "ServiceReceiverClientOther.findAll", query = "SELECT s FROM ServiceReceiverClientOther s")
public class ServiceReceiverClientOther extends AbstractAuditableEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "service_receiver_client_id")
    private Long serviceReceiverClientId;

    private String comment;

    private String education;

    private String faith;

    private String nationality;

    @Column(name = "native_place")
    private String nativePlace;

    @Column(name = "party_member")
    private String partyMember;

    @Column(name = "place_of_birth")
    private String placeOfBirth;

    private RecordStatus status;

    // bi-directional one-to-one association to ServiceReceiverClient
    @OneToOne
    @JoinColumn(name = "service_receiver_client_id")
    private ServiceReceiverClient serviceReceiverClient;

    public ServiceReceiverClientOther() {
    }

    public String getComment() {
        return this.comment;
    }

    public String getEducation() {
        return this.education;
    }

    public String getFaith() {
        return this.faith;
    }

    public String getNationality() {
        return this.nationality;
    }

    public String getNativePlace() {
        return this.nativePlace;
    }

    public String getPartyMember() {
        return this.partyMember;
    }

    public String getPlaceOfBirth() {
        return this.placeOfBirth;
    }

    public ServiceReceiverClient getServiceReceiverClient() {
        return this.serviceReceiverClient;
    }

    public Long getServiceReceiverClientId() {
        return this.serviceReceiverClientId;
    }

    public RecordStatus getStatus() {
        return this.status;
    }

    public void setComment(final String comment) {
        this.comment = comment;
    }

    public void setEducation(final String education) {
        this.education = education;
    }

    public void setFaith(final String faith) {
        this.faith = faith;
    }

    public void setNationality(final String nationality) {
        this.nationality = nationality;
    }

    public void setNativePlace(final String nativePlace) {
        this.nativePlace = nativePlace;
    }

    public void setPartyMember(final String partyMember) {
        this.partyMember = partyMember;
    }

    public void setPlaceOfBirth(final String placeOfBirth) {
        this.placeOfBirth = placeOfBirth;
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
