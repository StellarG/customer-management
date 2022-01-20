package com.enigma.datamanagement.controller;

import com.enigma.datamanagement.dto.CustomerDTO;
import com.enigma.datamanagement.entity.Customer;
import com.enigma.datamanagement.entity.Family;
import com.enigma.datamanagement.entity.Job;
import com.enigma.datamanagement.exception.NotFoundException;
import com.enigma.datamanagement.request.CustomerRequest;
import com.enigma.datamanagement.response.CustomerResponse;
import com.enigma.datamanagement.response.PageResponse;
import com.enigma.datamanagement.service.CustomerService;
import com.enigma.datamanagement.util.WebResponse;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/customers")
public class CustomerController {

    @Autowired
    protected CustomerService customerService;



    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<WebResponse<?>> create (@RequestBody CustomerRequest request){

        Customer customer = new Customer();
        customer.setName(request.getName());
        customer.setPlaceOfBirth(request.getPlaceOfBirth());
        customer.setBirthDate(request.getBirthDate());
        customer.setAddress(request.getAddress());
        customer.setPhone(request.getPhone());
        customer.setReligion(request.getReligion());

        Family family = new Family();
        family.setMotherName(request.getMotherName());
        family.setAddress(request.getFamilyAddress());
        family.setPhone(request.getFamilyPhone());

        Job job = new Job();
        job.setCompanyName(request.getCompanyName());
        job.setJobName(request.getJobName());
        job.setDuration(request.getDuration());

        CustomerResponse customerCreate = customerService.create(customer, family, job);
        WebResponse<?> response = new WebResponse<>("Successfully Create New Customer",customerCreate);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<WebResponse<PageResponse<?>>> getAll (
            @RequestParam(name = "size", defaultValue = "10") Integer size,
            @RequestParam(name = "page",defaultValue = "0") Integer page,
            @RequestParam(name = "sortBy",defaultValue = "name") String sortBy,
            @RequestParam(name = "direction",defaultValue = "ASC") String direction,
            @RequestParam(name = "name",required = false) String name,
            @RequestParam(name = "phone",required = false) String phone
    ){
        Sort sort = Sort.by(Sort.Direction.fromString(direction),sortBy);
        Pageable pageable = PageRequest.of(page,size,sort);
        CustomerDTO customerDTO = new CustomerDTO(name,phone);
        Page<Customer> getCustomers = customerService.getAll(pageable, customerDTO, sort);
        PageResponse<Customer> pageResponse = new PageResponse<>(
                getCustomers.getContent(),
                getCustomers.getTotalElements(),
                getCustomers.getTotalPages(),
                page,
                size,
                sortBy
        );
        WebResponse<PageResponse<?>> response = new WebResponse<>("All Customer Data", pageResponse);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping(value = "/{customerId}")
    public ResponseEntity<WebResponse<?>> getById (@PathVariable("customerId") String id) throws NotFoundException {
        Customer customer = customerService.getById(id);
        WebResponse<?> response = new WebResponse<>(String.format("Customer with ID %s found",id),customer);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping(value = "/{customerId}")
    public ResponseEntity<WebResponse<?>> update (@RequestBody String id, Customer customer){
        Customer customerUpdate = customerService.update(id,customer);
        WebResponse<?> response = new WebResponse<>("Data Successfully Updated",customerUpdate);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping(value = "/{customerId}")
    public ResponseEntity<WebResponse<String>> deleteById(@PathVariable("customerId") String id) {
        String deleteCustomer = customerService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(new WebResponse<>(String.format("ID %s successfully deleted", id), deleteCustomer));
    }
}
