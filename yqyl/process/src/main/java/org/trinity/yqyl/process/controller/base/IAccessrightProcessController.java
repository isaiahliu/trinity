package org.trinity.yqyl.process.controller.base;

import java.util.List;

import org.trinity.common.exception.IException;
import org.trinity.process.controller.ICrudProcessController;
import org.trinity.yqyl.common.message.dto.domain.AccessrightDto;
import org.trinity.yqyl.common.message.dto.domain.AccessrightSearchingDto;

public interface IAccessrightProcessController extends ICrudProcessController<AccessrightDto, AccessrightSearchingDto> {
    List<AccessrightDto> getAccessRightTree() throws IException;

    void refreshAll() throws IException;
}
