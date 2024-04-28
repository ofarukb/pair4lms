package com.tobeto.java4a.pair4lms.controllers;

import com.tobeto.java4a.pair4lms.services.abstracts.AuthorService;
import com.tobeto.java4a.pair4lms.services.dtos.requests.authors.AddAuthorRequest;
import com.tobeto.java4a.pair4lms.services.dtos.requests.authors.UpdateAuthorRequest;
import com.tobeto.java4a.pair4lms.services.dtos.responses.authors.AddAuthorResponse;
import com.tobeto.java4a.pair4lms.services.dtos.responses.authors.ListAuthorResponse;
import com.tobeto.java4a.pair4lms.services.dtos.responses.authors.UpdateAuthorResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/authors")
public class AuthorsController {

    private AuthorService authorService;

    @PostMapping("/create-author")
    @ResponseStatus(HttpStatus.CREATED)
    public AddAuthorResponse add(@RequestBody @Valid AddAuthorRequest request) {
        return authorService.add(request);
    }

    @PutMapping("/update-author")
    public UpdateAuthorResponse update(@RequestBody @Valid UpdateAuthorRequest request) {
        return authorService.update(request);
    }

    @GetMapping("/get-all")
    public List<ListAuthorResponse> getAll() {
        return authorService.getAll();
    }

    @GetMapping("/{id}")
    public ListAuthorResponse getById(@PathVariable int id) {
        return authorService.getById(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        authorService.delete(id);
    }
}
