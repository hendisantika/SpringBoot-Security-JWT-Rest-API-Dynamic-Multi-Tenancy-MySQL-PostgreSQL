package com.hendisantika.dynamicmultitenancy.controller;

import com.hendisantika.dynamicmultitenancy.security.RequestAuthorization;
import com.hendisantika.dynamicmultitenancy.tenant.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
 * Time: 06.23
 */
@RestController
@RequestMapping("/api/product")
public class ProductController implements Serializable {

	private static final long serialVersionUID = 1L;
	
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationController.class);

    @Autowired
    private ProductService productService;

    @RequestAuthorization
    @GetMapping(value = "/all")
    public ResponseEntity<Object> getAllProduct() {
        LOGGER.info("getAllProduct() method call...");
        return new ResponseEntity<>(productService.getAllProduct(), HttpStatus.OK);
    }
}
