package org.trinity.yqyl.repository.business.dataaccess;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.trinity.yqyl.repository.business.entity.ServiceOrderAppraise;

public interface IServiceOrderAppraiseRepository extends CrudRepository<ServiceOrderAppraise, Long>, PagingAndSortingRepository<ServiceOrderAppraise, Long> {}