package com.enigma.datamanagement.controller;

import com.enigma.datamanagement.entity.Job;
import com.enigma.datamanagement.exception.NotFoundException;
import com.enigma.datamanagement.response.PageResponse;
import com.enigma.datamanagement.service.JobService;
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
@RequestMapping(value = "/job")
@NoArgsConstructor
public class JobController {

    @Autowired
    protected JobService jobService;

    @PostMapping
    public ResponseEntity<WebResponse<?>> create (@RequestBody Job job){
        WebResponse<?> response = new WebResponse<>("Data Has Been Created!",job);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping
    public ResponseEntity<WebResponse<PageResponse<?>>> getAll (
            @RequestParam(name = "size", defaultValue = "10") Integer size,
            @RequestParam(name = "page",defaultValue = "0") Integer page,
            @RequestParam(name = "sortBy",defaultValue = "companyName") String sortBy,
            @RequestParam(name = "direction",defaultValue = "ASC") String direction
    ){
        Sort sort = Sort.by(Sort.Direction.fromString(direction),sortBy);
        Pageable pageable = PageRequest.of(page,size,sort);
        Page<Job> getFamily = jobService.getAll(pageable, sort);
        PageResponse<Job> pageResponse = new PageResponse<>(
                getFamily.getContent(),
                getFamily.getTotalElements(),
                getFamily.getTotalPages(),
                page,
                size,
                sortBy
        );
        WebResponse<PageResponse<?>> response = new WebResponse<>("All Data Successfully Retrieved", pageResponse);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping(value = "/{jobId}")
    public ResponseEntity<WebResponse<?>> getById (@PathVariable("jobId") String id) throws NotFoundException {
        Job job = jobService.getById(id);
        WebResponse<?> response = new WebResponse<>(String.format("Customer with ID %s found", id), job);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping(value = "/{jobId}")
    public ResponseEntity<WebResponse<String>> deleteById(@PathVariable("familyId") String id) {
        String deleteCustomer = jobService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(new WebResponse<>(String.format("ID %s successfully deleted", id), deleteCustomer));
    }
}
