package org.trinity.yqyl.repository.business.dataaccess;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.trinity.yqyl.repository.business.entity.ServiceInfo;

public interface IServiceInfoRepository
		extends CrudRepository<ServiceInfo, Long>, PagingAndSortingRepository<ServiceInfo, Long>, JpaSpecificationExecutor<ServiceInfo> {
}
