package org.trinity.yqyl.common.message.dto.domain;

import org.trinity.common.dto.domain.AbstractBusinessDto;
import org.trinity.common.dto.object.LookupDto;

public class ServiceOrderAppraiseDto extends AbstractBusinessDto {
    private Integer attitudeRate;

    private String comment;

    private Integer onTimeRate;

    private Integer qualityRate;

    private Integer staffRate;

    private LookupDto status;

    public Integer getAttitudeRate() {
        return attitudeRate;
    }

    public String getComment() {
        return comment;
    }

    public Integer getOnTimeRate() {
        return onTimeRate;
    }

    public Integer getQualityRate() {
        return qualityRate;
    }

    public Integer getStaffRate() {
        return staffRate;
    }

    public LookupDto getStatus() {
        return status;
    }

    public void setAttitudeRate(final Integer attitudeRate) {
        this.attitudeRate = attitudeRate;
    }

    public void setComment(final String comment) {
        this.comment = comment;
    }

    public void setOnTimeRate(final Integer onTimeRate) {
        this.onTimeRate = onTimeRate;
    }

    public void setQualityRate(final Integer qualityRate) {
        this.qualityRate = qualityRate;
    }

    public void setStaffRate(final Integer staffRate) {
        this.staffRate = staffRate;
    }

    public void setStatus(final LookupDto status) {
        this.status = status;
    }
}
