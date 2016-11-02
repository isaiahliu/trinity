package org.trinity.yqyl.process.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.criteria.Predicate;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.trinity.common.accessright.ISecurityUtil.CheckMode;
import org.trinity.common.exception.IException;
import org.trinity.yqyl.common.message.dto.domain.UserDto;
import org.trinity.yqyl.common.message.dto.domain.UserSearchingDto;
import org.trinity.yqyl.common.message.exception.ErrorMessage;
import org.trinity.yqyl.common.message.lookup.AccessRight;
import org.trinity.yqyl.common.message.lookup.UserStatus;
import org.trinity.yqyl.process.controller.base.AbstractAutowiredCrudProcessController;
import org.trinity.yqyl.process.controller.base.IUserProcessController;
import org.trinity.yqyl.repository.business.dataaccess.IAccessrightRepository;
import org.trinity.yqyl.repository.business.dataaccess.IUserRepository;
import org.trinity.yqyl.repository.business.entity.Accessright;
import org.trinity.yqyl.repository.business.entity.User;
import org.trinity.yqyl.repository.business.entity.User_;

@Service
public class UserProcessController extends AbstractAutowiredCrudProcessController<User, UserDto, UserSearchingDto, IUserRepository>
        implements IUserProcessController {
    @Autowired
    private IAccessrightRepository accessrightRepository;

    public UserProcessController() {
        super(User.class, ErrorMessage.UNABLE_TO_FIND_USER);
    }

    @Override
    public void changePassword(final Long id, final String oldPassword, final String newPassword) throws IException {
        final User user = getDomainEntityRepository().findOne(id);

        validateDataPermission(getDomainObjectConverter().convert(user));

        if (!user.getPassword().equals(oldPassword)) {
            throw getExceptionFactory().createException(ErrorMessage.WRONG_PASSWORD);
        }

        user.setPassword(newPassword);

        getDomainEntityRepository().save(user);
    }

    @Override
    public List<UserDto> getMe(final UserSearchingDto dto) throws IException {
        final String username = getSecurityUtil().getCurrentToken().getUsername();

        final User user = getDomainEntityRepository().findOneByUsername(username);
        final UserDto userDto = getDomainObjectConverter().convert(user, dto.generateRelationship());

        final List<UserDto> result = new ArrayList<>();
        result.add(userDto);
        return result;
    }

    @Override
    protected void addRelationship(final User entity, final UserDto dto) {
        entity.setStatus(UserStatus.ACTIVE);
    }

    @Override
    @Transactional
    protected Page<User> queryAll(final UserSearchingDto dto) throws IException {
        final Pageable pagable = getPagingConverter().convert(dto);

        final Accessright superUser = accessrightRepository.findOneByName(AccessRight.SUPER_USER);
        final List<Long> exceptUserIds = superUser.getUsers().stream().map(item -> item.getId()).collect(Collectors.toList());

        final Specification<User> specification = (root, query, cb) -> {
            final List<Predicate> predicates = new ArrayList<>();

            if (!StringUtils.isEmpty(dto.getUsername())) {
                predicates.add(cb.like(root.get(User_.username), "%" + dto.getUsername() + "%"));
            }

            if (dto.getId() != null && dto.getId() > 0) {
                predicates.add(cb.equal(root.get(User_.id), dto.getId()));
            }

            if (!exceptUserIds.isEmpty()) {
                predicates.add(root.get(User_.id).in(exceptUserIds).not());
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };

        return getDomainEntityRepository().findAll(specification, pagable);
    }

    @Override
    protected void updateRelationship(final User entity, final UserDto dto) throws IException {
        if (dto.getAccessrights() != null) {
            if (getSecurityUtil().hasAccessRight(CheckMode.ANY, AccessRight.ADMINISTRATOR)) {
                entity.getAccessrights().clear();
                if (!dto.getAccessrights().isEmpty()) {
                    accessrightRepository.findAll(dto.getAccessrights().stream().map(item -> item.getId()).collect(Collectors.toList()))
                            .forEach(item -> entity.getAccessrights().add(item));
                }
            }
        }
    }

    @Override
    protected void validateDataPermission(final UserDto dto) throws IException {
        final String currentUser = getSecurityUtil().getCurrentToken().getUsername();

        final User user = getDomainEntityRepository().findOne(dto.getId());

        if (!getSecurityUtil().hasAccessRight(CheckMode.ANY, AccessRight.SUPER_USER)) {
            if (!currentUser.equals(user.getUsername())) {
                throw getExceptionFactory().createException(ErrorMessage.UNABLE_TO_ACCESS_USER, user.getUsername());
            }
        }
    }
}
