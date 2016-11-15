package org.trinity.framework.contact.jtt808.messages.terminal.request;

import java.util.LinkedList;
import java.util.List;

import org.trinity.common.util.Tuple3;
import org.trinity.framework.contact.ContactMessage;
import org.trinity.framework.contact.ContactMessage.StoreMethod;
import org.trinity.framework.contact.ContactMessageField;
import org.trinity.framework.contact.ContactMessageField.FieldType;
import org.trinity.framework.contact.ContactMessageStructField;
import org.trinity.framework.contact.ContactMessageStructField.StructFieldType;
import org.trinity.framework.contact.IAdditionalMessageKey;
import org.trinity.framework.contact.IContactMessageFieldType;

@ContactMessage(storeMethod = StoreMethod.BIG_END)
public class Jtt808TerminalUploadPositionRequest extends AbstractJtt808TerminalRequest {
    public static class StatusStruct {
        public static enum LoadStatus implements IContactMessageFieldType {
            EMPTY(0x00),
            HALF(0x01),
            DETERMINED(0x10),
            FULL(0x11);

            private final int value;

            private LoadStatus(final int value) {
                this.value = value;
            }

            @Override
            public int getValue() {
                return value;
            }
        }

        @ContactMessageStructField(fieldType = StructFieldType.FLAG, order = 1)
        private boolean accOn;

        @ContactMessageStructField(fieldType = StructFieldType.FLAG, order = 2)
        private boolean located;

        @ContactMessageStructField(fieldType = StructFieldType.FLAG, order = 3)
        private boolean southLatitude;

        @ContactMessageStructField(fieldType = StructFieldType.FLAG, order = 4)
        private boolean westLongitude;

        @ContactMessageStructField(fieldType = StructFieldType.FLAG, order = 5)
        private boolean stopped;

        @ContactMessageStructField(fieldType = StructFieldType.FLAG, order = 6)
        private boolean locationEncrypted;

        @ContactMessageStructField(fieldType = StructFieldType.NIL, length = 2, order = 7)
        private int noUsed1;

        @ContactMessageStructField(fieldType = StructFieldType.NBIT, length = 2, order = 8)
        private LoadStatus loadStatus;

        @ContactMessageStructField(fieldType = StructFieldType.FLAG, order = 9)
        private boolean oilWayDisconnected;

        @ContactMessageStructField(fieldType = StructFieldType.FLAG, order = 10)
        private boolean circuitDisconnected;

        @ContactMessageStructField(fieldType = StructFieldType.FLAG, order = 11)
        private boolean doorLocked;

        @ContactMessageStructField(fieldType = StructFieldType.FLAG, order = 12)
        private boolean door1Open;

        @ContactMessageStructField(fieldType = StructFieldType.FLAG, order = 13)
        private boolean door2Open;

        @ContactMessageStructField(fieldType = StructFieldType.FLAG, order = 14)
        private boolean door3Open;

        @ContactMessageStructField(fieldType = StructFieldType.FLAG, order = 15)
        private boolean door4Open;

        @ContactMessageStructField(fieldType = StructFieldType.FLAG, order = 16)
        private boolean door5Open;

        @ContactMessageStructField(fieldType = StructFieldType.FLAG, order = 17)
        private boolean gpsLocated;

        @ContactMessageStructField(fieldType = StructFieldType.FLAG, order = 18)
        private boolean compassLocated;

        @ContactMessageStructField(fieldType = StructFieldType.FLAG, order = 19)
        private boolean glonassLocated;

        @ContactMessageStructField(fieldType = StructFieldType.FLAG, order = 20)
        private boolean galileoLocated;

        @ContactMessageStructField(fieldType = StructFieldType.NIL, length = 10, order = 21)
        private int noUsed2;

        public LoadStatus getLoadStatus() {
            return loadStatus;
        }

        public int getNoUsed1() {
            return noUsed1;
        }

        public int getNoUsed2() {
            return noUsed2;
        }

