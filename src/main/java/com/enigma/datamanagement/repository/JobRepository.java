package com.enigma.datamanagement.repository;

import com.enigma.datamanagement.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job, String> {
}
