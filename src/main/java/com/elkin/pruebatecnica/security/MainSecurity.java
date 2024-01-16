package com.elkin.pruebatecnica.security;

import com.elkin.pruebatecnica.security.details.service.UserDetailsServiceImpl;
import com.elkin.pruebatecnica.security.jwt.JwtEntryPoint;
import com.elkin.pruebatecnica.security.jwt.JwtTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class MainSecurity {
    @Autowired
    UserDetailsServiceImpl userDetailsService;
    @Autowired
    JwtEntryPoint jwtEntryPoint;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtTokenFilter jwtTokenFilter;

    AuthenticationManager authenticationManager;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder builder = http.getSharedObject(AuthenticationManagerBuilder.class);
        builder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
        authenticationManager = builder.build();
        http.authenticationManager(authenticationManager);

        return http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/users/crear","/users/login").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .exceptionHandling().authenticationEntryPoint(jwtEntryPoint)
                .and()
                .addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class)
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .build();

    }
}
