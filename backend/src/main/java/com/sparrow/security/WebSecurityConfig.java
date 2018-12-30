package com.sparrow.security;

import com.sparrow.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthenticationFailureHandlerImpl authenticationFailureHandler;

    @Qualifier("notFoundEntryPoint")
    @Autowired
    AuthenticationEntryPoint notFoundEntryPoint;

    @Qualifier("unauthorizedEntryPoint")
    @Autowired
    AuthenticationEntryPoint unauthorizedEntryPoint;



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
                .antMatchers("/user/**").authenticated()
                .antMatchers("/api/**").authenticated()
                .and()
                .exceptionHandling()
                .defaultAuthenticationEntryPointFor(
                        notFoundEntryPoint,
                        new AntPathRequestMatcher("/api/user/me")
                )
                .defaultAuthenticationEntryPointFor(
                        unauthorizedEntryPoint,
                        new AntPathRequestMatcher("/api/**")
                )
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
