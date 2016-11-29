package org.trinity.yqyl.process.controller.base;

import org.trinity.common.exception.IException;
import org.trinity.process.controller.ICrudProcessController;
import org.trinity.yqyl.common.message.dto.domain.ServiceReceiverClientYiquanDto;
import org.trinity.yqyl.common.message.dto.domain.ServiceReceiverClientYiquanSearchingDto;

public interface IServiceReceiverClientYiquanProcessController extends ICrudProcessController<ServiceReceiverClientYiquanDto, ServiceReceiverClientYiquanSearchingDto> {
    void bindMe(ServiceReceiverClientYiquanDto yiquanCode) throws IException;

    void topupMe(ServiceReceiverClientYiquanDto yiquanDto) throws IException;
}
