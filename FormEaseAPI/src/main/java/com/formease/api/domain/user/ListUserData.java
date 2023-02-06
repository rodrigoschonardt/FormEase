package com.formease.api.domain.user;

public record ListUserData
(
    String name,
    String email,
    User.States state
)
{
    public ListUserData( User user )
    {
        this(user.getName(), user.getEmail(), user.getState() );
    }
}
