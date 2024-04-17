package com.tobeto.java4a.pair4lms.services.abstracts;

import com.tobeto.java4a.pair4lms.entities.Book;
import com.tobeto.java4a.pair4lms.entities.User;
import com.tobeto.java4a.pair4lms.services.dtos.requests.books.AddBookRequest;
import com.tobeto.java4a.pair4lms.services.dtos.requests.books.UpdateBookRequest;
import com.tobeto.java4a.pair4lms.services.dtos.responses.books.AddBookResponse;
import com.tobeto.java4a.pair4lms.services.dtos.responses.books.ListBookResponse;
import com.tobeto.java4a.pair4lms.services.dtos.responses.books.UpdateBookResponse;

import java.util.List;

public interface BookService {
    AddBookResponse add(AddBookRequest request);
    UpdateBookResponse update(UpdateBookRequest request);
    List<ListBookResponse> getAll();
    ListBookResponse getById(int id);
    void delete(int id);
    Book getByBookId(int id);
}
