package com.tobeto.java4a.pair4lms.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tobeto.java4a.pair4lms.entities.Fine;
import org.springframework.data.jpa.repository.Query;

public interface FineRepository extends JpaRepository<Fine, Integer> {
    @Query(value = "select f from Fine f order by f.createdAt desc limit 1")
    Fine findCurrentFineAmount();
}
