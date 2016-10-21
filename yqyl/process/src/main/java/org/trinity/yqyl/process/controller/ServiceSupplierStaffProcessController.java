package org.trinity.yqyl.process.controller;

import org.springframework.stereotype.Service;
import org.trinity.yqyl.common.message.dto.domain.ServiceSupplierStaffDto;
import org.trinity.yqyl.common.message.dto.domain.ServiceSupplierStaffSearchingDto;
import org.trinity.yqyl.common.message.exception.ErrorMessage;
import org.trinity.yqyl.process.controller.base.AbstractAutowiredCrudProcessController;
import org.trinity.yqyl.process.controller.base.IServiceSupplierStaffProcessController;
import org.trinity.yqyl.repository.business.dataaccess.IServiceSupplierStaffRepository;
import org.trinity.yqyl.repository.business.entity.ServiceSupplierStaff;

@Service
public class ServiceSupplierStaffProcessController
      extends AbstractAutowiredCrudProcessController<ServiceSupplierStaff, ServiceSupplierStaffDto, ServiceSupplierStaffSearchingDto, IServiceSupplierStaffRepository>
      implements IServiceSupplierStaffProcessController {
  public ServiceSupplierStaffProcessController() {
      super(ServiceSupplierStaff.class, ErrorMessage.UNABLE_TO_FIND_SERVICE_SUPPLIER_STAFF);
  }
}

