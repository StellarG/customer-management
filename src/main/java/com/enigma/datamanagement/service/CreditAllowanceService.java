package com.enigma.datamanagement.service;

import com.enigma.datamanagement.dto.CreditAllowanceDTO;
import com.enigma.datamanagement.entity.CreditAllowance;
import com.enigma.datamanagement.request.CreditRequest;
import com.enigma.datamanagement.response.CreditResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public interface CreditAllowanceService {

    CreditResponse create (CreditAllowance creditAllowance);
    Page<CreditAllowance> getAll (Pageable pageable, CreditAllowanceDTO creditAllowanceDTO, Sort sort);
    CreditAllowance getById (String id);
    CreditAllowance update (CreditAllowance creditAllowance);
}
