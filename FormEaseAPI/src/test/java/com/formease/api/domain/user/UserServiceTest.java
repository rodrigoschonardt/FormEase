package com.formease.api.domain.user;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName( "UserServiceTest" )
@SpringBootTest
@AutoConfigureMockMvc
public class UserServiceTest
{
    @MockBean
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    @Test
    @DisplayName( "Must create a new user" )
    public void mustCreateNewUser()
    {
        AddUserData userData = new AddUserData( "test@email.com",
                                                "test",
                                                "password" );

        User newUser = userService.createNewUser( userData );

        assertTrue( passwordEncoder.matches( userData.password(), newUser.getPassword() ), "Password not encrypted" );
        Mockito.verify( userRepository ).save( newUser );
    }
}
