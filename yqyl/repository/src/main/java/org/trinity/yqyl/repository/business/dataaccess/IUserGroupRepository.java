package org.trinity.yqyl.repository.business.dataaccess;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.trinity.yqyl.repository.business.entity.UserGroup;

public interface IUserGroupRepository extends CrudRepository<UserGroup, Long>, PagingAndSortingRepository<UserGroup, Long> {
}