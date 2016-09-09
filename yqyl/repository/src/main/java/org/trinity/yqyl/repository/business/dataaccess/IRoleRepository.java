package org.trinity.yqyl.repository.business.dataaccess;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.trinity.yqyl.repository.business.entity.Role;

public interface IRoleRepository extends CrudRepository<Role, Long>, PagingAndSortingRepository<Role, Long> {
}