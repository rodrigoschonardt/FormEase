package com.formease.api.domain.user;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.userdetails.UserDetails;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class UserRepositoryTest
{
    @Autowired
    private UserRepository userRepository;

    @AfterEach
    void clearAll()
    {
        userRepository.deleteAll();
    }

    @Test
    void ItShouldFindByEmail()
    {
        User user = createUser();

        userRepository.save( user );

        UserDetails expected = userRepository.findByEmail( "test@email.com" );

        assertNotNull( expected );
    }

    @Test
    void ItShouldntFindByEmail()
    {
        UserDetails expected = userRepository.findByEmail( "test@email.com" );

        assertNull( expected );
    }

    @Test
    void ItShouldFindAllByState()
    {
        User user = createUser();

        userRepository.save( user );

        user = createUser();

        userRepository.save( user );

        Page<User> expected = userRepository.findAllByState( PageRequest.of( 1, 10 ), User.States.ACTIVE );

        assertNotNull( expected );
        assertTrue( expected.getTotalElements() > 1 );
    }

    @Test
    void ItShouldntFindAllByState()
    {
        Page<User> expected = userRepository.findAllByState( PageRequest.of( 1, 10 ), User.States.ACTIVE );

        assertTrue( expected.getTotalElements() == 0 );
    }

    private User createUser()
    {
        User user = new User();

        user.setEmail( "test@email.com" );
        user.setPassword( "test" );
        user.setName( "test" );
        user.setState( User.States.ACTIVE );

        return user;
    }
}