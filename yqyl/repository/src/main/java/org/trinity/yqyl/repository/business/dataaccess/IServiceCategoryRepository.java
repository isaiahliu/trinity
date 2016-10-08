package org.trinity.yqyl.repository.business.dataaccess;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.trinity.yqyl.repository.business.entity.ServiceCategory;

public interface IServiceCategoryRepository
		extends CrudRepository<ServiceCategory, Long>, PagingAndSortingRepository<ServiceCategory, Long> {
	List<ServiceCategory> findAllByParent(ServiceCategory parent);
}