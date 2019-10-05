package com.waqar.reservation.web.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class ApiSecurity extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //super.configure(http);
            http.httpBasic().and().authorizeRequests()
                    .antMatchers(HttpMethod.GET, "/api/**").hasAnyRole("USER","ADMIN")
                    .antMatchers(HttpMethod.POST, "/api/**").hasRole("ADMIN")
                    .and()
                    .csrf().disable()
                    .formLogin().disable();
    }

    @Override
    public void configure(AuthenticationManagerBuilder authBuilder) throws Exception{
        authBuilder
                .jdbcAuthentication()
                .dataSource(dataSource)
                .passwordEncoder(passwordEncoder())
//                .passwordEncoder(NoOpPasswordEncoder.getInstance())
                .usersByUsernameQuery(
                "select username,password,enabled from users where username=?")
                .authoritiesByUsernameQuery(
                        "select username,role from user_roles where username=?");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
