package vip.codemonkey.security.core;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author wang zhengtao
 */
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class SecurityCoreConfig {

    @Bean
    public PasswordEncoder passwordEncoder(){return new BCryptPasswordEncoder(); }
}
