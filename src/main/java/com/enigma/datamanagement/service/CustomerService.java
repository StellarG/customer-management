package com.enigma.datamanagement.service;

import com.enigma.datamanagement.dto.CustomerDTO;
import com.enigma.datamanagement.entity.Customer;
import com.enigma.datamanagement.entity.Family;
import com.enigma.datamanagement.entity.Job;
import com.enigma.datamanagement.response.CustomerResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public interface CustomerService{

    CustomerResponse create (Customer customer, Family family, Job job);
    Page<Customer> getAll (Pageable pageable, CustomerDTO customerDTO, Sort sort);
    Customer getById (String id);
    Customer update(Customer customer);
    String delete (String id);
}
