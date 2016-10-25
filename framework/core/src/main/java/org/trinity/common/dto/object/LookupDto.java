package org.trinity.common.dto.object;

import org.trinity.message.ILookupMessage;

public class LookupDto implements IDto {
    private String code;
    private String message;

    public LookupDto() {
        super();
    }

    public LookupDto(final ILookupMessage<?> message) {
        this(message.getMessageCode(), "");
    }

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
