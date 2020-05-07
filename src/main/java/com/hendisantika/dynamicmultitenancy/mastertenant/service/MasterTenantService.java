package com.hendisantika.dynamicmultitenancy.mastertenant.service;

import com.hendisantika.dynamicmultitenancy.mastertenant.entity.MasterTenant;
import com.hendisantika.dynamicmultitenancy.mastertenant.repository.MasterTenantRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by IntelliJ IDEA.
 * Project : springboot-security-jwt-rest-api-dynamic-multi-tenancy-mysql-postgresql
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 08/05/20
 * Time: 05.50
 */
@Service
public class MasterTenantService {
    private static final Logger LOG = LoggerFactory.getLogger(MasterTenantService.class);

    @Autowired
    MasterTenantRepository masterTenantRepository;

    public MasterTenant findByClientId(Integer clientId) {
        LOG.info("findByClientId() method call...");
        return masterTenantRepository.findByTenantClientId(clientId);
    }
}
