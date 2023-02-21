package com.formease.api.controller;

import com.formease.api.domain.field.*;
import com.formease.api.domain.form.Form;
import com.formease.api.domain.form.FormRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping( "/fields" )
@SecurityRequirement( name = "bearer-key" )
public class FieldController
{
    @Autowired
    FieldRepository fieldRepository;

    @Autowired
    FormRepository formRepository;

    @PostMapping
    @Transactional
    public ResponseEntity add( @RequestBody @Valid AddFieldData data, UriComponentsBuilder uriBuilder )
    {
        Form form = formRepository.findById( data.formId() ).get();

        Field field = new Field( form, data );

        fieldRepository.save( field );

        URI uri = uriBuilder.path( "fields/{id}" ).buildAndExpand( field.getId() ).toUri();

        return ResponseEntity.created( uri ).body( new FieldDetailsData( field ) );
    }

    @GetMapping( "/form/{formId}" )
    public ResponseEntity<Page<FieldDetailsData>> getFields( Pageable page, @PathVariable Long formId )
    {
        Page<FieldDetailsData> fields = fieldRepository.findByFormIdAndState( page, formId, Field.States.ACTIVE )
                                                       .map( FieldDetailsData :: new );

        return ResponseEntity.ok( fields );
    }

    @GetMapping( "/{id}" )
    public ResponseEntity<FieldDetailsData> getField( @PathVariable Long id )
    {
        FieldDetailsData field = new FieldDetailsData( fieldRepository.getReferenceById( id ) );

        return ResponseEntity.ok( field );
    }

    @PutMapping
    @Transactional
    public ResponseEntity update( @RequestBody @Valid UpdateFieldData data )
    {
        Field field = fieldRepository.getReferenceById( data.id() );

        field.update( data );

        return ResponseEntity.ok( new  FieldDetailsData( field ) );
    }

    @DeleteMapping( "/{id}" )
    @Transactional
    public ResponseEntity delete( @PathVariable Long id )
    {
        Field field = fieldRepository.getReferenceById( id );

        field.delete();

        return ResponseEntity.noContent().build();
    }
}
