package com.hendisantika.dynamicmultitenancy.tenant.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Created by IntelliJ IDEA.
 * Project : springboot-security-jwt-rest-api-dynamic-multi-tenancy-mysql-postgresql
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 08/05/20
 * Time: 06.03
 */
@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = {"com.hendisantika.dynamicmultitenancy.tenant.repository", "com.hendisantika" +
        ".dynamicmultitenancy.tenant.entity"})
@EnableJpaRepositories(basePackages = {"com.hendisantika.dynamicmultitenancy.tenant.repository", "com.hendisantika" +
        ".dynamicmultitenancy.tenant.service"},
        entityManagerFactoryRef = "tenantEntityManagerFactory",
        transactionManagerRef = "tenantTransactionManager")
public class TenantDatabaseConfig {
}
