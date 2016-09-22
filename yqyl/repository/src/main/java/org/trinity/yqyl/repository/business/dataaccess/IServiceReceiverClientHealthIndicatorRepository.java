package org.trinity.yqyl.repository.business.dataaccess;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.trinity.yqyl.repository.business.entity.ServiceReceiverClientHealthIndicator;

public interface IServiceReceiverClientHealthIndicatorRepository extends CrudRepository<ServiceReceiverClientHealthIndicator, Long>, PagingAndSortingRepository<ServiceReceiverClientHealthIndicator, Long> {}