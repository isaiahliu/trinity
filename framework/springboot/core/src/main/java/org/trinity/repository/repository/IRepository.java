package org.trinity.repository.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.trinity.common.dto.object.ISearchingDto;

public interface IRepository<TEntity, TSearchingDto extends ISearchingDto>
        extends CrudRepository<TEntity, Long>, PagingAndSortingRepository<TEntity, Long> {
    Page<TEntity> query(TSearchingDto searchingDto, Pageable pagable);
}
