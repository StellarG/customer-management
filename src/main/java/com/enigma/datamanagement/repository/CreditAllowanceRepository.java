package com.enigma.datamanagement.repository;

import com.enigma.datamanagement.entity.CreditAllowance;
import com.enigma.datamanagement.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditAllowanceRepository extends JpaRepository<CreditAllowance,String> {
    Page<CreditAllowance> findAll(Pageable pageable);
    Page<CreditAllowance> findAll(Specification<CreditAllowance> specification, Pageable pageable);


}
