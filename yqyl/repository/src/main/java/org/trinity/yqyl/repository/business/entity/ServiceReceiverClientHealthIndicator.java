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
import org.trinity.yqyl.common.message.lookup.FlagStatus;
import org.trinity.yqyl.common.message.lookup.FrequencyStatus;
import org.trinity.yqyl.common.message.lookup.RecordStatus;
import org.trinity.yqyl.common.message.lookup.SmokerAge;

/**
 * The persistent class for the service_receiver_client_health_indicator database table.
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
    private FlagStatus brainDeath;

    private FlagStatus cancer;

    @Column(name = "coronary_disease")
    private FlagStatus coronaryDisease;

    @Column(name = "diastolic_pressure")
    private String diastolicPressure;

    @Column(name = "heart_rate")
    private String heartRate;

    private String height;

    @Column(name = "high_pressure_alarm_value")
    private String highPressureAlarmValue;

    @Column(name = "lung_disease")
    private FlagStatus lungDisease;

    private String other;

    private RecordStatus status;

    @Column(name = "systolic_pressure")
    private String systolicPressure;

    @Column(name = "trip_mode")
    private String tripMode;

    private String vision;

    private String waistline;

    private String weight;

    @Column(name = "family_history")
    private String familyHistory;

    @Column(name = "smoker_age")
    private SmokerAge smokerAge;

    @Column(name = "drinker_frequency")
    private FrequencyStatus drinkerFrequency;

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

    public FlagStatus getBrainDeath() {
        return this.brainDeath;
    }

    public FlagStatus getCancer() {
        return this.cancer;
    }

    public FlagStatus getCoronaryDisease() {
        return this.coronaryDisease;
    }

    public String getDiastolicPressure() {
        return this.diastolicPressure;
    }

    public FrequencyStatus getDrinkerFrequency() {
        return drinkerFrequency;
    }

    public String getFamilyHistory() {
        return familyHistory;
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

    public FlagStatus getLungDisease() {
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

    public SmokerAge getSmokerAge() {
        return smokerAge;
    }

    public RecordStatus getStatus() {
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

    public void setBrainDeath(final FlagStatus brainDeath) {
        this.brainDeath = brainDeath;
    }

    public void setCancer(final FlagStatus cancer) {
        this.cancer = cancer;
    }

    public void setCoronaryDisease(final FlagStatus coronaryDisease) {
        this.coronaryDisease = coronaryDisease;
    }

    public void setDiastolicPressure(final String diastolicPressure) {
        this.diastolicPressure = diastolicPressure;
    }

    public void setDrinkerFrequency(final FrequencyStatus drinkerFrequency) {
        this.drinkerFrequency = drinkerFrequency;
    }

    public void setFamilyHistory(final String familyHistory) {
        this.familyHistory = familyHistory;
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

    public void setLungDisease(final FlagStatus lungDisease) {
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

    public void setSmokerAge(final SmokerAge smokerAge) {
        this.smokerAge = smokerAge;
    }

    public void setStatus(final RecordStatus status) {
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