        public boolean isAccOn() {
            return accOn;
        }

        public boolean isCircuitDisconnected() {
            return circuitDisconnected;
        }

        public boolean isCompassLocated() {
            return compassLocated;
        }

        public boolean isDoor1Open() {
            return door1Open;
        }

        public boolean isDoor2Open() {
            return door2Open;
        }

        public boolean isDoor3Open() {
            return door3Open;
        }

        public boolean isDoor4Open() {
            return door4Open;
        }

        public boolean isDoor5Open() {
            return door5Open;
        }

        public boolean isDoorLocked() {
            return doorLocked;
        }

        public boolean isGalileoLocated() {
            return galileoLocated;
        }

        public boolean isGlonassLocated() {
            return glonassLocated;
        }

        public boolean isGpsLocated() {
            return gpsLocated;
        }

        public boolean isLocated() {
            return located;
        }

        public boolean isLocationEncrypted() {
            return locationEncrypted;
        }

        public boolean isOilWayDisconnected() {
            return oilWayDisconnected;
        }

        public boolean isSouthLatitude() {
            return southLatitude;
        }

        public boolean isStopped() {
            return stopped;
        }

        public boolean isWestLongitude() {
            return westLongitude;
        }

        public void setAccOn(final boolean accOn) {
            this.accOn = accOn;
        }

        public void setCircuitDisconnected(final boolean circuitDisconnected) {
            this.circuitDisconnected = circuitDisconnected;
        }

        public void setCompassLocated(final boolean compassLocated) {
            this.compassLocated = compassLocated;
        }

        public void setDoor1Open(final boolean door1Open) {
            this.door1Open = door1Open;
        }

        public void setDoor2Open(final boolean door2Open) {
            this.door2Open = door2Open;
        }

        public void setDoor3Open(final boolean door3Open) {
            this.door3Open = door3Open;
        }

        public void setDoor4Open(final boolean door4Open) {
            this.door4Open = door4Open;
        }

        public void setDoor5Open(final boolean door5Open) {
            this.door5Open = door5Open;
        }

        public void setDoorLocked(final boolean doorLocked) {
            this.doorLocked = doorLocked;
        }

        public void setGalileoLocated(final boolean galileoLocated) {
            this.galileoLocated = galileoLocated;
        }

        public void setGlonassLocated(final boolean glonassLocated) {
            this.glonassLocated = glonassLocated;
        }

        public void setGpsLocated(final boolean gpsLocated) {
            this.gpsLocated = gpsLocated;
        }

        public void setLoadStatus(final LoadStatus loadStatus) {
            this.loadStatus = loadStatus;
        }

        public void setLocated(final boolean located) {
            this.located = located;
        }

        public void setLocationEncrypted(final boolean locationEncrypted) {
            this.locationEncrypted = locationEncrypted;
        }

        public void setNoUsed1(final int noUsed1) {
            this.noUsed1 = noUsed1;
        }

        public void setNoUsed2(final int noUsed2) {
            this.noUsed2 = noUsed2;
        }

        public void setOilWayDisconnected(final boolean oilWayDisconnected) {
            this.oilWayDisconnected = oilWayDisconnected;
        }

        public void setSouthLatitude(final boolean southLatitude) {
            this.southLatitude = southLatitude;
        }

        public void setStopped(final boolean stopped) {
            this.stopped = stopped;
        }

        public void setWestLongitude(final boolean westLongitude) {
            this.westLongitude = westLongitude;
        }
    }

