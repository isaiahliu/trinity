package org.trinity.yqyl.repository.business.dataaccess;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.trinity.yqyl.repository.business.entity.Message;

public interface IMessageRepository extends CrudRepository<Message, Long>, PagingAndSortingRepository<Message, Long> {
}