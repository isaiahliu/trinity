package org.trinity.yqyl.repository.business.dataaccess;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.trinity.yqyl.repository.business.entity.AccountPosting;

public interface IAccountPostingRepository extends CrudRepository<AccountPosting, Long>, PagingAndSortingRepository<AccountPosting, Long> {
}