    public static enum TerminalUploadPositionRequestAdditionalMessageKey implements IAdditionalMessageKey {
        MILEAGE(0x01, 4, FieldType.DWORD),
        OIL(0x02, 2, FieldType.WORD),
        SPEED(0x03, 2, FieldType.WORD),
        RESPONSE_REQUIRED_EVENT_ID(0x04, 2, FieldType.WORD),
        OVERSPEED_WARNING(0x11, 5, FieldType.COMPONENT, OverspeedComponent.class),
        IN_OUT_AREA(0x12, 6, FieldType.COMPONENT, InOutAreaComponent.class),
        ILLEGAL_DRIVING_TIME(0x13, 7, FieldType.COMPONENT, IllegalDrivingTimeComponent.class),
        EXTENDED_VEHICLE_STATUS(0x25, 4, FieldType.STRUCT, ExtendVehicleStatusStruct.class),
        IO_STATUS(0x2A, 2, FieldType.STRUCT, IOStatusStruct.class),
        ANALOG_QUANTITY(0x2B, 4, FieldType.STRUCT, AnalogQuantityStruct.class),
        WIRELESS_SIGNAL_STRENGTH(0x30, 1, FieldType.BYTE),
        GNSS_SIGNAL_STRENGTH(0x31, 1, FieldType.BYTE),
        DEFINED_LENGTH(0xE0, 0, FieldType.BCD);

        public static class AnalogQuantityStruct {
            @ContactMessageStructField(fieldType = StructFieldType.NBIT, length = 16, order = 1)
            private int ad0;

            @ContactMessageStructField(fieldType = StructFieldType.NBIT, length = 16, order = 2)
            private int ad1;

            public int getAd0() {
                return ad0;
            }

            public int getAd1() {
                return ad1;
            }

            public void setAd0(final int ad0) {
                this.ad0 = ad0;
            }

            public void setAd1(final int ad1) {
                this.ad1 = ad1;
            }
        }

        public static class ExtendVehicleStatusStruct {
            @ContactMessageStructField(fieldType = StructFieldType.FLAG, order = 1)
            private boolean dippedHeadLightOn;

            @ContactMessageStructField(fieldType = StructFieldType.FLAG, order = 2)
            private boolean highBeanOn;

            @ContactMessageStructField(fieldType = StructFieldType.FLAG, order = 3)
            private boolean rightTurnSignalOn;

            @ContactMessageStructField(fieldType = StructFieldType.FLAG, order = 4)
            private boolean leftTurnSignalOn;

            @ContactMessageStructField(fieldType = StructFieldType.FLAG, order = 5)
            private boolean brakingOn;

            @ContactMessageStructField(fieldType = StructFieldType.FLAG, order = 6)
            private boolean reverseOn;

            @ContactMessageStructField(fieldType = StructFieldType.FLAG, order = 7)
            private boolean fogLightOn;

            @ContactMessageStructField(fieldType = StructFieldType.FLAG, order = 8)
            private boolean clearanceLampOn;

            @ContactMessageStructField(fieldType = StructFieldType.FLAG, order = 9)
            private boolean hornOn;

            @ContactMessageStructField(fieldType = StructFieldType.FLAG, order = 10)
            private boolean acOn;

            @ContactMessageStructField(fieldType = StructFieldType.FLAG, order = 11)
            private boolean nullOn;

            @ContactMessageStructField(fieldType = StructFieldType.FLAG, order = 12)
            private boolean retarderOn;

            @ContactMessageStructField(fieldType = StructFieldType.FLAG, order = 13)
            private boolean absOn;

            @ContactMessageStructField(fieldType = StructFieldType.FLAG, order = 14)
            private boolean heaterOn;

            @ContactMessageStructField(fieldType = StructFieldType.FLAG, order = 15)
            private boolean clutchOn;

            @ContactMessageStructField(fieldType = StructFieldType.NIL, length = 17, order = 16)
            private int noUsed;

            public int getNoUsed() {
                return noUsed;
            }

            public boolean isAbsOn() {
                return absOn;
            }

            public boolean isAcOn() {
                return acOn;
            }

            public boolean isBrakingOn() {
                return brakingOn;
            }

            public boolean isClearanceLampOn() {
                return clearanceLampOn;
            }

            public boolean isClutchOn() {
                return clutchOn;
            }

            public boolean isDippedHeadLightOn() {
                return dippedHeadLightOn;
            }

            public boolean isFogLightOn() {
                return fogLightOn;
            }

