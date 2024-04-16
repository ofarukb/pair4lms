package com.tobeto.java4a.pair4lms.controllers;

import com.tobeto.java4a.pair4lms.services.abstracts.BookService;
import com.tobeto.java4a.pair4lms.services.dtos.requests.books.AddBookRequest;
import com.tobeto.java4a.pair4lms.services.dtos.requests.books.UpdateBookRequest;
import com.tobeto.java4a.pair4lms.services.dtos.responses.books.AddBookResponse;
import com.tobeto.java4a.pair4lms.services.dtos.responses.books.ListBookResponse;
import com.tobeto.java4a.pair4lms.services.dtos.responses.books.UpdateBookResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/books")
public class BooksController {

    private BookService bookService;

    @PostMapping("/create-book")
    @ResponseStatus(HttpStatus.CREATED)
    public AddBookResponse add(@RequestBody @Valid AddBookRequest request) {
        return bookService.add(request);
    }

    @PutMapping("/update-book")
    public UpdateBookResponse update(@RequestBody @Valid UpdateBookRequest request) {
        return bookService.update(request);
    }

    @GetMapping("/get-all")
    public List<ListBookResponse> getAll() {
        return bookService.getAll();
    }

    @GetMapping("/{id}")
    public ListBookResponse getById(@PathVariable int id) {
        return bookService.getById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        bookService.delete(id);
    }
}
