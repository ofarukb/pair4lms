package com.tobeto.java4a.pair4lms.services.mappers;

import com.tobeto.java4a.pair4lms.entities.Borrowing;
import com.tobeto.java4a.pair4lms.services.dtos.requests.borrowings.AddBorrowingRequest;
import com.tobeto.java4a.pair4lms.services.dtos.responses.borrowings.AddBorrowingResponse;
import com.tobeto.java4a.pair4lms.services.dtos.responses.borrowings.ListBorrowingResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BorrowingMapper {

    BorrowingMapper INSTANCE = Mappers.getMapper(BorrowingMapper.class);

    @Mapping(target = "book.id", source = "bookId")
    @Mapping(target = "user.id", source = "userId")
    Borrowing borrowingFromAddRequest(AddBorrowingRequest request);

    @Mapping(target = "bookId", source = "book.id")
    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "userFirstName", source = "user.firstName")
    @Mapping(target = "userLastName", source = " user.lastName")
    @Mapping(target = "bookName", source = "book.name")
    AddBorrowingResponse addResponseFromBorrowing(Borrowing borrowing);

    @Mapping(target = "bookId", source = "book.id")
    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "userFirstName", source = "user.firstName")
    @Mapping(target = "userLastName", source = " user.lastName")
    @Mapping(target = "bookName", source = "book.name")
    ListBorrowingResponse listResponseFromBorrowing(Borrowing borrowing);
}