            public boolean isHeaterOn() {
                return heaterOn;
            }

            public boolean isHighBeanOn() {
                return highBeanOn;
            }

            public boolean isHornOn() {
                return hornOn;
            }

            public boolean isLeftTurnSignalOn() {
                return leftTurnSignalOn;
            }

            public boolean isNullOn() {
                return nullOn;
            }

            public boolean isRetarderOn() {
                return retarderOn;
            }

            public boolean isReverseOn() {
                return reverseOn;
            }

            public boolean isRightTurnSignalOn() {
                return rightTurnSignalOn;
            }

            public void setAbsOn(final boolean absOn) {
                this.absOn = absOn;
            }

            public void setAcOn(final boolean acOn) {
                this.acOn = acOn;
            }

            public void setBrakingOn(final boolean brakingOn) {
                this.brakingOn = brakingOn;
            }

            public void setClearanceLampOn(final boolean clearanceLampOn) {
                this.clearanceLampOn = clearanceLampOn;
            }

            public void setClutchOn(final boolean clutchOn) {
                this.clutchOn = clutchOn;
            }

            public void setDippedHeadLightOn(final boolean dippedHeadLightOn) {
                this.dippedHeadLightOn = dippedHeadLightOn;
            }

            public void setFogLightOn(final boolean fogLightOn) {
                this.fogLightOn = fogLightOn;
            }

            public void setHeaterOn(final boolean heaterOn) {
                this.heaterOn = heaterOn;
            }

            public void setHighBeanOn(final boolean highBeanOn) {
                this.highBeanOn = highBeanOn;
            }

            public void setHornOn(final boolean hornOn) {
                this.hornOn = hornOn;
            }

            public void setLeftTurnSignalOn(final boolean leftTurnSignalOn) {
                this.leftTurnSignalOn = leftTurnSignalOn;
            }

            public void setNoUsed(final int noUsed) {
                this.noUsed = noUsed;
            }

            public void setNullOn(final boolean nullOn) {
                this.nullOn = nullOn;
            }

            public void setRetarderOn(final boolean retarderOn) {
                this.retarderOn = retarderOn;
            }

            public void setReverseOn(final boolean reverseOn) {
                this.reverseOn = reverseOn;
            }

            public void setRightTurnSignalOn(final boolean rightTurnSignalOn) {
                this.rightTurnSignalOn = rightTurnSignalOn;
            }
        }

        public static class IllegalDrivingTimeComponent {
            public static enum IllegalDrivingTimeStatus implements IContactMessageFieldType {
                LESS(0),
                OVER(1);

                private final int value;

                private IllegalDrivingTimeStatus(final int value) {
                    this.value = value;
                }

                @Override
                public int getValue() {
                    return value;
                }
            }

            @ContactMessageField(fieldType = FieldType.DWORD, order = 1)
            private int routeId;

            @ContactMessageField(fieldType = FieldType.WORD, order = 2)
            private int drivingSeconds;

            @ContactMessageField(fieldType = FieldType.BYTE, order = 3)
            private IllegalDrivingTimeStatus result;

            public int getDrivingSeconds() {
                return drivingSeconds;
            }

            public IllegalDrivingTimeStatus getResult() {
                return result;
            }

            public int getRouteId() {
                return routeId;
            }

            public void setDrivingSeconds(final int drivingSeconds) {
                this.drivingSeconds = drivingSeconds;
            }

            public void setResult(final IllegalDrivingTimeStatus result) {
                this.result = result;
            }

            public void setRouteId(final int routeId) {
                this.routeId = routeId;
            }
        }

        public static class InOutAreaComponent {
            public static enum Direction implements IContactMessageFieldType {
                IN(0),
                OUT(1);

                private final int value;

                private Direction(final int value) {
                    this.value = value;
                }

                @Override
                public int getValue() {
                    return value;
                }
            }

            public static enum PositionType implements IContactMessageFieldType {
                ROUND_AREA(1),
                SQUARE_AREA(2),
                POLYGON_AREA(3),
                ROUTE(4);

