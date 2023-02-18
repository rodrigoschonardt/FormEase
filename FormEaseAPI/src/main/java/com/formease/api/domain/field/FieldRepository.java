package com.formease.api.domain.field;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FieldRepository
    extends
        JpaRepository<Field, Long>
{
    Page<Field> findByFormId( Pageable page, Long formId );
}
