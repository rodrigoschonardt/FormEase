package com.formease.api.controller;

import com.formease.api.domain.user.AuthenticationData;
import com.formease.api.domain.user.User;
import com.formease.api.infra.security.JwtTokenData;
import com.formease.api.infra.security.TokenService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class AuthenticationControllerTest
{
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JacksonTester<AuthenticationData> authDataJson;

    @Autowired
    private JacksonTester<JwtTokenData> tokenDataJson;

    @MockBean
    private AuthenticationManager manager;

    @MockBean
    private TokenService tokenService;

    @Test
    @DisplayName( "It should return code 400 because no body was provided" )
    void mustNotAuthenticate() throws Exception
    {
        MockHttpServletResponse response = mockMvc.perform( post( "/auth" ) )
                                                  .andReturn()
                                                  .getResponse();

        assertThat( response.getStatus() ).isEqualTo( HttpStatus.BAD_REQUEST.value() );
    }

    @Test
    @DisplayName( "It should return code 200 because the user was informed correctly" )
    void mustAuthenticate() throws Exception
    {
        when( manager.authenticate( any() ) ).thenReturn( getAuthentication() );

        MockHttpServletResponse response = mockMvc.perform( post( "/auth" ).contentType( MediaType.APPLICATION_JSON )
                                                                           .content( authDataJson.write( new AuthenticationData( "test@email.com", "test" ) ).getJson() ) )
                                                                           .andReturn()
                                                                           .getResponse();

        assertThat( response.getStatus() ).isEqualTo( HttpStatus.OK.value() );
    }

    private Authentication getAuthentication()
    {
        return new Authentication()
        {
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities()
            {
                return new ArrayList<>();
            }

            @Override
            public Object getCredentials()
            {
                return "";
            }

            @Override
            public Object getDetails()
            {
                return new User();
            }

            @Override
            public Object getPrincipal()
            {
                return new User();
            }

            @Override
            public boolean isAuthenticated()
            {
                return true;
            }

            @Override
            public void setAuthenticated( boolean isAuthenticated )
                    throws IllegalArgumentException
            { }

            @Override
            public String getName()
            {
                return "";
            }
        };
    }
}