package com.enigma.datamanagement.service.impl;

import com.enigma.datamanagement.dto.CustomerDTO;
import com.enigma.datamanagement.entity.Customer;
import com.enigma.datamanagement.entity.Family;
import com.enigma.datamanagement.entity.Job;
import com.enigma.datamanagement.exception.NotFoundException;
import com.enigma.datamanagement.repository.CustomerRepository;
import com.enigma.datamanagement.repository.FamilyRepository;
import com.enigma.datamanagement.repository.JobRepository;
import com.enigma.datamanagement.response.CustomerResponse;
import com.enigma.datamanagement.service.CustomerService;
import com.enigma.datamanagement.specification.CustomerSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService{

    @Autowired
    protected CustomerRepository customerRepository;
    @Autowired
    protected FamilyRepository familyRepository;
    @Autowired
    protected JobRepository jobRepository;

    @Override
    public CustomerResponse create(Customer customer, Family family, Job job) {
        Customer saveCustomer = customerRepository.save(customer);
        Family saveFamily = familyRepository.save(family);
        Job saveJob = jobRepository.save(job);
        return new CustomerResponse(
                saveCustomer.getId(),
                saveCustomer.getName(),
                saveCustomer.getPlaceOfBirth(),
                saveCustomer.getBirthDate(),
                saveCustomer.getAddress(),
                saveCustomer.getPhone(),
                saveCustomer.getReligion(),
                saveFamily.getMotherName(),
                saveFamily.getAddress(),
                saveFamily.getPhone(),
                saveJob.getCompanyName(),
                saveJob.getJobName(),
                saveJob.getDuration(),
                saveCustomer.getCreatedBy(),
                saveCustomer.getUpdatedBy()
        );
    }

    @Override
    public Page<Customer> getAll(Pageable pageable, CustomerDTO customerDTO, Sort sort) {
        Specification<Customer> specification = CustomerSpecification.getSpecification(customerDTO);
        return customerRepository.findAll(specification,pageable);
    }

    @Override
    public Customer getById(String id) {
        return findByIdOrThrowNotFound(id);
    }

    @Override
    public Customer update(String id,Customer customer) {

        Customer updateCustomer = findByIdOrThrowNotFound(id);
        updateCustomer.setName(customer.getName());
        updateCustomer.setPlaceOfBirth(customer.getPlaceOfBirth());
        updateCustomer.setBirthDate(customer.getBirthDate());
        updateCustomer.setAddress(customer.getAddress());
        updateCustomer.setPhone(customer.getPhone());
        updateCustomer.setReligion(customer.getReligion());

        return customerRepository.save(customer);
    }

    @Override
    public String delete(String id) {
        Customer customer = findByIdOrThrowNotFound(id);
        if (customer.getIsDeleted() == null) {
            customer.setIsDeleted(false);
            customerRepository.save(customer);
        }
        if (customer.getIsDeleted()){
            throw new NotFoundException("Customer Not Found!");
        } else {
            customer.setIsDeleted(true);
            customerRepository.save(customer);
        }
        return "Customer has been Deleted!";
    }

    private Customer findByIdOrThrowNotFound(String id) {
        Optional<Customer> customer = customerRepository.findById(id);
        if (customer.isPresent()){
            return customer.get();
        } else {
            throw new NotFoundException("Customer Not Found");
        }
    }
}
