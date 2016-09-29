package org.trinity.common.dto.domain;

import javax.validation.constraints.NotNull;

import org.trinity.common.dto.object.IDto;
import org.trinity.common.dto.request.AbstractDataRequest.DeleteData;

public abstract class AbstractBusinessDto implements IDto {
    @NotNull(groups = { DeleteData.class })
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }
}
