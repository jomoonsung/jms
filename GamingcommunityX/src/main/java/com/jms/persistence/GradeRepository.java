package com.jms.persistence;

import org.springframework.data.repository.CrudRepository;

import com.jms.domain.Grade;

public interface GradeRepository extends CrudRepository<Grade, Long> {

}
