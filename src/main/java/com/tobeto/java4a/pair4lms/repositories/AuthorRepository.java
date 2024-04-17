package com.tobeto.java4a.pair4lms.repositories;

import com.tobeto.java4a.pair4lms.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Integer> {
}
