package com.tobeto.java4a.pair4lms.services.abstracts;

import com.tobeto.java4a.pair4lms.entities.Author;
import com.tobeto.java4a.pair4lms.services.dtos.requests.authors.AddAuthorRequest;
import com.tobeto.java4a.pair4lms.services.dtos.requests.authors.UpdateAuthorRequest;
import com.tobeto.java4a.pair4lms.services.dtos.responses.authors.AddAuthorResponse;
import com.tobeto.java4a.pair4lms.services.dtos.responses.authors.ListAuthorResponse;
import com.tobeto.java4a.pair4lms.services.dtos.responses.authors.UpdateAuthorResponse;

import java.util.List;

public interface AuthorService {
    AddAuthorResponse add(AddAuthorRequest request);
    UpdateAuthorResponse update(UpdateAuthorRequest request);
    List<ListAuthorResponse> getAll();
    ListAuthorResponse getById(int id);
    void delete(int id);
    Author getByAuthorId(int id);
}
