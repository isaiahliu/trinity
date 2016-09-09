package org.trinity.yqyl.repository.business.dataaccess;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.trinity.yqyl.repository.business.entity.Service;

public interface IServiceRepository extends CrudRepository<Service, Long>, PagingAndSortingRepository<Service, Long> {
}