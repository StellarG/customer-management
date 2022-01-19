package com.enigma.datamanagement.repository;

import com.enigma.datamanagement.entity.Family;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FamilyRepository extends JpaRepository<Family, String> {
}
