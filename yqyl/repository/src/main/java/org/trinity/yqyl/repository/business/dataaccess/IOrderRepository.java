package org.trinity.yqyl.repository.business.dataaccess;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.trinity.yqyl.repository.business.entity.Order;

public interface IOrderRepository extends CrudRepository<Order, Long>, PagingAndSortingRepository<Order, Long> {
}