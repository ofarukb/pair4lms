package com.tobeto.java4a.pair4lms.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tobeto.java4a.pair4lms.entities.Borrowing;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BorrowingRepository extends JpaRepository<Borrowing, Integer> {
    List<Borrowing> findByUserId(int userId);

    Borrowing findByUserIdAndBookId(@Param("userId") int userId, @Param("bookId") int bookId);
    
    @Query("select b from Borrowing b where b.returnDate is null and b.dueDate < current_date")
    List<Borrowing> findFinedBorrowings();
}
