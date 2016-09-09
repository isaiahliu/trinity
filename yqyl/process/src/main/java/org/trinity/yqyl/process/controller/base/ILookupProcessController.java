package org.trinity.yqyl.process.controller.base;

import java.util.List;

import org.trinity.process.controller.IProcessController;
import org.trinity.yqyl.common.message.dto.domain.LookupDto;

public interface ILookupProcessController extends IProcessController {
    List<LookupDto> getLookupsByType(String lookupType);

    void refresh();
}
