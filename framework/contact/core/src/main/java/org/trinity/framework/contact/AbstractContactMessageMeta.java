package org.trinity.framework.contact;

public abstract class AbstractContactMessageMeta implements IContactMessageMeta {

    private byte[] originalMessageBytes;
    private int id;

    private int serialNumber = -1;
    private int bodyLength;
    private int firstPackageSerialNumber;
    private int packageCount;
    private int packageIndex;

    public AbstractContactMessageMeta(final int id) {
        this.id = id;
    }

    @Override
    public int getBodyLength() {
        return bodyLength;
    }

    @Override
    public int getFirstPackageSerialNumber() {
        return firstPackageSerialNumber;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public byte[] getOriginalMessageBytes() {
        return originalMessageBytes;
    }

    @Override
    public int getPackageCount() {
        return packageCount;
    }

    @Override
    public int getPackageIndex() {
        return packageIndex;
    }

    @Override
    public int getSerialNumber() {
        return serialNumber;
    }

    @Override
    public void setBodyLength(final int bodyLength) {
        this.bodyLength = bodyLength;
    }

    @Override
    public void setFirstPackageSerialNumber(final int firstPackageSerialNumber) {
        this.firstPackageSerialNumber = firstPackageSerialNumber;
    }

    @Override
    public void setId(final int id) {
        this.id = id;
    }

    @Override
    public void setOriginalMessageBytes(final byte[] originalMessageBytes) {
        this.originalMessageBytes = originalMessageBytes;
    }

    @Override
    public void setPackageCount(final int packageCount) {
        this.packageCount = packageCount;
    }

    @Override
    public void setPackageIndex(final int packageIndex) {
        this.packageIndex = packageIndex;
    }

    @Override
    public void setSerialNumber(final int serialNumber) {
        this.serialNumber = serialNumber;
    }
}
