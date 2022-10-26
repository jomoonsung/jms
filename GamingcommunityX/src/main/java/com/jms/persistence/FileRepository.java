package com.jms.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jms.domain.Files;


public interface FileRepository extends JpaRepository<Files, Long> {
}
