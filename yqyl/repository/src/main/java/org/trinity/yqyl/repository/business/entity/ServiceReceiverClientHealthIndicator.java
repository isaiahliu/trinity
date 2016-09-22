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
 * The persistent class for the service_receiver_client_health_indicator
 * database table.
 * 
 */
@Entity
@Table(name = "service_receiver_client_health_indicator")
@NamedQuery(name = "ServiceReceiverClientHealthIndicator.findAll", query = "SELECT s FROM ServiceReceiverClientHealthIndicator s")
public class ServiceReceiverClientHealthIndicator extends AbstractAuditableEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "service_receiver_client_id")
	private Long serviceReceiverClientId;

	@Column(name = "action_capacity")
	private String actionCapacity;

	@Column(name = "allergic_history")
	private String allergicHistory;

	@Column(name = "blood_fat")
	private String bloodFat;

	@Column(name = "blood_sugar")
	private String bloodSugar;

	@Column(name = "blood_type")
	private String bloodType;

	@Column(name = "brain_death")
	private String brainDeath;

	private String cancer;

	@Column(name = "coronary_disease")
	private String coronaryDisease;

	@Column(name = "diastolic_pressure")
	private String diastolicPressure;

	@Column(name = "heart_rate")
	private String heartRate;

	private String height;

	@Column(name = "high_pressure_alarm_value")
	private String highPressureAlarmValue;

	@Column(name = "lung_disease")
	private String lungDisease;

	private String other;

	private String status;

	@Column(name = "systolic_pressure")
	private String systolicPressure;

	@Column(name = "trip_mode")
	private String tripMode;

	private String vision;

	private String waistline;

	private String weight;

	// bi-directional one-to-one association to ServiceReceiverClient
	@OneToOne
	@JoinColumn(name = "service_receiver_client_id")
	private ServiceReceiverClient serviceReceiverClient;

	public ServiceReceiverClientHealthIndicator() {
	}

	public String getActionCapacity() {
		return this.actionCapacity;
	}

	public String getAllergicHistory() {
		return this.allergicHistory;
	}

	public String getBloodFat() {
		return this.bloodFat;
	}

	public String getBloodSugar() {
		return this.bloodSugar;
	}

	public String getBloodType() {
		return this.bloodType;
	}

	public String getBrainDeath() {
		return this.brainDeath;
	}

	public String getCancer() {
		return this.cancer;
	}

	public String getCoronaryDisease() {
		return this.coronaryDisease;
	}

	public String getDiastolicPressure() {
		return this.diastolicPressure;
	}

	public String getHeartRate() {
		return this.heartRate;
	}

	public String getHeight() {
		return this.height;
	}

	public String getHighPressureAlarmValue() {
		return this.highPressureAlarmValue;
	}

	public String getLungDisease() {
		return this.lungDisease;
	}

	public String getOther() {
		return this.other;
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

	public String getSystolicPressure() {
		return this.systolicPressure;
	}

	public String getTripMode() {
		return this.tripMode;
	}

	public String getVision() {
		return this.vision;
	}

	public String getWaistline() {
		return this.waistline;
	}

	public String getWeight() {
		return this.weight;
	}

	public void setActionCapacity(final String actionCapacity) {
		this.actionCapacity = actionCapacity;
	}

	public void setAllergicHistory(final String allergicHistory) {
		this.allergicHistory = allergicHistory;
	}

	public void setBloodFat(final String bloodFat) {
		this.bloodFat = bloodFat;
	}

	public void setBloodSugar(final String bloodSugar) {
		this.bloodSugar = bloodSugar;
	}

	public void setBloodType(final String bloodType) {
		this.bloodType = bloodType;
	}

	public void setBrainDeath(final String brainDeath) {
		this.brainDeath = brainDeath;
	}

	public void setCancer(final String cancer) {
		this.cancer = cancer;
	}

	public void setCoronaryDisease(final String coronaryDisease) {
		this.coronaryDisease = coronaryDisease;
	}

	public void setDiastolicPressure(final String diastolicPressure) {
		this.diastolicPressure = diastolicPressure;
	}

	public void setHeartRate(final String heartRate) {
		this.heartRate = heartRate;
	}

	public void setHeight(final String height) {
		this.height = height;
	}

	public void setHighPressureAlarmValue(final String highPressureAlarmValue) {
		this.highPressureAlarmValue = highPressureAlarmValue;
	}

	public void setLungDisease(final String lungDisease) {
		this.lungDisease = lungDisease;
	}

	public void setOther(final String other) {
		this.other = other;
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

	public void setSystolicPressure(final String systolicPressure) {
		this.systolicPressure = systolicPressure;
	}

	public void setTripMode(final String tripMode) {
		this.tripMode = tripMode;
	}

	public void setVision(final String vision) {
		this.vision = vision;
	}

	public void setWaistline(final String waistline) {
		this.waistline = waistline;
	}

	public void setWeight(final String weight) {
		this.weight = weight;
	}

}
