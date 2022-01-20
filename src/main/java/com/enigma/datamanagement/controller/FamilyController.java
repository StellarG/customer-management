package com.enigma.datamanagement.controller;

import com.enigma.datamanagement.dto.FamilyDTO;
import com.enigma.datamanagement.entity.Family;
import com.enigma.datamanagement.exception.NotFoundException;
import com.enigma.datamanagement.response.PageResponse;
import com.enigma.datamanagement.service.FamilyService;
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
@RequestMapping("/family")
@NoArgsConstructor
public class FamilyController {
    @Autowired
    protected FamilyService familyService;

    @PostMapping
    public ResponseEntity<WebResponse<?>> create (@RequestBody Family family){
        WebResponse<?> response = new WebResponse<>("Data Has Been Created!",family);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping
    public ResponseEntity<WebResponse<PageResponse<?>>> getAll (
            @RequestParam(name = "size", defaultValue = "10") Integer size,
            @RequestParam(name = "page",defaultValue = "0") Integer page,
            @RequestParam(name = "sortBy",defaultValue = "motherName") String sortBy,
            @RequestParam(name = "direction",defaultValue = "ASC") String direction,
            @RequestParam(name = "motherName",required = false) String motherName,
            @RequestParam(name = "familyPhone",required = false) String familyPhone
    ){
        Sort sort = Sort.by(Sort.Direction.fromString(direction),sortBy);
        Pageable pageable = PageRequest.of(page,size,sort);
        FamilyDTO familyDTO = new FamilyDTO(motherName,familyPhone);
        Page<Family> getFamily = familyService.getAll(pageable, familyDTO, sort);
        PageResponse<Family> pageResponse = new PageResponse<>(
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

    @GetMapping(value = "/{familyId}")
    public ResponseEntity<WebResponse<?>> getById (@PathVariable("familyId") String id) throws NotFoundException {
        Family family = familyService.getById(id);
        WebResponse<?> response = new WebResponse<>(String.format("Customer with ID %s found", id), family);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping(value = "/{familyId}")
    public ResponseEntity<WebResponse<?>> update (@RequestBody Family family){
        Family familyUpdate = familyService.update(family);
        WebResponse<?> response = new WebResponse<>("Data Successfully Updated",familyUpdate);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping(value = "/{familyId}")
    public ResponseEntity<WebResponse<String>> deleteById(@PathVariable("familyId") String id) {
        String deleteCustomer = familyService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(new WebResponse<>(String.format("ID %s successfully deleted", id), deleteCustomer));
    }
}
