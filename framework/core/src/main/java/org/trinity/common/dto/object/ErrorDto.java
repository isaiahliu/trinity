package org.trinity.common.dto.object;

public class ErrorDto {
    private String code;
    private String[] parameters;
    private String message;

    public ErrorDto() {
    }

    public ErrorDto(final String code, final String message) {
        this(code, new String[0], message);
    }

    public ErrorDto(final String code, final String[] parameters, final String message) {
        super();
        this.code = code;
        this.parameters = parameters;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String[] getParameters() {
        if (parameters == null) {
            parameters = new String[0];
        }
        return parameters;
    }

    public void setCode(final String code) {
        this.code = code;
    }

    public void setMessage(final String message) {
        this.message = message;
    }

    public void setParameters(final String[] parameters) {
        this.parameters = parameters;
    }
}
