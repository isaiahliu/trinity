package org.trinity.yqyl.common.message.dto.domain;

import java.util.Date;

import org.trinity.common.dto.domain.AbstractBusinessDto;

public class ServiceSupplierStaffDto extends AbstractBusinessDto {
    private String comment;

    private Date dob;

    private String identityCard;

    private String name;

    private String phoneNo;

    private String photo;

    private LookupDto status;

    public String getComment() {
        return comment;
    }

    public Date getDob() {
        return dob;
    }

    public String getIdentityCard() {
        return identityCard;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public String getPhoto() {
        return photo;
    }

    public LookupDto getStatus() {
        return status;
    }

    public void setComment(final String comment) {
        this.comment = comment;
    }

    public void setDob(final Date dob) {
        this.dob = dob;
    }

    public void setIdentityCard(final String identityCard) {
        this.identityCard = identityCard;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public void setPhoneNo(final String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public void setPhoto(final String photo) {
        this.photo = photo;
    }

    public void setStatus(final LookupDto status) {
        this.status = status;
    }

}
