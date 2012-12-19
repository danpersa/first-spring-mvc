package ro.danix.first.config;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.memory.UserAttribute;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;
import org.springframework.security.web.access.ExceptionTranslationFilter;
import org.springframework.security.web.authentication.*;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextPersistenceFilter;

/**
 *
 * @author Dan Persa
 */
//@Configuration
//@ImportResource({
//    "classpath:ro/danix/first/security/security.xml"})
public class SecurityFilters {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private AuthenticationSuccessHandler authenticationSuccessHandler;
    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;

    @Bean
    public LogoutFilter logoutFilter() {
        LogoutFilter logoutFilter = new LogoutFilter("/index.html", new SecurityContextLogoutHandler());
        logoutFilter.setFilterProcessesUrl("/logout.html");
        return logoutFilter;
    }

    @Bean
    public SecurityContextPersistenceFilter securityContextPersistenceFilter() {
        HttpSessionSecurityContextRepository securityContextRepository = new HttpSessionSecurityContextRepository();
        securityContextRepository.setAllowSessionCreation(false);

        SecurityContextPersistenceFilter securityContextPersistenceFilter = new SecurityContextPersistenceFilter(securityContextRepository);
        return securityContextPersistenceFilter;
    }

    @Bean
    public UsernamePasswordAuthenticationFilter authenticationProcessingFilter() {
        UsernamePasswordAuthenticationFilter authenticationProcessingFilter = new UsernamePasswordAuthenticationFilter();
        authenticationProcessingFilter.setAuthenticationManager(authenticationManager);
        authenticationProcessingFilter.setAuthenticationSuccessHandler(authenticationSuccessHandler);
        authenticationProcessingFilter.setAuthenticationFailureHandler(authenticationFailureHandler);
        authenticationProcessingFilter.setUsernameParameter("j_username");
        authenticationProcessingFilter.setPasswordParameter("j_password");
        authenticationProcessingFilter.setFilterProcessesUrl("/authenticate");
        return authenticationProcessingFilter;
    }

    @Bean
    public AnonymousAuthenticationFilter anonymousFilter() {
        List<GrantedAuthority> grantedAuthoritys = new ArrayList<GrantedAuthority>();
        grantedAuthoritys.add(new SimpleGrantedAuthority("anonymousUser,ROLE_ANONYMOUS"));
        AnonymousAuthenticationFilter anonymousAuthenticationFilter = new AnonymousAuthenticationFilter("unique_key_123", "anonymousUser", grantedAuthoritys);
        return anonymousAuthenticationFilter;
    }

    @Bean
    public ExceptionTranslationFilter exceptionTranslationFilter() {
        LoginUrlAuthenticationEntryPoint loginUrlAuthenticationEntryPoint = new LoginUrlAuthenticationEntryPoint("/login.html");
        loginUrlAuthenticationEntryPoint.setForceHttps(false);
        AccessDeniedHandlerImpl accessDeniedHandler = new AccessDeniedHandlerImpl();
        accessDeniedHandler.setErrorPage("/access-denied.html");
        ExceptionTranslationFilter exceptionTranslationFilter = new ExceptionTranslationFilter(loginUrlAuthenticationEntryPoint);
        exceptionTranslationFilter.setAccessDeniedHandler(accessDeniedHandler);
        return exceptionTranslationFilter;
    }
}
