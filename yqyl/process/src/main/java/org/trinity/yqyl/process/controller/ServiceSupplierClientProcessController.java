package org.trinity.yqyl.process.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.persistence.criteria.Predicate;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.trinity.common.exception.IException;
import org.trinity.yqyl.common.message.dto.domain.ServiceSupplierClientAccountDto;
import org.trinity.yqyl.common.message.dto.domain.ServiceSupplierClientDto;
import org.trinity.yqyl.common.message.dto.domain.ServiceSupplierClientMaterialDto;
import org.trinity.yqyl.common.message.dto.domain.ServiceSupplierClientSearchingDto;
import org.trinity.yqyl.common.message.exception.ErrorMessage;
import org.trinity.yqyl.common.message.lookup.RecordStatus;
import org.trinity.yqyl.common.message.lookup.ServiceSupplierClientStatus;
import org.trinity.yqyl.process.controller.base.AbstractAutowiredCrudProcessController;
import org.trinity.yqyl.process.controller.base.IServiceSupplierClientAccountProcessController;
import org.trinity.yqyl.process.controller.base.IServiceSupplierClientMaterialProcessController;
import org.trinity.yqyl.process.controller.base.IServiceSupplierClientProcessController;
import org.trinity.yqyl.repository.business.dataaccess.IContentRepository;
import org.trinity.yqyl.repository.business.dataaccess.IServiceCategoryRepository;
import org.trinity.yqyl.repository.business.dataaccess.IServiceSupplierClientAccountRepository;
import org.trinity.yqyl.repository.business.dataaccess.IServiceSupplierClientMaterialRepository;
import org.trinity.yqyl.repository.business.dataaccess.IServiceSupplierClientRepository;
import org.trinity.yqyl.repository.business.dataaccess.IUserRepository;
import org.trinity.yqyl.repository.business.entity.Content;
import org.trinity.yqyl.repository.business.entity.ServiceCategory;
import org.trinity.yqyl.repository.business.entity.ServiceCategory_;
import org.trinity.yqyl.repository.business.entity.ServiceInfo_;
import org.trinity.yqyl.repository.business.entity.ServiceSupplierClient;
import org.trinity.yqyl.repository.business.entity.ServiceSupplierClientAccount;
import org.trinity.yqyl.repository.business.entity.ServiceSupplierClientMaterial;
import org.trinity.yqyl.repository.business.entity.ServiceSupplierClient_;
import org.trinity.yqyl.repository.business.entity.User;
import org.trinity.yqyl.repository.business.entity.User_;

