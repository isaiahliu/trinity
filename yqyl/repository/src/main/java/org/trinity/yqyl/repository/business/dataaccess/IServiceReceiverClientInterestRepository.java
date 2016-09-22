package org.trinity.yqyl.repository.business.dataaccess;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.trinity.yqyl.repository.business.entity.ServiceReceiverClientInterest;

public interface IServiceReceiverClientInterestRepository extends CrudRepository<ServiceReceiverClientInterest, Long>, PagingAndSortingRepository<ServiceReceiverClientInterest, Long> {}