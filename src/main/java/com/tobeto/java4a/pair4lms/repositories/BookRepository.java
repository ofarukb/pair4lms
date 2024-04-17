package com.tobeto.java4a.pair4lms.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tobeto.java4a.pair4lms.entities.Book;

import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Integer>{
    Optional<Book> findByNameIgnoreCase(String name);

}
