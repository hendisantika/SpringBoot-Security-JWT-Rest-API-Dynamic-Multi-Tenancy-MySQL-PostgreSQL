package com.hendisantika.dynamicmultitenancy.controller;

import com.hendisantika.dynamicmultitenancy.constant.UserStatus;
import com.hendisantika.dynamicmultitenancy.dto.AuthResponse;
import com.hendisantika.dynamicmultitenancy.dto.UserLoginDTO;
import com.hendisantika.dynamicmultitenancy.mastertenant.config.DBContextHolder;
import com.hendisantika.dynamicmultitenancy.mastertenant.entity.MasterTenant;
import com.hendisantika.dynamicmultitenancy.mastertenant.service.MasterTenantService;
import com.hendisantika.dynamicmultitenancy.security.UserTenantInformation;
import com.hendisantika.dynamicmultitenancy.util.JwtTokenUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.annotation.ApplicationScope;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * Project : springboot-security-jwt-rest-api-dynamic-multi-tenancy-mysql-postgresql
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 08/05/20
 * Time: 06.17
 */
@RestController
@RequestMapping("/api/auth")
public class AuthenticationController implements Serializable {

	private static final long serialVersionUID = 1L;
	
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationController.class);

    private Map<String, String> mapValue = new HashMap<>();
    private final Map<String, String> userDbMap = new HashMap<>();


    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private MasterTenantService masterTenantService;

    @SuppressWarnings("unlikely-arg-type")
	@PostMapping(value = "/login")
    public ResponseEntity<?> userLogin(@RequestBody @NotNull UserLoginDTO userLoginDTO) throws AuthenticationException {
        LOGGER.info("userLogin() method call...");
        if (null == userLoginDTO.getUserName() || userLoginDTO.getUserName().isEmpty()) {
            return new ResponseEntity<>("User name is required", HttpStatus.BAD_REQUEST);
        }
        //set database parameter
        MasterTenant masterTenant = masterTenantService.findByClientId(userLoginDTO.getTenantOrClientId());
        if (null == masterTenant || masterTenant.getStatus().toUpperCase().equals(UserStatus.INACTIVE)) {
            throw new RuntimeException("Please contact service provider.");
        }
        //Set Client DB
        DBContextHolder.setCurrentDb(masterTenant.getDbName());
        //Entry Client Wise value dbName store into bean.
        //loadCurrentDatabaseInstance(masterTenant.getDbName(), userLoginDTO.getUserName());
        final Authentication authentication =
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userLoginDTO.getUserName()
                        , userLoginDTO.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        final String token = jwtTokenUtil.generateToken(userDetails.getUsername(),
                String.valueOf(userLoginDTO.getTenantOrClientId()));
        //Entry Client Wise value dbName store into bean.
        mapValue.put(userDetails.getUsername(), masterTenant.getDbName());
        //Map the value into applicationScope bean
        setMetaDataAfterLogin();
        return ResponseEntity.ok(new AuthResponse(userDetails.getUsername(), token));
    }

//    private void loadCurrentDatabaseInstance(String databaseName, String userName) {
//        DBContextHolder.setCurrentDb(databaseName);
//        mapValue.put(userName, databaseName);
//    }

    @Bean(name = "userTenantInfo")
    @ApplicationScope
    public UserTenantInformation setMetaDataAfterLogin() {
        UserTenantInformation tenantInformation = new UserTenantInformation();
        if (mapValue.size() > 0) {
            for (String key : mapValue.keySet()) {
                if (null == userDbMap.get(key)) {
                    //Here Assign putAll due to all time one come.
                    userDbMap.putAll(mapValue);
                } else {
                    userDbMap.put(key, mapValue.get(key));
                }
            }
            mapValue = new HashMap<>();
        }
        tenantInformation.setMap(userDbMap);
        return tenantInformation;
    }
}
