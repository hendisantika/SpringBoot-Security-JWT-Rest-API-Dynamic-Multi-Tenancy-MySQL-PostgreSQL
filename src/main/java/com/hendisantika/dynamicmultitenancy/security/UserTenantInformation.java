package com.hendisantika.dynamicmultitenancy.security;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * Project : springboot-security-jwt-rest-api-dynamic-multi-tenancy-mysql-postgresql
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 08/05/20
 * Time: 06.13
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserTenantInformation {
    private Map<String, String> map = new HashMap<>();
}
