package com.sparrow.security;

import com.sparrow.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthenticationFailureHandlerImpl authenticationFailureHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/login/**").permitAll()
                .antMatchers("/*.js").permitAll()
                .antMatchers("/assets/**").permitAll()
                .antMatchers("/h2-console/**").permitAll()
                .antMatchers("/api/public/**").permitAll()
                .antMatchers("/api/**").authenticated()
                .antMatchers("/user/**").authenticated()
                .and()
                .formLogin()
                .loginPage("/login").failureHandler(authenticationFailureHandler)
                .loginProcessingUrl("/perform_login")
                .and()
                .logout().logoutUrl("/perform_logout").deleteCookies("JSESSIONID");

        http.headers().frameOptions().disable();

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new PasswordEncoder() {
            public String encode(CharSequence charSequence) {
                return String.valueOf(charSequence);
            }

            public boolean matches(CharSequence charSequence, String s) {
                return String.valueOf(charSequence).equals(s);
            }
        };
    }

}
