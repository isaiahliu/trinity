package org.trinity.yqyl.repository.business.dataaccess;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.trinity.yqyl.repository.business.entity.ServiceSubOrder;

public interface IServiceSubOrderRepository extends CrudRepository<ServiceSubOrder, Long>, PagingAndSortingRepository<ServiceSubOrder, Long> {}