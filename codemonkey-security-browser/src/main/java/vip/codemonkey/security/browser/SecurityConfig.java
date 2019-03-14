package vip.codemonkey.security.browser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import vip.codemonkey.security.core.constant.SecurityConstant;

/**
 * @author wang zhengtao
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    AuthenticationSuccessHandler browserAuthenticationSuccessHandler;
    @Autowired
    AuthenticationFailureHandler browserAuthenticationFailureHandler;
    @Autowired
    AccessDeniedHandler customAccessDeniedHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .formLogin().loginPage(SecurityConstant.DEFAULT_UNAUTHENTICATION_URL)
                         .loginProcessingUrl(SecurityConstant.DEFAULT_LOGIN_PROCESSING_URL_FORM)
                         .successHandler(browserAuthenticationSuccessHandler)
                         .failureHandler(browserAuthenticationFailureHandler)
            .and()
            .authorizeRequests()
                .antMatchers(SecurityConstant.DEFAULT_UNAUTHENTICATION_URL,
                        SecurityConstant.DEFAULT_LOGIN_PROCESSING_URL_FORM).permitAll()
                .anyRequest().authenticated()
            .and()
            .csrf().disable()
            .exceptionHandling().accessDeniedHandler(customAccessDeniedHandler)
        ;
    }
}
