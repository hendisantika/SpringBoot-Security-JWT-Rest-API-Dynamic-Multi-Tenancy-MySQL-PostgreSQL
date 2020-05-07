package com.hendisantika.dynamicmultitenancy.mastertenant.config;

import com.zaxxer.hikari.HikariDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

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

    //Create Master Data Source using master properties and also configure HikariCP
    @Bean(name = "masterDataSource")
    public DataSource masterDataSource() {
        HikariDataSource hikariDataSource = new HikariDataSource();
        hikariDataSource.setUsername(masterDbProperties.getUsername());
        hikariDataSource.setPassword(masterDbProperties.getPassword());
        hikariDataSource.setJdbcUrl(masterDbProperties.getUrl());
        hikariDataSource.setDriverClassName(masterDbProperties.getDriverClassName());
        hikariDataSource.setPoolName(masterDbProperties.getPoolName());
        // HikariCP settings
        hikariDataSource.setMaximumPoolSize(masterDbProperties.getMaxPoolSize());
        hikariDataSource.setMinimumIdle(masterDbProperties.getMinIdle());
        hikariDataSource.setConnectionTimeout(masterDbProperties.getConnectionTimeout());
        hikariDataSource.setIdleTimeout(masterDbProperties.getIdleTimeout());
        LOG.info("Setup of masterDataSource succeeded.");
        return hikariDataSource;
    }
}
