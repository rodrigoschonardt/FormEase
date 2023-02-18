package com.formease.api.domain.field;

import com.formease.api.domain.form.Form;
import jakarta.persistence.*;
import lombok.*;

import javax.swing.plaf.nimbus.State;

@Entity( name = "Field" )
@Table( name = "fields" )
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode( of = "id" )
public class Field
{
    public enum Types { SIMPLE_TEXT, TEXTBOX };
    public enum States { ACTIVE, DELETED };

    @Id @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;
    private String label;
    private Boolean required;

    @ManyToOne( fetch = FetchType.LAZY )
    @JoinColumn( name = "ref_form" )
    private Form form;

    @Enumerated( EnumType.STRING )
    private Types type;

    @Enumerated( EnumType.STRING )
    private States state;

    public Field( Form form, AddFieldData data )
    {
        this.form = form;
        this.label = data.label();
        this.type = data.type();
        this.required = data.required();
        this.state = States.ACTIVE;
    }

    public void update( UpdateFieldData data )
    {
        if ( data.label() != null )
        {
            this.label = data.label();
        }

        if ( data.required() != null )
        {
            this.required = data.required();
        }

        if( data.type() != null )
        {
            this.type = data.type();
        }
    }

    public void delete()
    {
        this.state = States.DELETED;
    }
}
