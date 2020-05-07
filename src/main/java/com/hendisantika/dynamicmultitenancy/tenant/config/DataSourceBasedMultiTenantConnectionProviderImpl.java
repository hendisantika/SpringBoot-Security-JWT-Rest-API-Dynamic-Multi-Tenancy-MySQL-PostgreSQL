package com.hendisantika.dynamicmultitenancy.tenant.config;

import com.hendisantika.dynamicmultitenancy.mastertenant.repository.MasterTenantRepository;
import org.hibernate.engine.jdbc.connections.spi.AbstractDataSourceBasedMultiTenantConnectionProviderImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by IntelliJ IDEA.
 * Project : springboot-security-jwt-rest-api-dynamic-multi-tenancy-mysql-postgresql
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 08/05/20
 * Time: 05.54
 */
@Configuration
public class DataSourceBasedMultiTenantConnectionProviderImpl extends AbstractDataSourceBasedMultiTenantConnectionProviderImpl {

    private static final Logger LOG = LoggerFactory.getLogger(DataSourceBasedMultiTenantConnectionProviderImpl.class);

    private static final long serialVersionUID = 1L;

    private final Map<String, DataSource> dataSourcesMtApp = new TreeMap<>();
    @Autowired
    ApplicationContext applicationContext;
    @Autowired
    private MasterTenantRepository masterTenantRepository;
}
