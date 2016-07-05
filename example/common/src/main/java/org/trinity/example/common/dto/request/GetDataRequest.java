package org.trinity.example.common.dto.request;

import org.trinity.common.dto.request.AbstractRequest;

public class GetDataRequest extends AbstractRequest {
    private String data;

    public String getData() {
        return data;
    }

    public void setData(final String data) {
        this.data = data;
    }
}
