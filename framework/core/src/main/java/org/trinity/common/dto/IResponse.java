package org.trinity.common.dto;

import java.util.List;

import org.trinity.common.dto.object.ErrorDto;

/**
 * Interface for all responses.
 *
 * @author Isaiah Liu
 */
public interface IResponse {
    List<ErrorDto> getErrors();
}
