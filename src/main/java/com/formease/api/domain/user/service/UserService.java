package com.formease.api.domain.user.service;

import com.formease.api.domain.user.AddUserData;
import com.formease.api.domain.user.User;
import com.formease.api.domain.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService
{
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    public User createNewUser( AddUserData data )
    {
        User user = new User( data );

        user.setPassword( passwordEncoder.encode( data.password() ) );

        userRepository.save( user );

        return user;
    }
}
