package org.trinity.yqyl.repository.business.dataaccess;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.trinity.yqyl.repository.business.entity.ServiceSupplierClientAccount;

public interface IServiceSupplierClientAccountRepository extends CrudRepository<ServiceSupplierClientAccount, Long>, PagingAndSortingRepository<ServiceSupplierClientAccount, Long> {}