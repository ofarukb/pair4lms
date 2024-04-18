package com.tobeto.java4a.pair4lms.services.abstracts;

import com.tobeto.java4a.pair4lms.services.dtos.requests.borrowings.AddBorrowingRequest;
import com.tobeto.java4a.pair4lms.services.dtos.requests.borrowings.ReturnBookRequest;
import com.tobeto.java4a.pair4lms.services.dtos.responses.borrowings.AddBorrowingResponse;
import com.tobeto.java4a.pair4lms.services.dtos.responses.borrowings.ReturnBookResponse;
import com.tobeto.java4a.pair4lms.services.dtos.responses.borrowings.ListBorrowingResponse;
import com.tobeto.java4a.pair4lms.services.dtos.responses.borrowings.ListFinedUserResponse;

import java.util.List;

public interface BorrowingService {
    AddBorrowingResponse add(AddBorrowingRequest request);
    ListBorrowingResponse getById(int id);
    List<ListBorrowingResponse> getByUserId(int userId);
    ReturnBookResponse returnBook(ReturnBookRequest request);
    List<ListFinedUserResponse> getFinedUsers();
}
