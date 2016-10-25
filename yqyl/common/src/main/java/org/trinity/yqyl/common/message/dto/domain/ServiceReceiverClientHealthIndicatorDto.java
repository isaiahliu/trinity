package org.trinity.yqyl.common.message.dto.domain;

import org.trinity.common.dto.domain.AbstractBusinessDto;
import org.trinity.common.dto.object.LookupDto;

public class ServiceReceiverClientHealthIndicatorDto extends AbstractBusinessDto {
    private String actionCapacity;

    private String allergicHistory;

    private String bloodFat;

    private String bloodSugar;

    private String bloodType;

    private LookupDto brainDeath;

    private LookupDto cancer;

    private LookupDto coronaryDisease;

    private String diastolicPressure;

    private String heartRate;

    private String height;

    private String highPressureAlarmValue;

    private LookupDto lungDisease;

    private String other;

    private LookupDto status;

    private String systolicPressure;

    private String tripMode;

    private String vision;

    private String waistline;

    private String weight;

    private String familyHistory;

    private LookupDto smokerAge;

    private LookupDto drinkerFrequency;

    public String getActionCapacity() {
        return actionCapacity;
    }

    public String getAllergicHistory() {
        return allergicHistory;
    }

    public String getBloodFat() {
        return bloodFat;
    }

    public String getBloodSugar() {
        return bloodSugar;
    }

    public String getBloodType() {
        return bloodType;
    }

    public LookupDto getBrainDeath() {
        return brainDeath;
    }

    public LookupDto getCancer() {
        return cancer;
    }

    public LookupDto getCoronaryDisease() {
        return coronaryDisease;
    }

    public String getDiastolicPressure() {
        return diastolicPressure;
    }

    public LookupDto getDrinkerFrequency() {
        return drinkerFrequency;
    }

    public String getFamilyHistory() {
        return familyHistory;
    }

    public String getHeartRate() {
        return heartRate;
    }

    public String getHeight() {
        return height;
    }

    public String getHighPressureAlarmValue() {
        return highPressureAlarmValue;
    }

    public LookupDto getLungDisease() {
        return lungDisease;
    }

    public String getOther() {
        return other;
    }

    public LookupDto getSmokerAge() {
        return smokerAge;
    }

    public LookupDto getStatus() {
        return status;
    }

    public String getSystolicPressure() {
        return systolicPressure;
    }

    public String getTripMode() {
        return tripMode;
    }

    public String getVision() {
        return vision;
    }

    public String getWaistline() {
        return waistline;
    }

    public String getWeight() {
        return weight;
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

    public void setBrainDeath(final LookupDto brainDeath) {
        this.brainDeath = brainDeath;
    }

    public void setCancer(final LookupDto cancer) {
        this.cancer = cancer;
    }

    public void setCoronaryDisease(final LookupDto coronaryDisease) {
        this.coronaryDisease = coronaryDisease;
    }

    public void setDiastolicPressure(final String diastolicPressure) {
        this.diastolicPressure = diastolicPressure;
    }

    public void setDrinkerFrequency(final LookupDto drinkerFrequency) {
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

    public void setLungDisease(final LookupDto lungDisease) {
        this.lungDisease = lungDisease;
    }

    public void setOther(final String other) {
        this.other = other;
    }

    public void setSmokerAge(final LookupDto smokerAge) {
        this.smokerAge = smokerAge;
    }

    public void setStatus(final LookupDto status) {
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
