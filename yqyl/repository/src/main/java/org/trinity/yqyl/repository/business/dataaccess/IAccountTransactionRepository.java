package org.trinity.yqyl.repository.business.dataaccess;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.trinity.yqyl.repository.business.entity.AccountTransaction;

public interface IAccountTransactionRepository
		extends CrudRepository<AccountTransaction, Long>, PagingAndSortingRepository<AccountTransaction, Long> {
}