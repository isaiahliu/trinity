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

/**
 * The persistent class for the service_receiver_client_health_information
 * database table.
 * 
 */
@Entity
@Table(name = "service_receiver_client_health_information")
@NamedQuery(name = "ServiceReceiverClientHealthInformation.findAll", query = "SELECT s FROM ServiceReceiverClientHealthInformation s")
public class ServiceReceiverClientHealthInformation extends AbstractAuditableEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "service_receiver_client_id")
	private Long serviceReceiverClientId;

	private String anaphylactogen;

	@Column(name = "artificial_limb")
	private String artificialLimb;

	@Column(name = "health_status")
	private String healthStatus;

	@Column(name = "hearing_status")
	private String hearingStatus;

	@Column(name = "heart_rate")
	private String heartRate;

	@Column(name = "intellectual_condition")
	private String intellectualCondition;

	@Column(name = "medical_history")
	private String medicalHistory;

	private String photo;

	@Column(name = "primary_emergency_contact_name")
	private String primaryEmergencyContactName;

	@Column(name = "primary_emergency_contact_no")
	private String primaryEmergencyContactNo;

	@Column(name = "secondary_emergency_contact_name")
	private String secondaryEmergencyContactName;

	@Column(name = "secondary_emergency_contact_no")
	private String secondaryEmergencyContactNo;

	@Column(name = "self_care_status")
	private String selfCareStatus;

	private String status;

	@Column(name = "visual_acuity")
	private String visualAcuity;

	// bi-directional one-to-one association to ServiceReceiverClient
	@OneToOne
	@JoinColumn(name = "service_receiver_client_id")
	private ServiceReceiverClient serviceReceiverClient;

	public ServiceReceiverClientHealthInformation() {
	}

	public String getAnaphylactogen() {
		return this.anaphylactogen;
	}

	public String getArtificialLimb() {
		return this.artificialLimb;
	}

	public String getHealthStatus() {
		return this.healthStatus;
	}

	public String getHearingStatus() {
		return this.hearingStatus;
	}

	public String getHeartRate() {
		return this.heartRate;
	}

	public String getIntellectualCondition() {
		return this.intellectualCondition;
	}

	public String getMedicalHistory() {
		return this.medicalHistory;
	}

	public String getPhoto() {
		return this.photo;
	}

	public String getPrimaryEmergencyContactName() {
		return this.primaryEmergencyContactName;
	}

	public String getPrimaryEmergencyContactNo() {
		return this.primaryEmergencyContactNo;
	}

	public String getSecondaryEmergencyContactName() {
		return this.secondaryEmergencyContactName;
	}

	public String getSecondaryEmergencyContactNo() {
		return this.secondaryEmergencyContactNo;
	}

	public String getSelfCareStatus() {
		return this.selfCareStatus;
	}

	public ServiceReceiverClient getServiceReceiverClient() {
		return this.serviceReceiverClient;
	}

	public Long getServiceReceiverClientId() {
		return this.serviceReceiverClientId;
	}

	public String getStatus() {
		return this.status;
	}

	public String getVisualAcuity() {
		return this.visualAcuity;
	}

	public void setAnaphylactogen(final String anaphylactogen) {
		this.anaphylactogen = anaphylactogen;
	}

	public void setArtificialLimb(final String artificialLimb) {
		this.artificialLimb = artificialLimb;
	}

	public void setHealthStatus(final String healthStatus) {
		this.healthStatus = healthStatus;
	}

	public void setHearingStatus(final String hearingStatus) {
		this.hearingStatus = hearingStatus;
	}

	public void setHeartRate(final String heartRate) {
		this.heartRate = heartRate;
	}

	public void setIntellectualCondition(final String intellectualCondition) {
		this.intellectualCondition = intellectualCondition;
	}

	public void setMedicalHistory(final String medicalHistory) {
		this.medicalHistory = medicalHistory;
	}

	public void setPhoto(final String photo) {
		this.photo = photo;
	}

	public void setPrimaryEmergencyContactName(final String primaryEmergencyContactName) {
		this.primaryEmergencyContactName = primaryEmergencyContactName;
	}

	public void setPrimaryEmergencyContactNo(final String primaryEmergencyContactNo) {
		this.primaryEmergencyContactNo = primaryEmergencyContactNo;
	}

	public void setSecondaryEmergencyContactName(final String secondaryEmergencyContactName) {
		this.secondaryEmergencyContactName = secondaryEmergencyContactName;
	}

	public void setSecondaryEmergencyContactNo(final String secondaryEmergencyContactNo) {
		this.secondaryEmergencyContactNo = secondaryEmergencyContactNo;
	}

	public void setSelfCareStatus(final String selfCareStatus) {
		this.selfCareStatus = selfCareStatus;
	}

	public void setServiceReceiverClient(final ServiceReceiverClient serviceReceiverClient) {
		this.serviceReceiverClient = serviceReceiverClient;
	}

	public void setServiceReceiverClientId(final Long serviceReceiverClientId) {
		this.serviceReceiverClientId = serviceReceiverClientId;
	}

	public void setStatus(final String status) {
		this.status = status;
	}

	public void setVisualAcuity(final String visualAcuity) {
		this.visualAcuity = visualAcuity;
	}

}
