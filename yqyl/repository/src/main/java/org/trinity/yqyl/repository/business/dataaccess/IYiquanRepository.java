package org.trinity.yqyl.repository.business.dataaccess;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.trinity.yqyl.repository.business.entity.Yiquan;

public interface IYiquanRepository extends CrudRepository<Yiquan, Long>, PagingAndSortingRepository<Yiquan, Long> {
    Yiquan findOneByCode(String code);
}
