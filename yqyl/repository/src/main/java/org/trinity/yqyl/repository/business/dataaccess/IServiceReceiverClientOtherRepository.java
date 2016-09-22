package org.trinity.yqyl.repository.business.dataaccess;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.trinity.yqyl.repository.business.entity.ServiceReceiverClientOther;

public interface IServiceReceiverClientOtherRepository extends CrudRepository<ServiceReceiverClientOther, Long>, PagingAndSortingRepository<ServiceReceiverClientOther, Long> {}