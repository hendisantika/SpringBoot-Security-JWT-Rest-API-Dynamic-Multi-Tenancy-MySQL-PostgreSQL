package com.hendisantika.dynamicmultitenancy.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * Project : springboot-security-jwt-rest-api-dynamic-multi-tenancy-mysql-postgresql
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 08/05/20
 * Time: 06.22
 */
@RestController
@RequestMapping("/api/product/logout")
public class LogoutController implements Serializable {
    private static final Logger LOGGER = LoggerFactory.getLogger(LogoutController.class);

    @Autowired
    private ApplicationContext applicationContext;
}
