package com.enigma.datamanagement.service.impl;

import com.enigma.datamanagement.dto.CustomerDTO;
import com.enigma.datamanagement.dto.FamilyDTO;
import com.enigma.datamanagement.entity.Family;
import com.enigma.datamanagement.exception.NotFoundException;
import com.enigma.datamanagement.repository.FamilyRepository;
import com.enigma.datamanagement.service.FamilyService;
import com.enigma.datamanagement.specification.FamilySpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FamilyServiceImpl implements FamilyService {
    @Autowired
    protected FamilyRepository familyRepository;

    @Override
    public Family create(Family family) {
        return familyRepository.save(family);
    }

    @Override
    public Page<Family> getAll(Pageable pageable, FamilyDTO familyDTO, Sort sort) {
        Specification<Family> specification = FamilySpecification.getSpecification(familyDTO);
        return familyRepository.findAll(specification,pageable);
    }

    @Override
    public Family getById(String id) {
        return findByIdOrThrowNotFound(id);
    }

    @Override
    public Family update(Family family) {
        findByIdOrThrowNotFound(family.getId());
        return familyRepository.save(family);
    }

    @Override
    public String delete(String id) {
        Family family = findByIdOrThrowNotFound(id);
        if (family.getIsDeleted()){
            throw new NotFoundException("Data Not Found!");
        } else {
            family.setIsDeleted(true);
            familyRepository.save(family);
        }
        return "Data Has Been Deleted!";
    }

    private Family findByIdOrThrowNotFound (String id) {
        Optional<Family> family = familyRepository.findById(id);
        if (family.isPresent()){
            return family.get();
        } else {
            throw new NotFoundException("Data Not Found!");
        }
    }
}
