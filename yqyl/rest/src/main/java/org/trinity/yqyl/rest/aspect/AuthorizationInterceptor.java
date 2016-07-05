package org.trinity.yqyl.rest.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.trinity.common.exception.IException;
import org.trinity.yqyl.rest.accessright.Authorize;
import org.trinity.yqyl.rest.util.SecurityUtil;
import org.trinity.yqyl.rest.util.SecurityUtil.CheckMode;

@Component
@Aspect
public class AuthorizationInterceptor {
    @Autowired
    private SecurityUtil securityUtil;

    @Pointcut(value = "@annotation(org.trinity.yqyl.rest.accessright.Authorize)")
    public void authorizationPointCut() {
    }

    @Before("authorizationPointCut() && @annotation(authorize)")
    public void dataaccessBefore(final JoinPoint joinPoint, final Authorize authorize) throws IException {
        securityUtil.checkAccessRight(CheckMode.ALL, authorize.requireAll());
        securityUtil.checkAccessRight(CheckMode.ANY, authorize.requireAny());
    }
}
