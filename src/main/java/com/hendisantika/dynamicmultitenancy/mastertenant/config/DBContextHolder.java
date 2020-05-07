package com.hendisantika.dynamicmultitenancy.mastertenant.config;

/**
 * Created by IntelliJ IDEA.
 * Project : springboot-security-jwt-rest-api-dynamic-multi-tenancy-mysql-postgresql
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 08/05/20
 * Time: 05.44
 */
public class DBContextHolder {
    private static final ThreadLocal<String> contextHolder = new ThreadLocal<>();

    public static String getCurrentDb() {
        return contextHolder.get();
    }

    public static void setCurrentDb(String dbType) {
        contextHolder.set(dbType);
    }

    public static void clear() {
        contextHolder.remove();
    }
}
