package com.enigma.datamanagement.service;

import com.enigma.datamanagement.dto.CustomerDTO;
import com.enigma.datamanagement.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public interface CustomerService {

    Customer create (Customer customer);
    Page<Customer> getAll (Pageable pageable, CustomerDTO customerDTO, Sort sort);
    Customer getById (String Id);
}
