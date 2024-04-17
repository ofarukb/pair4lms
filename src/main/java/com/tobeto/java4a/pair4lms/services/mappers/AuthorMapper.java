package com.tobeto.java4a.pair4lms.services.mappers;

import com.tobeto.java4a.pair4lms.entities.Author;
import com.tobeto.java4a.pair4lms.services.dtos.requests.authors.AddAuthorRequest;
import com.tobeto.java4a.pair4lms.services.dtos.requests.authors.UpdateAuthorRequest;
import com.tobeto.java4a.pair4lms.services.dtos.responses.authors.AddAuthorResponse;
import com.tobeto.java4a.pair4lms.services.dtos.responses.authors.ListAuthorResponse;
import com.tobeto.java4a.pair4lms.services.dtos.responses.authors.UpdateAuthorResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AuthorMapper {

    AuthorMapper INSTANCE = Mappers.getMapper(AuthorMapper.class);

    Author authorFromAddRequest(AddAuthorRequest request);

    AddAuthorResponse addResponseFromAuthor(Author author);

    Author authorFromUpdateRequest(UpdateAuthorRequest request);

    UpdateAuthorResponse updateResponseFromAuthor(Author author);

    ListAuthorResponse listResponseFromAuthor(Author author);
}
