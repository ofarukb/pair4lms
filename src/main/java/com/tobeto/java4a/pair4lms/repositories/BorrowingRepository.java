package com.tobeto.java4a.pair4lms.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tobeto.java4a.pair4lms.entities.Borrowing;

import java.util.List;
import java.util.Optional;

public interface BorrowingRepository extends JpaRepository<Borrowing, Integer> {
    List<Borrowing> findByUserId(int userId);

    Optional<Borrowing> findByUserIdAndBookId(int userId, int bookId);
}
