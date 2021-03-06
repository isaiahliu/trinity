package org.trinity.common.dto.response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.trinity.common.dto.IResponse;
import org.trinity.common.dto.object.ErrorDto;
import org.trinity.common.dto.object.MetaDto;

/**
 * @author Isaiah Liu
 *
 * @param <TDto>
 */
public abstract class AbstractResponse<TDto> implements IResponse {
    private List<ErrorDto> errors;
    private List<TDto> data;
    private MetaDto meta;
    private Map<String, Object> extraData;

    public <T extends TDto> void addData(final List<T> data) {
        getData().addAll(data);
    }

    public void addData(final TDto data) {
        getData().add(data);
    }

    public void addError(final String code, final String description) {
        getErrors().add(new ErrorDto(code, description));
    }

    public void addExtraData(final String key, final Object value) {
        getExtraData().put(key, value);
    }

    public List<TDto> getData() {
        if (data == null) {
            data = new ArrayList<>();
        }
        return data;
    }

    @Override
    public List<ErrorDto> getErrors() {
        if (errors == null) {
            errors = new ArrayList<>();
        }
        return errors;
    }

    public Map<String, Object> getExtraData() {
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

    public void setData(final List<TDto> data) {
        this.data = data;
    }

    public void setErrors(final List<ErrorDto> errors) {
        this.errors = errors;
    }

    public void setExtraData(final Map<String, Object> extraData) {
        this.extraData = extraData;
    }

    public void setMeta(final MetaDto meta) {
        this.meta = meta;
    }
}