                private final int value;

                private PositionType(final int value) {
                    this.value = value;
                }

                @Override
                public int getValue() {
                    return value;
                }
            }

            @ContactMessageField(fieldType = FieldType.BYTE, order = 1)
            private PositionType positionType;

            @ContactMessageField(fieldType = FieldType.DWORD, order = 2)
            private int areaOrRouteId;

            @ContactMessageField(fieldType = FieldType.BYTE, order = 3)
            private Direction direction;

            public int getAreaOrRouteId() {
                return areaOrRouteId;
            }

            public Direction getDirection() {
                return direction;
            }

            public PositionType getPositionType() {
                return positionType;
            }

            public void setAreaOrRouteId(final int areaOrRouteId) {
                this.areaOrRouteId = areaOrRouteId;
            }

            public void setDirection(final Direction direction) {
                this.direction = direction;
            }

            public void setPositionType(final PositionType positionType) {
                this.positionType = positionType;
            }
        }

        public static class IOStatusStruct {
            @ContactMessageStructField(fieldType = StructFieldType.FLAG, order = 1)
            private boolean deepSleep;

            @ContactMessageStructField(fieldType = StructFieldType.FLAG, order = 2)
            private boolean sleep;

            @ContactMessageStructField(fieldType = StructFieldType.NIL, length = 14, order = 3)
            private int notUsed;

            public int getNotUsed() {
                return notUsed;
            }

            public boolean isDeepSleep() {
                return deepSleep;
            }

            public boolean isSleep() {
                return sleep;
            }

            public void setDeepSleep(final boolean deepSleep) {
                this.deepSleep = deepSleep;
            }

            public void setNotUsed(final int notUsed) {
                this.notUsed = notUsed;
            }

            public void setSleep(final boolean sleep) {
                this.sleep = sleep;
            }
        }

        public static class OverspeedComponent {
            public static enum PositionType implements IContactMessageFieldType {
                UNKNOWN(0),
                ROUND_AREA(1),
                SQUARE_AREA(2),
                POLYGON_AREA(3),
                ROUTE(4);

                private final int value;

                private PositionType(final int value) {
                    this.value = value;
                }

                @Override
                public int getValue() {
                    return value;
                }
            }

            @ContactMessageField(fieldType = FieldType.BYTE, order = 1)
            private PositionType positionType;

            @ContactMessageField(fieldType = FieldType.DWORD, getRequiredFrom = "positionType", order = 2)
            private int areaOrRouteId;
        }

        private final int id;

        private final int defaultLength;

        private final Class<?> messageType;

        private final FieldType fieldType;

        private TerminalUploadPositionRequestAdditionalMessageKey(final int id, final int defaultLength, final FieldType fieldType) {
            this(id, defaultLength, fieldType, Object.class);
        }

        private TerminalUploadPositionRequestAdditionalMessageKey(final int id, final int defaultLength, final FieldType fieldType,
                final Class<?> messageType) {
            this.id = id;
            this.defaultLength = defaultLength;
            this.fieldType = fieldType;
            this.messageType = messageType;
        }

        @Override
        public int getDefaultLength() {
            return defaultLength;
        }

        @Override
        public FieldType getFieldType() {
            return fieldType;
        }

        @Override
        public int getId() {
            return id;
        }

        @Override
        public Class<?> getMessageType() {
            return messageType;
        }
    }

    public static class WarningStruct {
        @ContactMessageStructField(fieldType = StructFieldType.FLAG, order = 1)
        private boolean emergency;

        @ContactMessageStructField(fieldType = StructFieldType.FLAG, order = 2)
        private boolean overspeed;

        @ContactMessageStructField(fieldType = StructFieldType.FLAG, order = 3)
        private boolean fatigueDriving;

        @ContactMessageStructField(fieldType = StructFieldType.FLAG, order = 4)
        private boolean earlyWarning;

