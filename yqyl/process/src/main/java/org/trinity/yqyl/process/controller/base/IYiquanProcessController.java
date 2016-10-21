package org.trinity.yqyl.process.controller.base;

import java.util.List;

import org.trinity.common.exception.IException;
import org.trinity.process.controller.ICrudProcessController;
import org.trinity.yqyl.common.message.dto.domain.YiquanDto;
import org.trinity.yqyl.common.message.dto.domain.YiquanSearchingDto;

public interface IYiquanProcessController extends ICrudProcessController<YiquanDto, YiquanSearchingDto> {
    void bindMe(YiquanDto yiquanCode) throws IException;

    List<YiquanDto> getMe(YiquanSearchingDto dto) throws IException;
}
