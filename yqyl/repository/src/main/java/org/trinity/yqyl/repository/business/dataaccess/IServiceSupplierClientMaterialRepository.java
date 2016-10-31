package org.trinity.yqyl.repository.business.dataaccess;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.trinity.yqyl.repository.business.entity.ServiceSupplierClientMaterial;

public interface IServiceSupplierClientMaterialRepository extends CrudRepository<ServiceSupplierClientMaterial, Long>, PagingAndSortingRepository<ServiceSupplierClientMaterial, Long> {}