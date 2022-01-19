package com.enigma.datamanagement.repository;

import com.enigma.datamanagement.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,String> {

    Page<Customer> findAll(Pageable pageable);
    Page<Customer> findAll(Specification<Customer> specification, Pageable pageable);
}
