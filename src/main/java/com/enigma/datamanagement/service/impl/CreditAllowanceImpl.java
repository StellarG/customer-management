package com.enigma.datamanagement.service.impl;

import com.enigma.datamanagement.dto.CreditAllowanceDTO;
import com.enigma.datamanagement.entity.CreditAllowance;
import com.enigma.datamanagement.exception.NotFoundException;
import com.enigma.datamanagement.repository.CreditAllowanceRepository;
import com.enigma.datamanagement.request.CreditRequest;
import com.enigma.datamanagement.response.CreditResponse;
import com.enigma.datamanagement.service.CreditAllowanceService;
import com.enigma.datamanagement.specification.CreditAllowanceSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class CreditAllowanceImpl implements CreditAllowanceService {

    @Autowired
    protected CreditAllowanceRepository creditAllowanceRepository;

    @Override
    public CreditResponse create(CreditAllowance creditAllowance) {
        CreditAllowance saveCreditAllowance = creditAllowanceRepository.save(creditAllowance);
        return new CreditResponse(
                saveCreditAllowance.getId(),
                saveCreditAllowance.getCustomerId(),
                saveCreditAllowance.getCreditTotal(),
                saveCreditAllowance.getCreditStatus()
        ) ;
    }

    @Override
    public Page<CreditAllowance> getAll(Pageable pageable, CreditAllowanceDTO creditAllowanceDTO, Sort sort) {
        Specification<CreditAllowance> specification = CreditAllowanceSpecification.getSpecification(creditAllowanceDTO);
        return creditAllowanceRepository.findAll(specification,pageable);
    }

    @Override
    public CreditAllowance getById(String id) {
        return findByIdOrThrowNotFound(id);
    }

    @Override
    public CreditAllowance update(CreditAllowance creditAllowance) {
        findByIdOrThrowNotFound(creditAllowance.getId());
        return creditAllowanceRepository.save(creditAllowance);
    }

    private CreditAllowance findByIdOrThrowNotFound (String id) {
        Optional<CreditAllowance> creditAllowance = creditAllowanceRepository.findById(id);
        if (creditAllowance.isPresent()){
            return creditAllowance.get();
        } else {
            throw new NotFoundException("Data Not Found!");
        }
    }
}
