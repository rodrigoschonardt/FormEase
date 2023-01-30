package com.formease.api.controller;

import com.formease.api.domain.user.AuthenticationData;
import com.formease.api.domain.user.User;
import com.formease.api.infra.security.JwtTokenData;
import com.formease.api.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping( "/authenticate" )
public class AuthenticationController
{
    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity authenticate( @RequestBody @Valid AuthenticationData data )
    {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken( data.email(), data.password() );
        Authentication authentication = manager.authenticate( authenticationToken );

        String token = tokenService.generateToken( (User) authentication.getPrincipal() );

        return ResponseEntity.ok( new JwtTokenData( token ) );
    }
}
