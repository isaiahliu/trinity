package org.trinity.yqyl.process.controller.base;

import org.trinity.common.exception.IException;
import org.trinity.process.controller.ICrudProcessController;
import org.trinity.yqyl.common.message.dto.domain.AccessrightDto;
import org.trinity.yqyl.common.message.dto.domain.AccessrightSearchingDto;

public interface IAccessrightProcessController extends ICrudProcessController<AccessrightDto, AccessrightSearchingDto> {
    void refreshAll() throws IException;
}