@Service
public class ServiceSupplierClientProcessController extends
        AbstractAutowiredCrudProcessController<ServiceSupplierClient, ServiceSupplierClientDto, ServiceSupplierClientSearchingDto, IServiceSupplierClientRepository>
        implements IServiceSupplierClientProcessController {
    @Autowired
    private IServiceCategoryRepository serviceCategoryRepository;

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IServiceSupplierClientAccountRepository serviceSupplierClientAccountRepository;

    @Autowired
    private IServiceSupplierClientMaterialRepository serviceSupplierClientMaterialRepository;

    @Autowired
    private IContentRepository contentRepository;

    @Autowired
    private IServiceSupplierClientAccountProcessController supplierClientAccountProcessController;

    @Autowired
    private IServiceSupplierClientMaterialProcessController supplierClientMaterialProcessController;

    public ServiceSupplierClientProcessController() {
        super(ServiceSupplierClient.class, ErrorMessage.UNABLE_TO_FIND_SERVICE_SUPPLIER_CLIENT);
    }

    @Override
    public Page<ServiceSupplierClientDto> getAll(final ServiceSupplierClientSearchingDto searchingData) throws IException {
        if (searchingData.getCategoryChildren().isEmpty()) {
            if (searchingData.getCategoryParent() != null) {
                final ServiceCategory category = serviceCategoryRepository.findOne(searchingData.getCategoryParent());
                if (category != null) {
                    searchingData.getCategoryChildren().addAll(serviceCategoryRepository.findAllByParent(category).stream()
                            .map(item -> item.getId()).collect(Collectors.toList()));
                }
            }
        }

        final Page<ServiceSupplierClientDto> dtos = super.getAll(searchingData);
        dtos.forEach(item -> {
            if (item.getServiceInfos() != null && !item.getServiceInfos().isEmpty()) {
                if (!searchingData.getCategoryChildren().isEmpty()) {
                    item.setExpectedPrice(item.getServiceInfos().stream()
                            .filter(info -> searchingData.getCategoryChildren().contains(info.getServiceCategory().getId()))
                            .collect(Collectors.summarizingDouble(info -> info.getPrice())).getSum());
                } else {
                    item.setExpectedPrice(
                            item.getServiceInfos().stream().collect(Collectors.summarizingDouble(info -> info.getPrice())).getSum());
                }
            }
        });

        return dtos;
    }

    @Override
    public Page<ServiceSupplierClient> queryAll(final ServiceSupplierClientSearchingDto searchingData) throws IException {
        final Pageable pagable = getPagingConverter().convert(searchingData);

        final String username = getSecurityUtil().getCurrentToken().getUsername();

        final Specification<ServiceSupplierClient> specification = (root, query, cb) -> {
            final List<Predicate> predicates = new ArrayList<>();

            if (!searchingData.getCategoryChildren().isEmpty()) {
                predicates.add(root.join(ServiceSupplierClient_.serviceInfos).join(ServiceInfo_.serviceCategory).get(ServiceCategory_.id)
                        .in(searchingData.getCategoryChildren()));
                query.distinct(true);
            }

            if (!searchingData.isSearchAll()) {
                predicates.add(cb.equal(root.join(ServiceSupplierClient_.user).get(User_.username), username));
            }

            if (!StringUtils.isEmpty(searchingData.getName())) {
                predicates.add(cb.like(root.get(ServiceSupplierClient_.name), "%" + searchingData.getName() + "%"));
            }

            if (searchingData.getId() != null && searchingData.getId() != 0) {
                predicates.add(cb.equal(root.get(ServiceSupplierClient_.userId), searchingData.getId()));
            }
            return cb.and(predicates.toArray(new Predicate[0]));
        };

        return getDomainEntityRepository().findAll(specification, pagable);
    }

    @Override
    @Transactional
    public ServiceSupplierClientDto register() throws IException {
        final ServiceSupplierClientSearchingDto searchingDto = new ServiceSupplierClientSearchingDto();
        searchingDto.setRsexp("account");

        final User user = userRepository.findOneByUsername(getSecurityUtil().getCurrentToken().getUsername());
        if (user.getServiceSupplierClient() != null) {
            return getDomainObjectConverter().convert(user.getServiceSupplierClient(), searchingDto.generateRelationship());
        }

        Content content = new Content();
        content.setStatus(RecordStatus.ACTIVE);
        content.setUuid(UUID.randomUUID().toString());
        content.setContent(new byte[0]);

        contentRepository.save(content);

        final ServiceSupplierClient serviceSupplierClient = new ServiceSupplierClient();
        serviceSupplierClient.setUserId(user.getId());
        serviceSupplierClient.setUser(user);
        serviceSupplierClient.setStatus(ServiceSupplierClientStatus.INACTIVE);
        serviceSupplierClient.setLogo(content.getUuid());

        getDomainEntityRepository().save(serviceSupplierClient);

        final ServiceSupplierClientAccount serviceSupplierClientAccount = new ServiceSupplierClientAccount();
        serviceSupplierClientAccount.setServiceSupplierClient(serviceSupplierClient);
        serviceSupplierClientAccount.setServiceSupplierClientId(serviceSupplierClient.getUserId());
        serviceSupplierClientAccount.setStatus(RecordStatus.ACTIVE);
        serviceSupplierClientAccountRepository.save(serviceSupplierClientAccount);

        final ServiceSupplierClientMaterial serviceSupplierClientMaterial = new ServiceSupplierClientMaterial();
        content = new Content();
        content.setStatus(RecordStatus.ACTIVE);
        content.setUuid(UUID.randomUUID().toString());
        content.setContent(new byte[0]);
        contentRepository.save(content);

        serviceSupplierClientMaterial.setApplicationCopy(content.getUuid());

        content = new Content();
        content.setStatus(RecordStatus.ACTIVE);
        content.setUuid(UUID.randomUUID().toString());
        content.setContent(new byte[0]);
        contentRepository.save(content);

        serviceSupplierClientMaterial.setBusinessLicenseCopy(content.getUuid());

        content = new Content();
        content.setStatus(RecordStatus.ACTIVE);
        content.setUuid(UUID.randomUUID().toString());
        content.setContent(new byte[0]);
        contentRepository.save(content);

        serviceSupplierClientMaterial.setContractCopy(content.getUuid());

        content = new Content();
        content.setStatus(RecordStatus.ACTIVE);
        content.setUuid(UUID.randomUUID().toString());
        content.setContent(new byte[0]);
        contentRepository.save(content);

        serviceSupplierClientMaterial.setCorporateCheckingCopy(content.getUuid());

        content = new Content();
        content.setStatus(RecordStatus.ACTIVE);
        content.setUuid(UUID.randomUUID().toString());
        content.setContent(new byte[0]);
        contentRepository.save(content);

        serviceSupplierClientMaterial.setJcv(content.getUuid());

        content = new Content();
        content.setStatus(RecordStatus.ACTIVE);
        content.setUuid(UUID.randomUUID().toString());
        content.setContent(new byte[0]);
        contentRepository.save(content);

        serviceSupplierClientMaterial.setLicenseCopy(content.getUuid());

        serviceSupplierClientMaterial.setServiceSupplierClient(serviceSupplierClient);
        serviceSupplierClientMaterial.setServiceSupplierClientId(serviceSupplierClient.getUserId());
        serviceSupplierClientMaterial.setStatus(RecordStatus.ACTIVE);

        serviceSupplierClientMaterialRepository.save(serviceSupplierClientMaterial);

        serviceSupplierClient.setAccount(serviceSupplierClientAccount);
        serviceSupplierClient.setMaterial(serviceSupplierClientMaterial);

        return getDomainObjectConverter().convert(serviceSupplierClient, searchingDto.generateRelationship());
    }

    @Override
    protected void updateRelationship(final ServiceSupplierClient entity, final ServiceSupplierClientDto dto) throws IException {
        if (dto.getAccount() != null) {
            final List<ServiceSupplierClientAccountDto> supplierClientAccountDtos = new ArrayList<>();

            dto.getAccount().setId(dto.getId());

            supplierClientAccountDtos.add(dto.getAccount());
            supplierClientAccountProcessController.updateAll(supplierClientAccountDtos);
        }

        if (dto.getMaterial() != null) {
            final List<ServiceSupplierClientMaterialDto> supplierClientMaterialDtos = new ArrayList<>();

            dto.getMaterial().setId(dto.getId());

            supplierClientMaterialDtos.add(dto.getMaterial());
            supplierClientMaterialProcessController.updateAll(supplierClientMaterialDtos);
        }

        super.updateRelationship(entity, dto);
    }

}
