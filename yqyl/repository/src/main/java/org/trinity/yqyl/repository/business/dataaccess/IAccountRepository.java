package org.trinity.yqyl.repository.business.dataaccess;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.trinity.yqyl.repository.business.entity.Account;

public interface IAccountRepository extends CrudRepository<Account, Long>, PagingAndSortingRepository<Account, Long> {
}