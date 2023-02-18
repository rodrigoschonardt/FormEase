package com.formease.api.domain.field;

import com.formease.api.domain.form.Form;
import com.formease.api.domain.form.FormDetailsData;

public record FieldDetailsData
(
    Long id,
    String label,
    Boolean required,
    Field.Types type
)
{
    public FieldDetailsData( Field field )
    {
        this( field.getId(), field.getLabel(), field.getRequired(), field.getType() );
    }
}