        @ContactMessageStructField(fieldType = StructFieldType.FLAG, order = 5)
        private boolean gnssBroken;

        @ContactMessageStructField(fieldType = StructFieldType.FLAG, order = 6)
        private boolean gnssDisconnected;

        @ContactMessageStructField(fieldType = StructFieldType.FLAG, order = 7)
        private boolean gnssShortCircuit;

        @ContactMessageStructField(fieldType = StructFieldType.FLAG, order = 8)
        private boolean terminalPowerSupplyUnderVoltage;

        @ContactMessageStructField(fieldType = StructFieldType.FLAG, order = 9)
        private boolean terminalPowerOff;

        @ContactMessageStructField(fieldType = StructFieldType.FLAG, order = 10)
        private boolean screenBroken;

        @ContactMessageStructField(fieldType = StructFieldType.FLAG, order = 11)
        private boolean ttsBroken;

        @ContactMessageStructField(fieldType = StructFieldType.FLAG, order = 12)
        private boolean cameraBroken;

        @ContactMessageStructField(fieldType = StructFieldType.FLAG, order = 13)
        private boolean icBroken;

        @ContactMessageStructField(fieldType = StructFieldType.FLAG, order = 14)
        private boolean overspeedEarlyWarning;

        @ContactMessageStructField(fieldType = StructFieldType.FLAG, order = 15)
        private boolean fatigueDrivingEarlyWarning;

        @ContactMessageStructField(fieldType = StructFieldType.NIL, length = 3, order = 16)
        private int notUsed1;

        @ContactMessageStructField(fieldType = StructFieldType.FLAG, order = 17)
        private boolean overtime;

        @ContactMessageStructField(fieldType = StructFieldType.FLAG, order = 18)
        private boolean parkingOvertime;

        @ContactMessageStructField(fieldType = StructFieldType.FLAG, order = 19)
        private boolean inOutArea;

        @ContactMessageStructField(fieldType = StructFieldType.FLAG, order = 20)
        private boolean inOutRoute;

        @ContactMessageStructField(fieldType = StructFieldType.FLAG, order = 21)
        private boolean abnormalRouteTime;

        @ContactMessageStructField(fieldType = StructFieldType.FLAG, order = 22)
        private boolean outOfRoute;

        @ContactMessageStructField(fieldType = StructFieldType.FLAG, order = 23)
        private boolean vvsBroken;

        @ContactMessageStructField(fieldType = StructFieldType.FLAG, order = 24)
        private boolean abnormalOil;

        @ContactMessageStructField(fieldType = StructFieldType.FLAG, order = 25)
        private boolean stolen;

        @ContactMessageStructField(fieldType = StructFieldType.FLAG, order = 26)
        private boolean illegalIgnition;

        @ContactMessageStructField(fieldType = StructFieldType.FLAG, order = 27)
        private boolean illegalMoving;

        @ContactMessageStructField(fieldType = StructFieldType.FLAG, order = 28)
        private boolean hitEarlyWarning;

        @ContactMessageStructField(fieldType = StructFieldType.FLAG, order = 29)
        private boolean rolloverEarlyWarning;

        @ContactMessageStructField(fieldType = StructFieldType.FLAG, order = 29)
        private boolean illegalDoorOpen;

        public int getNotUsed1() {
            return notUsed1;
        }

        public boolean isAbnormalOil() {
            return abnormalOil;
        }

        public boolean isAbnormalRouteTime() {
            return abnormalRouteTime;
        }

        public boolean isCameraBroken() {
            return cameraBroken;
        }

        public boolean isEarlyWarning() {
            return earlyWarning;
        }

        public boolean isEmergency() {
            return emergency;
        }

        public boolean isFatigueDriving() {
            return fatigueDriving;
        }

        public boolean isFatigueDrivingEarlyWarning() {
            return fatigueDrivingEarlyWarning;
        }

        public boolean isGnssBroken() {
            return gnssBroken;
        }

        public boolean isGnssDisconnected() {
            return gnssDisconnected;
        }

