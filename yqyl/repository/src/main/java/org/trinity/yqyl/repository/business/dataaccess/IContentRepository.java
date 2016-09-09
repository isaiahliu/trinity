package org.trinity.yqyl.repository.business.dataaccess;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.trinity.yqyl.repository.business.entity.Content;

public interface IContentRepository extends CrudRepository<Content, Long>, PagingAndSortingRepository<Content, Long> {
    Content findOneByUuid(String uuid);
}
