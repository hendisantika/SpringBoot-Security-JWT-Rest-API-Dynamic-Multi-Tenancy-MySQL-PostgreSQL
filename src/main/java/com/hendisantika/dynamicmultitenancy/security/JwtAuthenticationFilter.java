package com.hendisantika.dynamicmultitenancy.security;

import com.hendisantika.dynamicmultitenancy.mastertenant.service.MasterTenantService;
import com.hendisantika.dynamicmultitenancy.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * Created by IntelliJ IDEA.
 * Project : springboot-security-jwt-rest-api-dynamic-multi-tenancy-mysql-postgresql
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 08/05/20
 * Time: 06.08
 */
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    MasterTenantService masterTenantService;
    @Autowired
    private JwtUserDetailsService jwtUserDetailsService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

}
