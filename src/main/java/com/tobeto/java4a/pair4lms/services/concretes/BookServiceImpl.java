package com.tobeto.java4a.pair4lms.services.concretes;

import com.tobeto.java4a.pair4lms.entities.Book;
import com.tobeto.java4a.pair4lms.repositories.BookRepository;
import com.tobeto.java4a.pair4lms.services.abstracts.BookService;
import com.tobeto.java4a.pair4lms.services.dtos.requests.books.AddBookRequest;
import com.tobeto.java4a.pair4lms.services.dtos.requests.books.UpdateBookRequest;
import com.tobeto.java4a.pair4lms.services.dtos.responses.books.AddBookResponse;
import com.tobeto.java4a.pair4lms.services.dtos.responses.books.ListBookResponse;
import com.tobeto.java4a.pair4lms.services.dtos.responses.books.UpdateBookResponse;
import lombok.AllArgsConstructor;
import com.tobeto.java4a.pair4lms.services.mappers.BookMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {
    private BookRepository bookRepository;

    @Override
    public AddBookResponse add(AddBookRequest request) {
        Book book = BookMapper.INSTANCE.bookFromAddRequest(request);
        book = bookRepository.save(book);

        return BookMapper.INSTANCE.addResponseFromBook(book);
    }

    @Override
    public UpdateBookResponse update(UpdateBookRequest request) {
        return null;
    }

    @Override
    public List<ListBookResponse> getAll() {
        return null;
    }

    @Override
    public ListBookResponse getById(int id) {
        return null;
    }

    @Override
    public void delete(int id) {

    }
}
