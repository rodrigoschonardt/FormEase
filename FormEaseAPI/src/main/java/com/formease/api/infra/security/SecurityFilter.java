package com.formease.api.infra.security;

import com.formease.api.domain.user.User;
import com.formease.api.domain.user.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter
    extends
        OncePerRequestFilter
{
    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserRepository repository;

    @Override
    protected void doFilterInternal( HttpServletRequest request, HttpServletResponse response, FilterChain filterChain )
            throws ServletException, IOException
    {
        String token = retrieveToken( request );

        if ( token != null )
        {
            String subject = tokenService.getSubject( token );
            User user = (User) repository.findById( subject );

            Authentication authentication = new UsernamePasswordAuthenticationToken( user, null, user.getAuthorities() );
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter( request, response );
    }

    private String retrieveToken(HttpServletRequest request )
    {
        String authorizationHeader = request.getHeader( "Authorization" );

        if ( authorizationHeader != null )
        {
            return authorizationHeader.replace( "Bearer ", "" );
        }

        return null;
    }
}
