package com.formease.api.domain.user.service;

import com.formease.api.ApplicationConfigTest;
import com.formease.api.domain.user.AddUserData;
import com.formease.api.domain.user.User;
import com.formease.api.domain.user.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.Assert;

@DisplayName( "UserServiceTest" )
public class UserServiceTest
    extends
        ApplicationConfigTest
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
                                                "password",
                                                User.States.ACTIVE );

        User newUser = userService.createNewUser( userData );

        Assert.isTrue( passwordEncoder.matches( userData.password(), newUser.getPassword() ), "Password not encrypted" );
        Mockito.verify( userRepository ).save( newUser );
    }
}
