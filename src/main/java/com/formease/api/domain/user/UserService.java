package com.formease.api.domain.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService
{
    @Autowired
    private PasswordEncoder passwordEncoder;

    public User createNewUser( AddUserData data )
    {
        User user = new User( data );

        user.setPassword( passwordEncoder.encode( data.password() ) );

        return user;
    }
}
