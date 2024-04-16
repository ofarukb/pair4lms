package com.tobeto.java4a.pair4lms.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tobeto.java4a.pair4lms.entities.Book;

public interface BookRepository extends JpaRepository<Book, Integer>{

}
