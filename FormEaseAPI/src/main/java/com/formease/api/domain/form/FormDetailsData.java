package com.formease.api.domain.form;

import com.formease.api.domain.user.UserDetailsData;

public record FormDetailsData
(
    Long id,
    String name,
    String info,
    UserDetailsData owner,
    Form.States state
)
{
    public FormDetailsData( Form form )
    {
        this( form.getId(), form.getName(), form.getInfo(),
              new UserDetailsData( form.getOwner() ), form.getState() );
    }
}
