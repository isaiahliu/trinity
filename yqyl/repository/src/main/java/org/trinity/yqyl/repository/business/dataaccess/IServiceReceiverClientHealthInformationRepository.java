package org.trinity.yqyl.repository.business.dataaccess;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.trinity.yqyl.repository.business.entity.ServiceReceiverClientHealthInformation;

public interface IServiceReceiverClientHealthInformationRepository extends CrudRepository<ServiceReceiverClientHealthInformation, Long>, PagingAndSortingRepository<ServiceReceiverClientHealthInformation, Long> {}