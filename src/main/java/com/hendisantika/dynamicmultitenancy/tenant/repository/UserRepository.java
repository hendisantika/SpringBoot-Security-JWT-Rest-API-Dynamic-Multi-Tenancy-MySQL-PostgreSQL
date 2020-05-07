package com.hendisantika.dynamicmultitenancy.tenant.repository;

import com.hendisantika.dynamicmultitenancy.tenant.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by IntelliJ IDEA.
 * Project : springboot-security-jwt-rest-api-dynamic-multi-tenancy-mysql-postgresql
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 08/05/20
 * Time: 06.01
 */
public interface UserRepository extends JpaRepository<User, Integer> {

    User findByUserName(String userName);
}
