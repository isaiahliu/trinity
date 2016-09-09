package org.trinity.yqyl.repository.business.dataaccess;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.trinity.yqyl.repository.business.entity.AccountBalance;

public interface IAccountBalanceRepository extends CrudRepository<AccountBalance, Long>, PagingAndSortingRepository<AccountBalance, Long> {
}