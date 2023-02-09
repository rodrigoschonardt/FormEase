package com.formease.api.domain.user;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Table( name = "Users" )
@Entity( name = "User" )
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode( of = "id" )
public class User
    implements
        UserDetails
{
    public enum States { ACTIVE, DELETED };

    @Id @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;
    private String email;
    private String name;
    private String password;
    @Enumerated( EnumType.STRING )
    private States state;

    public User( AddUserData data )
    {
        this.email = data.email();
        this.name = data.name();
        this.state = data.state();
    }

    public User( Long id )
    {
        this.id = id;
    }

    public void updateInfo( UpdateUserData data )
    {
       if ( data.name() != null )
       {
           this.name = data.name();
       }

       if ( data.state() != null )
       {
           this.state = data.state();
       }
    }

    public void delete()
    {
        this.state = States.DELETED;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities()
    {
        return List.of(new SimpleGrantedAuthority( "ROLE_USER" ));
    }

    @Override
    public String getUsername()
    {
        return this.email;
    }

    @Override
    public String getPassword()
    {
        return this.password;
    }

    @Override
    public boolean isAccountNonExpired()
    {
        return true;
    }

    @Override
    public boolean isAccountNonLocked()
    {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired()
    {
        return true;
    }

    @Override
    public boolean isEnabled()
    {
        return state == States.ACTIVE;
    }
}
