package org.trinity.yqyl.common.message.dto.domain;

import org.trinity.common.dto.domain.AbstractBusinessDto;

public class ServiceSupplierClientMaterialDto extends AbstractBusinessDto {
    private String applicationCopy;

    private String applicationNo;

    private String businessLicenseCopy;

    private String businessLicenseNo;

    private String contractCopy;

    private String contractNo;

    private String corporateCheckingCopy;

    private String corporateCheckingNo;

    private String jcv;

    private String licenseCopy;

    private String licenseNo;

    public String getApplicationCopy() {
        return applicationCopy;
    }

    public String getApplicationNo() {
        return applicationNo;
    }

    public String getBusinessLicenseCopy() {
        return businessLicenseCopy;
    }

    public String getBusinessLicenseNo() {
        return businessLicenseNo;
    }

    public String getContractCopy() {
        return contractCopy;
    }

    public String getContractNo() {
        return contractNo;
    }

    public String getCorporateCheckingCopy() {
        return corporateCheckingCopy;
    }

    public String getCorporateCheckingNo() {
        return corporateCheckingNo;
    }

    public String getJcv() {
        return jcv;
    }

    public String getLicenseCopy() {
        return licenseCopy;
    }

    public String getLicenseNo() {
        return licenseNo;
    }

    public void setApplicationCopy(final String applicationCopy) {
        this.applicationCopy = applicationCopy;
    }

    public void setApplicationNo(final String applicationNo) {
        this.applicationNo = applicationNo;
    }

    public void setBusinessLicenseCopy(final String businessLicenseCopy) {
        this.businessLicenseCopy = businessLicenseCopy;
    }

    public void setBusinessLicenseNo(final String businessLicenseNo) {
        this.businessLicenseNo = businessLicenseNo;
    }

    public void setContractCopy(final String contractCopy) {
        this.contractCopy = contractCopy;
    }

    public void setContractNo(final String contractNo) {
        this.contractNo = contractNo;
    }

    public void setCorporateCheckingCopy(final String corporateCheckingCopy) {
        this.corporateCheckingCopy = corporateCheckingCopy;
    }

    public void setCorporateCheckingNo(final String corporateCheckingNo) {
        this.corporateCheckingNo = corporateCheckingNo;
    }

    public void setJcv(final String jcv) {
        this.jcv = jcv;
    }

    public void setLicenseCopy(final String licenseCopy) {
        this.licenseCopy = licenseCopy;
    }

    public void setLicenseNo(final String licenseNo) {
        this.licenseNo = licenseNo;
    }
}
