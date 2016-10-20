package org.trinity.process.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.trinity.common.exception.IException;

public interface ICrudProcessController<TDto, TSearchingDto> extends IProcessController {
    List<TDto> addAll(List<TDto> dtos) throws IException;

    void delete(Long id) throws IException;

    Page<TDto> getAll(TSearchingDto searchingData) throws IException;

    List<TDto> getMe(TSearchingDto data) throws IException;

    TDto getOne(Long id) throws IException;

    void updateAll(List<TDto> dtos) throws IException;
}
