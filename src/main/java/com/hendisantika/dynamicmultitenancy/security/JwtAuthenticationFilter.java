package com.hendisantika.dynamicmultitenancy.security;

import com.hendisantika.dynamicmultitenancy.constant.JWTConstants;
import com.hendisantika.dynamicmultitenancy.mastertenant.config.DBContextHolder;
import com.hendisantika.dynamicmultitenancy.mastertenant.entity.MasterTenant;
import com.hendisantika.dynamicmultitenancy.mastertenant.service.MasterTenantService;
import com.hendisantika.dynamicmultitenancy.util.JwtTokenUtil;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

/**
 * Created by IntelliJ IDEA.
 * Project : springboot-security-jwt-rest-api-dynamic-multi-tenancy-mysql-postgresql
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 08/05/20
 * Time: 06.08
 */
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    MasterTenantService masterTenantService;

    @Autowired
    private JwtUserDetailsService jwtUserDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) throws ServletException, IOException {
        String header = httpServletRequest.getHeader(JWTConstants.HEADER_STRING);
        String username = null;
        String audience = null; //tenantOrClientId
        String authToken = null;
        if (header != null && header.startsWith(JWTConstants.TOKEN_PREFIX)) {
            authToken = header.replace(JWTConstants.TOKEN_PREFIX, "");
            try {
                username = jwtTokenUtil.getUsernameFromToken(authToken);
                audience = jwtTokenUtil.getAudienceFromToken(authToken);
                MasterTenant masterTenant = masterTenantService.findByClientId(Integer.valueOf(audience));
                if (null == masterTenant) {
                    logger.error("An error during getting tenant name");
                    throw new BadCredentialsException("Invalid tenant and user.");
                }
                DBContextHolder.setCurrentDb(masterTenant.getDbName());
            } catch (IllegalArgumentException ex) {
                logger.error("An error during getting username from token", ex);
            } catch (ExpiredJwtException ex) {
                logger.warn("The token is expired and not valid anymore", ex);
            } catch (SignatureException ex) {
                logger.error("Authentication Failed. Username or Password not valid.", ex);
            }
        } else {
            logger.warn("Couldn't find bearer string, will ignore the header");
        }
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(username);
            if (jwtTokenUtil.validateToken(authToken, userDetails)) {
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(userDetails, null,
                                Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN")));
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                logger.info("authenticated user " + username + ", setting security context");
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
