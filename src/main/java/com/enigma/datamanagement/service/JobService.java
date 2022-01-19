package com.enigma.datamanagement.service;

import com.enigma.datamanagement.entity.Job;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public interface JobService {

    Job create (Job job);
    Page<Job> getAll (Pageable pageable, Sort sort);
    Job getById (String id);
    Job update (Job job);
    String delete (String id);

}
