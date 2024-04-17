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

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {
    private BookRepository bookRepository;

    @Override
    public AddBookResponse add(AddBookRequest request) {
        Book book = BookMapper.INSTANCE.bookFromAddRequest(request);
        Book savedBook = bookRepository.save(book);

        return BookMapper.INSTANCE.addResponseFromBook(savedBook);
    }

    @Override
    public UpdateBookResponse update(UpdateBookRequest request) {
        Book book = BookMapper.INSTANCE.bookFromUpdateRequest(request);
        Book savedBook = bookRepository.save(book);

        return BookMapper.INSTANCE.updateResponseFromBook(savedBook);
    }

    @Override
    public List<ListBookResponse> getAll() {
        List<Book> bookList = bookRepository.findAll();
        List<ListBookResponse> response = new ArrayList<>();
        for (Book book : bookList) {
            response.add(BookMapper.INSTANCE.listResponseFromBook(book));
        }
        return response;
    }

    @Override
    public ListBookResponse getById(int id) {
        Book book = bookRepository.findById(id).orElse(null);

        return BookMapper.INSTANCE.listResponseFromBook(book);
    }

    @Override
    public void delete(int id) {
        bookRepository.deleteById(id);
    }
}
