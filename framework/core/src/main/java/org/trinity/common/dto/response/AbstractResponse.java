package org.trinity.common.dto.response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.trinity.common.dto.IResponse;
import org.trinity.common.dto.object.ErrorDto;
import org.trinity.common.dto.object.MetaDto;

public abstract class AbstractResponse<T> implements IResponse {
    private List<ErrorDto> errors;
    private List<T> data;
    private MetaDto meta;
    private Map<String, String> extraData;

    public void addData(final T data) {
        getData().add(data);
    }

    public void addError(final String code, final String description) {
        getErrors().add(new ErrorDto(code, description));
    }

    public List<T> getData() {
        if (data == null) {
            data = new ArrayList<>();
        }
        return data;
    }

    public List<ErrorDto> getErrors() {
        if (errors == null) {
            errors = new ArrayList<>();
        }
        return errors;
    }

    public Map<String, String> getExtraData() {
        if (extraData == null) {
            extraData = new HashMap<>();
        }
        return extraData;
    }

    public MetaDto getMeta() {
        if (meta == null) {
            meta = new MetaDto();
        }
        return meta;
    }

    public void setData(final List<T> data) {
        this.data = data;
    }

    public void setErrors(final List<ErrorDto> errors) {
        this.errors = errors;
    }

    public void setExtraData(final Map<String, String> extraData) {
        this.extraData = extraData;
    }

    public void setMeta(final MetaDto meta) {
        this.meta = meta;
    }
}
