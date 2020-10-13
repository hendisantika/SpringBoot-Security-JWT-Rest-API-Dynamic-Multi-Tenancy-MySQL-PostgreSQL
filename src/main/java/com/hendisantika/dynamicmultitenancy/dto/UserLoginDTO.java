package com.hendisantika.dynamicmultitenancy.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * Project : springboot-security-jwt-rest-api-dynamic-multi-tenancy-mysql-postgresql
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 08/05/20
 * Time: 05.38
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
    private String userName;
    private String password;
    private Integer tenantOrClientId;
}
