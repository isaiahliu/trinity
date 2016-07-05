package org.trinity.yqyl.repository.business.dataaccess;

import org.springframework.data.repository.CrudRepository;
import org.trinity.yqyl.repository.business.entity.Token;

public interface ITokenRepository extends CrudRepository<Token, Long> {
	Token findOneByToken(String token);
}
