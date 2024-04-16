package com.tobeto.java4a.pair4lms.controllers;

import com.tobeto.java4a.pair4lms.services.abstracts.BorrowingService;
import com.tobeto.java4a.pair4lms.services.dtos.requests.borrowings.AddBorrowingRequest;
import com.tobeto.java4a.pair4lms.services.dtos.responses.borrowings.AddBorrowingResponse;
import com.tobeto.java4a.pair4lms.services.dtos.responses.borrowings.ListBorrowingResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/borrowings")
public class BorrowingsController {

    private BorrowingService borrowingService;

    @PostMapping("/create-borrowing")
    @ResponseStatus(HttpStatus.CREATED)
    public AddBorrowingResponse add(@RequestBody @Valid AddBorrowingRequest request) {
        return borrowingService.add(request);
    }

    @GetMapping("/get-by-user-id/{userId}")
    public List<ListBorrowingResponse> getByUserId(@PathVariable int userId) {
        return borrowingService.getByUserId(userId);
    }

    @GetMapping("/{id}")
    public ListBorrowingResponse getById(@PathVariable int id) {
        return borrowingService.getById(id);
    }
}
