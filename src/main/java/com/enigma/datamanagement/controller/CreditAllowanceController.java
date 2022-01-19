package com.enigma.datamanagement.controller;

import com.enigma.datamanagement.dto.CreditAllowanceDTO;
import com.enigma.datamanagement.entity.CreditAllowance;
import com.enigma.datamanagement.request.CreditRequest;
import com.enigma.datamanagement.response.CreditResponse;
import com.enigma.datamanagement.response.PageResponse;
import com.enigma.datamanagement.service.CreditAllowanceService;
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
@RequestMapping("/allowance")
@NoArgsConstructor
public class CreditAllowanceController {
    @Autowired
    protected CreditAllowanceService creditAllowanceService;

    @PostMapping
    public ResponseEntity<WebResponse<?>> create (@RequestBody CreditRequest request) {

        CreditAllowance creditAllowance = new CreditAllowance();
        creditAllowance.setCustomerId(request.getCustomerId());
        creditAllowance.setCreditTotal(request.getCreditTotal());
        creditAllowance.setCreditStatus(request.getCreditStatus());

        CreditResponse creditResponse = creditAllowanceService.create(creditAllowance);
        WebResponse<?> response = new WebResponse<>("Data Successfully Created",creditResponse);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping
    public ResponseEntity<WebResponse<PageResponse<?>>> getAll (
            @RequestParam(name = "size", defaultValue = "10") Integer size,
            @RequestParam(name = "page",defaultValue = "0") Integer page,
            @RequestParam(name = "sortBy",defaultValue = "name") String sortBy,
            @RequestParam(name = "direction",defaultValue = "ASC") String direction,
            @RequestParam(name = "creditStatus",required = false) String creditStatus
    ){
        Sort sort = Sort.by(Sort.Direction.fromString(direction),sortBy);
        Pageable pageable = PageRequest.of(page,size,sort);
        CreditAllowanceDTO creditAllowanceDTO = new CreditAllowanceDTO(creditStatus);
        Page<CreditAllowance> getCreditAllowance = creditAllowanceService.getAll(pageable, creditAllowanceDTO, sort);
        PageResponse<CreditAllowance> pageResponse = new PageResponse<>(
                getCreditAllowance.getContent(),
                getCreditAllowance.getTotalElements(),
                getCreditAllowance.getTotalPages(),
                page,
                size,
                sortBy
        );
        WebResponse<PageResponse<?>> response = new WebResponse<>("All Customer Data", pageResponse);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping(value = "/{creditAllowanceId}")
    public ResponseEntity<WebResponse<?>> update (@RequestBody CreditAllowance creditAllowance){
        CreditResponse creditUpdate = creditAllowanceService.create(creditAllowance);
        WebResponse<?> response = new WebResponse<>("Data Successfully Updated",creditUpdate);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
