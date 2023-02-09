package com.formease.api.domain.form;

import com.formease.api.domain.field.Field;
import com.formease.api.domain.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity( name = "Form" )
@Table( name = "forms" )
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode( of = "id" )
public class Form
{
    public enum States { ACTIVE, SUSPENDED, DELETED };

    @Id @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;
    private String name;
    private String info;

    @OneToMany( cascade = CascadeType.ALL )
    private List<Field> fields = new ArrayList<>();

    @ManyToOne
    @JoinColumn( name = "ref_owner" )
    private User owner;

    @Enumerated( EnumType.STRING )
    private States state;

    public Form( AddFormData data, User owner )
    {
        this.name = data.name();
        this.info = data.info();
        this.state = States.ACTIVE;
        this.owner = owner;
    }

    public void updateData( UpdateFormData data )
    {
        if ( data.name() != null )
        {
            this.name = data.name();
        }

        if ( data.info() != null )
        {
            this.info = data.info();
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
}
