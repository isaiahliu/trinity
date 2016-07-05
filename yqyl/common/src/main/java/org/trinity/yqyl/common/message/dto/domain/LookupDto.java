package org.trinity.yqyl.common.message.dto.domain;

public class LookupDto {
    private String code;
    private String message;

    public LookupDto(final String code, final String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public void setCode(final String code) {
        this.code = code;
    }

    public void setMessage(final String message) {
        this.message = message;
    }
}
