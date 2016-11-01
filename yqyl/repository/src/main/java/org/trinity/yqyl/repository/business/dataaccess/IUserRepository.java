package org.trinity.yqyl.repository.business.dataaccess;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.trinity.yqyl.repository.business.entity.User;

public interface IUserRepository extends CrudRepository<User, Long>, JpaSpecificationExecutor<User> {
    User findOneByUsername(String username);
}
