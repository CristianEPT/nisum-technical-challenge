package com.nisum.core.nisumtechnicalchallenge.adapter.out.persistence.h2.repository;

import com.nisum.core.nisumtechnicalchallenge.adapter.out.persistence.h2.model.PhoneEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhoneRepository extends JpaRepository<PhoneEntity, Integer> {}
