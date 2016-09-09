package org.trinity.yqyl.repository.business.dataaccess;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.trinity.yqyl.repository.business.entity.Announcement;

public interface IAnnouncementRepository extends CrudRepository<Announcement, Long>, PagingAndSortingRepository<Announcement, Long> {
}