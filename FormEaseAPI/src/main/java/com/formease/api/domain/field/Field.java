package com.formease.api.domain.field;

import com.formease.api.domain.form.Form;
import jakarta.persistence.*;
import lombok.*;

@Entity( name = "Field" )
@Table( name = "fields" )
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode( of = "id" )
public class Field
{
    public enum Types {  }; // todo

    @Id @GeneratedValue( strategy = GenerationType.IDENTITY )
    private Long id;
    private String label;
    private Boolean required;

    @ManyToOne
    @JoinColumn( name = "ref_form" )
    private Form form;

    private Types type;
}
