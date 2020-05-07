package com.hendisantika.dynamicmultitenancy.mastertenant.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
 * Time: 05.44
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = {"com.hendisantika.dynamicmultitenancy.mastertenant.entity", "com.hendisantika" +
        ".dynamicmultitenancy.mastertenant.repository"},
        entityManagerFactoryRef = "masterEntityManagerFactory",
        transactionManagerRef = "masterTransactionManager")
public class MasterDatabaseConfig {

    private static final Logger LOG = LoggerFactory.getLogger(MasterDatabaseConfig.class);

    @Autowired
    private MasterDatabaseConfigProperties masterDbProperties;
}
