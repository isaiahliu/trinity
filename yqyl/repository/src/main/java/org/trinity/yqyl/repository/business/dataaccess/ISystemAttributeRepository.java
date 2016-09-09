package org.trinity.yqyl.repository.business.dataaccess;

import org.springframework.data.repository.CrudRepository;
import org.trinity.yqyl.common.message.lookup.SystemAttributeKey;
import org.trinity.yqyl.repository.business.entity.SystemAttribute;

public interface ISystemAttributeRepository extends CrudRepository<SystemAttribute, Long> {
	SystemAttribute findOneByKey(SystemAttributeKey key);
}
