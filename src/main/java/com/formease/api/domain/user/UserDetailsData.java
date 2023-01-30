package com.formease.api.domain.user;

public record UserDetailsData
(
    Long id,
    String email,
    String name,
    User.States state
)
{
    public UserDetailsData( User user )
    {
        this( user.getId(), user.getEmail(), user.getName(), user.getState() );
    }
}
