package com.enigma.datamanagement.repository;

import com.enigma.datamanagement.entity.Customer;
import com.enigma.datamanagement.entity.Family;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FamilyRepository extends JpaRepository<Family, String> {
    Page<Family> findAll(Pageable pageable);
    Page<Family> findAll(Specification<Family> specification, Pageable pageable);
}
