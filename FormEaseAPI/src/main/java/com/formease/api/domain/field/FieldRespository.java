package com.formease.api.domain.field;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface FieldRespository
    extends
        JpaRepository<Field, Long>
{
}