        public boolean isGnssShortCircuit() {
            return gnssShortCircuit;
        }

        public boolean isHitEarlyWarning() {
            return hitEarlyWarning;
        }

        public boolean isIcBroken() {
            return icBroken;
        }

        public boolean isIllegalDoorOpen() {
            return illegalDoorOpen;
        }

        public boolean isIllegalIgnition() {
            return illegalIgnition;
        }

        public boolean isIllegalMoving() {
            return illegalMoving;
        }

        public boolean isInOutArea() {
            return inOutArea;
        }

        public boolean isInOutRoute() {
            return inOutRoute;
        }

        public boolean isOutOfRoute() {
            return outOfRoute;
        }

        public boolean isOverspeed() {
            return overspeed;
        }

        public boolean isOverspeedEarlyWarning() {
            return overspeedEarlyWarning;
        }

        public boolean isOvertime() {
            return overtime;
        }

        public boolean isParkingOvertime() {
            return parkingOvertime;
        }

        public boolean isRolloverEarlyWarning() {
            return rolloverEarlyWarning;
        }

        public boolean isScreenBroken() {
            return screenBroken;
        }

        public boolean isStolen() {
            return stolen;
        }

        public boolean isTerminalPowerOff() {
            return terminalPowerOff;
        }

        public boolean isTerminalPowerSupplyUnderVoltage() {
            return terminalPowerSupplyUnderVoltage;
        }

        public boolean isTtsBroken() {
            return ttsBroken;
        }

        public boolean isVvsBroken() {
            return vvsBroken;
        }

        public void setAbnormalOil(final boolean abnormalOil) {
            this.abnormalOil = abnormalOil;
        }

        public void setAbnormalRouteTime(final boolean abnormalRouteTime) {
            this.abnormalRouteTime = abnormalRouteTime;
        }

        public void setCameraBroken(final boolean cameraBroken) {
            this.cameraBroken = cameraBroken;
        }

        public void setEarlyWarning(final boolean earlyWarning) {
            this.earlyWarning = earlyWarning;
        }

        public void setEmergency(final boolean emergency) {
            this.emergency = emergency;
        }

        public void setFatigueDriving(final boolean fatigueDriving) {
            this.fatigueDriving = fatigueDriving;
        }

        public void setFatigueDrivingEarlyWarning(final boolean fatigueDrivingEarlyWarning) {
            this.fatigueDrivingEarlyWarning = fatigueDrivingEarlyWarning;
        }

        public void setGnssBroken(final boolean gnssBroken) {
            this.gnssBroken = gnssBroken;
        }

        public void setGnssDisconnected(final boolean gnssDisconnected) {
            this.gnssDisconnected = gnssDisconnected;
        }

        public void setGnssShortCircuit(final boolean gnssShortCircuit) {
            this.gnssShortCircuit = gnssShortCircuit;
        }

        public void setHitEarlyWarning(final boolean hitEarlyWarning) {
            this.hitEarlyWarning = hitEarlyWarning;
        }

        public void setIcBroken(final boolean icBroken) {
            this.icBroken = icBroken;
        }

        public void setIllegalDoorOpen(final boolean illegalDoorOpen) {
            this.illegalDoorOpen = illegalDoorOpen;
        }

        public void setIllegalIgnition(final boolean illegalIgnition) {
            this.illegalIgnition = illegalIgnition;
        }

        public void setIllegalMoving(final boolean illegalMoving) {
            this.illegalMoving = illegalMoving;
        }

        public void setInOutArea(final boolean inOutArea) {
            this.inOutArea = inOutArea;
        }

        public void setInOutRoute(final boolean inOutRoute) {
            this.inOutRoute = inOutRoute;
        }

        public void setNotUsed1(final int notUsed1) {
            this.notUsed1 = notUsed1;
        }

        public void setOutOfRoute(final boolean outOfRoute) {
            this.outOfRoute = outOfRoute;
        }

