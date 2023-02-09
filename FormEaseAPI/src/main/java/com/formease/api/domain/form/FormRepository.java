package com.formease.api.domain.form;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface FormRepository
    extends
        JpaRepository<Form, Long>
{
    Page<Form> findByStateIn(Pageable page, List<Form.States> states );
}
