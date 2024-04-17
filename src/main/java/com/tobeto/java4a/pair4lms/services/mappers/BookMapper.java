package com.tobeto.java4a.pair4lms.services.mappers;

import com.tobeto.java4a.pair4lms.entities.Book;
import com.tobeto.java4a.pair4lms.services.dtos.requests.books.AddBookRequest;
import com.tobeto.java4a.pair4lms.services.dtos.requests.books.UpdateBookRequest;
import com.tobeto.java4a.pair4lms.services.dtos.responses.books.AddBookResponse;
import com.tobeto.java4a.pair4lms.services.dtos.responses.books.ListBookResponse;
import com.tobeto.java4a.pair4lms.services.dtos.responses.books.UpdateBookResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;



@Mapper
public interface BookMapper {

    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);

    Book bookFromAddRequest(AddBookRequest request);

    AddBookResponse addResponseFromBook(Book book);

    Book bookFromUpdateRequest(UpdateBookRequest request);

    UpdateBookResponse updateResponseFromBook(Book book);

    ListBookResponse listResponseFromBook(Book book);
}
