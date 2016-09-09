package org.trinity.yqyl.repository.business.dataaccess;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.trinity.yqyl.common.message.lookup.AccessRight;
import org.trinity.yqyl.repository.business.entity.Accessright;

public interface IAccessrightRepository extends CrudRepository<Accessright, Long>, PagingAndSortingRepository<Accessright, Long> {
    Accessright findOneByName(AccessRight name);
}
