package org.trinity.framework.contact.jtt808;

import org.trinity.framework.contact.IContactMessageSession;

public interface IJtt808MessageSession extends IContactMessageSession {
    String getDriverLicenseNo();

    String getRegistrationCode();

    long getRouteId();

    String getTerminalIdentifier();

    long getTimestamp();

    String getVehicleLicenseNo();

    void setDriverLicenseNo(String driverLicenseNo);

    void setRegistrationCode(String registrationCode);

    void setRouteId(long routeId);

    void setTerminalIdentifier(String terminalIdentifier);

    void setTimestamp(long timestamp);

    void setVehicleLicenseNo(String licenseNo);
}
