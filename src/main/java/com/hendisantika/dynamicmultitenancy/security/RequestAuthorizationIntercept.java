package com.hendisantika.dynamicmultitenancy.security;

import com.hendisantika.dynamicmultitenancy.mastertenant.service.MasterTenantService;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * Created by IntelliJ IDEA.
 * Project : springboot-security-jwt-rest-api-dynamic-multi-tenancy-mysql-postgresql
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 08/05/20
 * Time: 06.12
 */
@Aspect
@Component
public class RequestAuthorizationIntercept {

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private MasterTenantService masterTenantService;

}
