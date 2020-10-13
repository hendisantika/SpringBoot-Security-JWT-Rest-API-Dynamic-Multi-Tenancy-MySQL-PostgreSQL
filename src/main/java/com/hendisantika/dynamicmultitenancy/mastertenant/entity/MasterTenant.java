package com.hendisantika.dynamicmultitenancy.mastertenant.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * Project : springboot-security-jwt-rest-api-dynamic-multi-tenancy-mysql-postgresql
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 08/05/20
 * Time: 05.48
 */
@Entity
@Table(name = "tbl_tenant_master")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MasterTenant implements Serializable {

	private static final long serialVersionUID = 1L;
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tenant_client_id")
    private Integer tenantClientId;

    @Size(max = 50)
    @Column(name = "db_name", nullable = false)
    private String dbName;

    @Size(max = 100)
    @Column(name = "url", nullable = false)
    private String url;

    @Size(max = 50)
    @Column(name = "user_name", nullable = false)
    private String userName;
    @Size(max = 100)
    @Column(name = "password", nullable = false)
    private String password;
    @Size(max = 100)
    @Column(name = "driver_class", nullable = false)
    private String driverClass;
    @Size(max = 10)
    @Column(name = "status", nullable = false)
    private String status;

    public MasterTenant(@Size(max = 50) String dbName, @Size(max = 100) String url, @Size(max = 50) String userName,
                        @Size(max = 100) String password, @Size(max = 100) String driverClass,
                        @Size(max = 10) String status) {
        this.dbName = dbName;
        this.url = url;
        this.userName = userName;
        this.password = password;
        this.driverClass = driverClass;
        this.status = status;
    }
}
