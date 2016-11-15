package org.trinity.framework.contact;

public interface IContactMessageMeta {
    int getBodyLength();

    int getFirstPackageSerialNumber();

    int getId();

    byte[] getOriginalMessageBytes();

    int getPackageCount();

    int getPackageIndex();

    int getSerialNumber();

    void setBodyLength(final int bodyLength);

    void setFirstPackageSerialNumber(final int firstPackageSerialNumber);

    void setId(final int id);

    void setOriginalMessageBytes(byte[] originalMessageBytes);

    void setPackageCount(final int packageCount);

    void setPackageIndex(final int packageIndex);

    void setSerialNumber(final int serialNumber);
}
