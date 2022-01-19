package com.enigma.datamanagement.service;

import com.enigma.datamanagement.dto.CustomerDTO;
import com.enigma.datamanagement.dto.FamilyDTO;
import com.enigma.datamanagement.entity.Family;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public interface FamilyService {

    Family create (Family family);
    Page<Family> getAll (Pageable pageable, FamilyDTO familyDTO, Sort sort);
    Family getById (String id);
    Family update (Family family);
    String delete (String id);

}
