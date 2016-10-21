package org.trinity.yqyl.process.controller.base;

import java.util.List;

import org.trinity.common.exception.IException;
import org.trinity.process.controller.ICrudProcessController;
import org.trinity.yqyl.common.message.dto.domain.UserRealnameDto;
import org.trinity.yqyl.common.message.dto.domain.UserRealnameSearchingDto;

public interface IUserRealnameProcessController extends ICrudProcessController<UserRealnameDto, UserRealnameSearchingDto> {

    List<UserRealnameDto> getMe(UserRealnameSearchingDto dto) throws IException;
}
