package org.trinity.yqyl.process.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.trinity.common.dto.object.LookupDto;
import org.trinity.message.ILookupMessage;
import org.trinity.process.converter.AbstractLookupSupportObjectConverter;
import org.trinity.process.converter.IObjectConverter;
import org.trinity.yqyl.common.message.dto.domain.ServiceReceiverClientHealthIndicatorDto;
import org.trinity.yqyl.common.message.lookup.FlagStatus;
import org.trinity.yqyl.common.message.lookup.FrequencyStatus;
import org.trinity.yqyl.common.message.lookup.RecordStatus;
import org.trinity.yqyl.common.message.lookup.SmokerAge;
import org.trinity.yqyl.repository.business.entity.ServiceReceiverClientHealthIndicator;

@Component
public class ServiceReceiverClientHealthIndicatorConverter
        extends AbstractLookupSupportObjectConverter<ServiceReceiverClientHealthIndicator, ServiceReceiverClientHealthIndicatorDto> {
    @Autowired
    public ServiceReceiverClientHealthIndicatorConverter(final IObjectConverter<ILookupMessage<?>, LookupDto> lookupConverter) {
        super(lookupConverter);
    }

    @Override
    protected void convertBackInternal(final ServiceReceiverClientHealthIndicatorDto source,
            final ServiceReceiverClientHealthIndicator target, final CopyPolicy copyPolicy) {
        copyObject(source::getId, target::getServiceReceiverClientId, target::setServiceReceiverClientId, copyPolicy);
        copyObject(source::getActionCapacity, target::getActionCapacity, target::setActionCapacity, copyPolicy);
        copyObject(source::getAllergicHistory, target::getAllergicHistory, target::setAllergicHistory, copyPolicy);
        copyObject(source::getBloodFat, target::getBloodFat, target::setBloodFat, copyPolicy);
        copyObject(source::getBloodSugar, target::getBloodSugar, target::setBloodSugar, copyPolicy);
        copyObject(source::getBloodType, target::getBloodType, target::setBloodType, copyPolicy);
        copyObject(source::getDiastolicPressure, target::getDiastolicPressure, target::setDiastolicPressure, copyPolicy);
        copyObject(source::getHeartRate, target::getHeartRate, target::setHeartRate, copyPolicy);
        copyObject(source::getHeight, target::getHeight, target::setHeight, copyPolicy);
        copyObject(source::getHighPressureAlarmValue, target::getHighPressureAlarmValue, target::setHighPressureAlarmValue, copyPolicy);
        copyObject(source::getOther, target::getOther, target::setOther, copyPolicy);
        copyObject(source::getSystolicPressure, target::getSystolicPressure, target::setSystolicPressure, copyPolicy);
        copyObject(source::getTripMode, target::getTripMode, target::setTripMode, copyPolicy);
        copyObject(source::getVision, target::getVision, target::setVision, copyPolicy);
        copyObject(source::getWaistline, target::getWaistline, target::setWaistline, copyPolicy);
        copyObject(source::getWeight, target::getWeight, target::setWeight, copyPolicy);
        copyObject(source::getFamilyHistory, target::getFamilyHistory, target::setFamilyHistory, copyPolicy);

        copyLookup(source::getBrainDeath, target::getBrainDeath, target::setBrainDeath, FlagStatus.class, copyPolicy);
        copyLookup(source::getCancer, target::getCancer, target::setCancer, FlagStatus.class, copyPolicy);
        copyLookup(source::getCoronaryDisease, target::getCoronaryDisease, target::setCoronaryDisease, FlagStatus.class, copyPolicy);
        copyLookup(source::getLungDisease, target::getLungDisease, target::setLungDisease, FlagStatus.class, copyPolicy);
        copyLookup(source::getStatus, target::getStatus, target::setStatus, RecordStatus.class, copyPolicy);
        copyLookup(source::getSmokerAge, target::getSmokerAge, target::setSmokerAge, SmokerAge.class, copyPolicy);
        copyLookup(source::getDrinkerFrequency, target::getDrinkerFrequency, target::setDrinkerFrequency, FrequencyStatus.class,
                copyPolicy);
    }

    @Override
    protected void convertInternal(final ServiceReceiverClientHealthIndicator source, final ServiceReceiverClientHealthIndicatorDto target,
            final CopyPolicy copyPolicy) {
        copyObject(source::getServiceReceiverClientId, target::getId, target::setId, copyPolicy);
        copyObject(source::getActionCapacity, target::getActionCapacity, target::setActionCapacity, copyPolicy);
        copyObject(source::getAllergicHistory, target::getAllergicHistory, target::setAllergicHistory, copyPolicy);
        copyObject(source::getBloodFat, target::getBloodFat, target::setBloodFat, copyPolicy);
        copyObject(source::getBloodSugar, target::getBloodSugar, target::setBloodSugar, copyPolicy);
        copyObject(source::getBloodType, target::getBloodType, target::setBloodType, copyPolicy);
        copyObject(source::getDiastolicPressure, target::getDiastolicPressure, target::setDiastolicPressure, copyPolicy);
        copyObject(source::getHeartRate, target::getHeartRate, target::setHeartRate, copyPolicy);
        copyObject(source::getHeight, target::getHeight, target::setHeight, copyPolicy);
        copyObject(source::getHighPressureAlarmValue, target::getHighPressureAlarmValue, target::setHighPressureAlarmValue, copyPolicy);
        copyObject(source::getOther, target::getOther, target::setOther, copyPolicy);
        copyObject(source::getSystolicPressure, target::getSystolicPressure, target::setSystolicPressure, copyPolicy);
        copyObject(source::getTripMode, target::getTripMode, target::setTripMode, copyPolicy);
        copyObject(source::getVision, target::getVision, target::setVision, copyPolicy);
        copyObject(source::getWaistline, target::getWaistline, target::setWaistline, copyPolicy);
        copyObject(source::getWeight, target::getWeight, target::setWeight, copyPolicy);
        copyObject(source::getFamilyHistory, target::getFamilyHistory, target::setFamilyHistory, copyPolicy);

        copyMessage(source::getBrainDeath, target::getBrainDeath, target::setBrainDeath, copyPolicy);
        copyMessage(source::getCancer, target::getCancer, target::setCancer, copyPolicy);
        copyMessage(source::getCoronaryDisease, target::getCoronaryDisease, target::setCoronaryDisease, copyPolicy);
        copyMessage(source::getLungDisease, target::getLungDisease, target::setLungDisease, copyPolicy);
        copyMessage(source::getStatus, target::getStatus, target::setStatus, copyPolicy);
        copyMessage(source::getSmokerAge, target::getSmokerAge, target::setSmokerAge, copyPolicy);
        copyMessage(source::getDrinkerFrequency, target::getDrinkerFrequency, target::setDrinkerFrequency, copyPolicy);
    }

    @Override
    protected ServiceReceiverClientHealthIndicator createFromInstance() {
        return new ServiceReceiverClientHealthIndicator();
    }

    @Override
    protected ServiceReceiverClientHealthIndicatorDto createToInstance() {
        return new ServiceReceiverClientHealthIndicatorDto();
    }
}
