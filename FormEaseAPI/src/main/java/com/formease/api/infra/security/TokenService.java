package com.formease.api.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.formease.api.domain.user.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value( "${api.security.token.secret}" )
    private String secret;

    public JwtTokenData generateToken( User user )
    {
        try
        {
            Algorithm algorithm = Algorithm.HMAC256( secret );

            Instant expiration = getExpirationDate();

            return new JwtTokenData( JWT.create()
                                        .withIssuer( "API formease" )
                                        .withSubject( String.valueOf( user.getId() )  )
                                        .withExpiresAt( expiration )
                                        .sign( algorithm ) );
        }
        catch ( JWTCreationException ex )
        {
            throw new RuntimeException( "Error generating JWT token", ex );
        }
    }

    public String getSubject( String token )
    {
        try
        {
            Algorithm algorithm = Algorithm.HMAC256( secret );

            return JWT.require( algorithm )
                      .withIssuer( "API formease" )
                      .build()
                      .verify( token )
                      .getSubject();
        }

        catch ( JWTVerificationException ex )
        {
            throw new RuntimeException( "JWT token is invalid or expired!" );
        }
    }

    private Instant getExpirationDate()
    {
        return LocalDateTime.now().plusHours(2).toInstant( ZoneOffset.of( "-03:00" ) );
    }
}