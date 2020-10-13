package com.hendisantika.dynamicmultitenancy.controller;

import com.hendisantika.dynamicmultitenancy.security.UserTenantInformation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.security.Principal;
import java.util.Map;

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
	
	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = LoggerFactory.getLogger(LogoutController.class);

    @Autowired
    private ApplicationContext applicationContext;

    @GetMapping(value = "/logout")
    public ResponseEntity<?> logoutFromApp(Principal principal) {
        LOGGER.info("AuthenticationController::logoutFromApp() method call..");
        UserTenantInformation userCharityInfo = applicationContext.getBean(UserTenantInformation.class);
        Map<String, String> map = userCharityInfo.getMap();
        map.remove(principal.getName());
        userCharityInfo.setMap(map);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
