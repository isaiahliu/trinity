package org.trinity.yqyl.common.message.dto.domain;

import org.trinity.common.dto.domain.AbstractBusinessDto;
import org.trinity.common.dto.object.LookupDto;

public class ServiceReceiverClientDto extends AbstractBusinessDto {

    private String address;

    private String cellphoneNo;

    private String dob;

    private String email;

    private LookupDto gender;

    private String homephoneNo;

    private String identityCard;

    private String name;

    private LookupDto status;

    private LookupDto familyRelationship;

    public String getAddress() {
        return address;
    }

    public String getCellphoneNo() {
        return cellphoneNo;
    }

    public String getDob() {
        return dob;
    }

    public String getEmail() {
        return email;
    }

    public LookupDto getFamilyRelationship() {
        return familyRelationship;
    }

    public LookupDto getGender() {
        return gender;
    }

    public String getHomephoneNo() {
        return homephoneNo;
    }

    public String getIdentityCard() {
        return identityCard;
    }

    public String getName() {
        return name;
    }

    public LookupDto getStatus() {
        return status;
    }

    public void setAddress(final String address) {
        this.address = address;
    }

    public void setCellphoneNo(final String cellphoneNo) {
        this.cellphoneNo = cellphoneNo;
    }

    public void setDob(final String dob) {
        this.dob = dob;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public void setFamilyRelationship(final LookupDto familyRelationship) {
        this.familyRelationship = familyRelationship;
    }

    public void setGender(final LookupDto gender) {
        this.gender = gender;
    }

    public void setHomephoneNo(final String homephoneNo) {
        this.homephoneNo = homephoneNo;
    }

    public void setIdentityCard(final String identityCard) {
        this.identityCard = identityCard;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public void setStatus(final LookupDto status) {
        this.status = status;
    }
}
