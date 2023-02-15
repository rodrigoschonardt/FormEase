package com.formease.api.controller;

import com.formease.api.domain.form.*;
import com.formease.api.domain.user.User;
import com.formease.api.domain.user.UserRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping( "/forms" )
public class FormController
{
    @Autowired
    FormRepository formRepository;

    @Autowired
    UserRepository userRepository;

    @PostMapping
    @Transactional
    public ResponseEntity add( @RequestBody @Valid AddFormData data, UriComponentsBuilder uriBuilder )
    {
        User owner = userRepository.getReferenceById(data.ownerId());

        Form form = new Form( data, owner );

        formRepository.save( form );

        URI uri = uriBuilder.path( "/forms/{id}" ).buildAndExpand( form.getId() ).toUri();

        return ResponseEntity.created( uri ).body( new FormDetailsData( form ) );

    }

    @GetMapping( "/owner/{ownerId}" )
    public ResponseEntity<Page<FormDetailsData>> getForms( Pageable page, @PathVariable Long ownerId )
    {
        Page<FormDetailsData> forms = formRepository.findByStateInAndOwnerId( page,
                                                                              List.of( Form.States.ACTIVE,
                                                                                       Form.States.SUSPENDED ),
                                                                              ownerId ).map( FormDetailsData :: new );

        return ResponseEntity.ok( forms );
    }

    @GetMapping( "/{id}" )
    public ResponseEntity getForm( @PathVariable Long id )
    {
        Form form = formRepository.getReferenceById( id );

        return ResponseEntity.ok( new FormDetailsData( form ) );
    }

    @PutMapping
    @Transactional
    public ResponseEntity update( @RequestBody @Valid UpdateFormData data )
    {
        Form form = formRepository.getReferenceById( data.id() );

        form.updateData( data );

        return ResponseEntity.ok( new FormDetailsData( form ) );
    }

    @DeleteMapping( "/{id}" )
    @Transactional
    public ResponseEntity delete( @PathVariable Long id )
    {
        Form form = formRepository.getReferenceById( id );

        form.delete();

        return ResponseEntity.noContent().build();
    }
}
