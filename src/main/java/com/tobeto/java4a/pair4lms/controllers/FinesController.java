package com.tobeto.java4a.pair4lms.controllers;

import com.tobeto.java4a.pair4lms.services.abstracts.FineService;
import com.tobeto.java4a.pair4lms.services.dtos.requests.fines.AddFineRequest;
import com.tobeto.java4a.pair4lms.services.dtos.responses.fines.AddFineResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/fines")
public class FinesController {

    private FineService fineService;

    @PostMapping("/create-fine")
    @ResponseStatus(HttpStatus.CREATED)
    public AddFineResponse add(@RequestBody @Valid AddFineRequest request) {
        return fineService.add(request);
    }
}
