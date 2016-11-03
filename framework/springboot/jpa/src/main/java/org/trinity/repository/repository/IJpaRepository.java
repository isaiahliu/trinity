package org.trinity.repository.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.trinity.common.dto.object.ISearchingDto;

public interface IJpaRepository<TEntity, TSearchingDto extends ISearchingDto>
        extends IRepository<TEntity, TSearchingDto>, JpaSpecificationExecutor<TEntity> {

}
