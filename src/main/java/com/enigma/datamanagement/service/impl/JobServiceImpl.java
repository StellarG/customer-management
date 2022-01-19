package com.enigma.datamanagement.service.impl;

import com.enigma.datamanagement.entity.Job;
import com.enigma.datamanagement.exception.NotFoundException;
import com.enigma.datamanagement.repository.JobRepository;
import com.enigma.datamanagement.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class JobServiceImpl implements JobService {

    @Autowired
    protected JobRepository jobRepository;

    @Override
    public Job create(Job job) {
        return jobRepository.save(job);
    }

    @Override
    public Page<Job> getAll(Pageable pageable, Sort sort) {
        return jobRepository.findAll(pageable);
    }

    @Override
    public Job getById(String id) {
        return findByIdOrThrowNotFound(id);
    }

    @Override
    public Job update(Job job) {
        findByIdOrThrowNotFound(job.getId());
        return jobRepository.save(job);
    }

    @Override
    public String delete(String id) {
        Job job = findByIdOrThrowNotFound(id);
        if (job.getIsDeleted()){
            throw new NotFoundException("Not Found!");
        } else {
            job.setIsDeleted(true);
            jobRepository.save(job);
        }
        return "Data Has Been Deleted!";
    }

    private Job findByIdOrThrowNotFound (String id) {
        Optional<Job> job = jobRepository.findById(id);
        if (job.isPresent()){
            return job.get();
        } else {
            throw new NotFoundException("Job Not Found");
        }
    }
}