        public void setOverspeed(final boolean overspeed) {
            this.overspeed = overspeed;
        }

        public void setOverspeedEarlyWarning(final boolean overspeedEarlyWarning) {
            this.overspeedEarlyWarning = overspeedEarlyWarning;
        }

        public void setOvertime(final boolean overtime) {
            this.overtime = overtime;
        }

        public void setParkingOvertime(final boolean parkingOvertime) {
            this.parkingOvertime = parkingOvertime;
        }

        public void setRolloverEarlyWarning(final boolean rolloverEarlyWarning) {
            this.rolloverEarlyWarning = rolloverEarlyWarning;
        }

        public void setScreenBroken(final boolean screenBroken) {
            this.screenBroken = screenBroken;
        }

        public void setStolen(final boolean stolen) {
            this.stolen = stolen;
        }

        public void setTerminalPowerOff(final boolean terminalPowerOff) {
            this.terminalPowerOff = terminalPowerOff;
        }

        public void setTerminalPowerSupplyUnderVoltage(final boolean terminalPowerSupplyUnderVoltage) {
            this.terminalPowerSupplyUnderVoltage = terminalPowerSupplyUnderVoltage;
        }

        public void setTtsBroken(final boolean ttsBroken) {
            this.ttsBroken = ttsBroken;
        }

        public void setVvsBroken(final boolean vvsBroken) {
            this.vvsBroken = vvsBroken;
        }
    }

    @ContactMessageField(fieldType = FieldType.STRUCT, length = 4, order = 1)
    private WarningStruct warning;

    @ContactMessageField(fieldType = FieldType.STRUCT, length = 4, order = 2)
    private StatusStruct status;

    @ContactMessageField(fieldType = FieldType.DWORD, order = 3)
    private int y;

    @ContactMessageField(fieldType = FieldType.DWORD, order = 4)
    private int x;

    @ContactMessageField(fieldType = FieldType.WORD, order = 5)
    private int z;

    @ContactMessageField(fieldType = FieldType.WORD, order = 6)
    private int speed;

    @ContactMessageField(fieldType = FieldType.WORD, order = 7)
    private int direction;

    @ContactMessageField(fieldType = FieldType.BCD, order = 8, length = 6, padLetter = '0')
    private String date;

    @ContactMessageField(fieldType = FieldType.ADDITIONALS, order = 9, additionalEnumClass = TerminalUploadPositionRequestAdditionalMessageKey.class, length = 1)
    private List<Tuple3<IAdditionalMessageKey, Integer, Object>> addtionalMessages;

    public List<Tuple3<IAdditionalMessageKey, Integer, Object>> getAddtionalMessages() {
        if (addtionalMessages == null) {
            addtionalMessages = new LinkedList<>();
        }
        return addtionalMessages;
    }

    public String getDate() {
        return date;
    }

    public int getDirection() {
        return direction;
    }

    public int getSpeed() {
        return speed;
    }

    public StatusStruct getStatus() {
        if (status == null) {
            status = new StatusStruct();
        }
        return status;
    }

    public WarningStruct getWarning() {
        if (warning == null) {
            warning = new WarningStruct();
        }
        return warning;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return z;
    }

    public void setAddtionalMessages(final List<Tuple3<IAdditionalMessageKey, Integer, Object>> addtionalMessages) {
        this.addtionalMessages = addtionalMessages;
    }

    public void setDate(final String date) {
        this.date = date;
    }

    public void setDirection(final int direction) {
        this.direction = direction;
    }

    public void setSpeed(final int speed) {
        this.speed = speed;
    }

    public void setStatus(final StatusStruct status) {
        this.status = status;
    }

    public void setWarning(final WarningStruct warning) {
        this.warning = warning;
    }

    public void setX(final int x) {
        this.x = x;
    }

    public void setY(final int y) {
        this.y = y;
    }

    public void setZ(final int z) {
        this.z = z;
    }

    @Override
    protected int getDefaultMessageId() {
        return 0x0200;
    }
}
