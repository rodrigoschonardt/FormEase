package com.formease.api.infra.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfigurations
{
    @Autowired
    private SecurityFilter securityFilter;

    private AntPathRequestMatcher[] requestMatchers =
    {
        new AntPathRequestMatcher( "/auth" ),
        new AntPathRequestMatcher( "/users", HttpMethod.POST.toString() )
    };

    @Bean
    public SecurityFilterChain securityFilterChain( HttpSecurity http ) throws Exception
    {
        return http.cors().and()
                   .csrf()
                   .disable()
                   .sessionManagement().sessionCreationPolicy( SessionCreationPolicy.STATELESS )
                   .and().authorizeRequests()
                   .requestMatchers( requestMatchers ).permitAll()
                   .anyRequest().authenticated()
                   .and().addFilterBefore( securityFilter, UsernamePasswordAuthenticationFilter.class )
                   .build();
    }

    @Bean
    public AuthenticationManager authenticationManager( AuthenticationConfiguration configuration ) throws Exception
    {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }
}
