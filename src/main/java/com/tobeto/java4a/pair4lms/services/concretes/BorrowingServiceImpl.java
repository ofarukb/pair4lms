package com.tobeto.java4a.pair4lms.services.concretes;


import com.tobeto.java4a.pair4lms.core.utils.exceptions.types.BusinessException;
import com.tobeto.java4a.pair4lms.entities.Book;
import com.tobeto.java4a.pair4lms.entities.Borrowing;
import com.tobeto.java4a.pair4lms.repositories.BookRepository;
import com.tobeto.java4a.pair4lms.repositories.BorrowingRepository;
import com.tobeto.java4a.pair4lms.services.abstracts.BookService;
import com.tobeto.java4a.pair4lms.services.abstracts.BorrowingService;
import com.tobeto.java4a.pair4lms.services.abstracts.UserService;
import com.tobeto.java4a.pair4lms.services.dtos.requests.borrowings.AddBorrowingRequest;
import com.tobeto.java4a.pair4lms.services.dtos.responses.borrowings.AddBorrowingResponse;
import com.tobeto.java4a.pair4lms.services.dtos.responses.borrowings.ListBorrowingResponse;
import com.tobeto.java4a.pair4lms.services.mappers.BorrowingMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class BorrowingServiceImpl implements BorrowingService {

    private BorrowingRepository borrowingRepository;
    private BookRepository bookRepository;
    private UserService userService;
    private BookService bookService;

    public final int DEFAULT_QUANTITY = 1;

    @Override
    public AddBorrowingResponse add(AddBorrowingRequest request) {
        userService.getByUserId(request.getUserId());
        Book book = bookService.getByBookId(request.getBookId());

        bookShouldBeAvailable(book.getAvailableQuantity());

        Borrowing borrowing = BorrowingMapper.INSTANCE.borrowingFromAddRequest(request);
        Borrowing savedBorrowing = borrowingRepository.save(borrowing);
        book.setAvailableQuantity(book.getAvailableQuantity() - DEFAULT_QUANTITY);
        bookRepository.save(book);

        return BorrowingMapper.INSTANCE.addResponseFromBorrowing(savedBorrowing);
    }

    @Override
    public ListBorrowingResponse getById(int id) {
        Borrowing borrowing = borrowingRepository.findById(id).orElse(null);
        return BorrowingMapper.INSTANCE.listResponseFromBorrowing(borrowing);
    }

    @Override
    public List<ListBorrowingResponse> getByUserId(int userId) {
        List<Borrowing> borrowings = borrowingRepository.findByUserId(userId);
        List<ListBorrowingResponse> response = new ArrayList<>();
        for (Borrowing borrowing : borrowings) {
            response.add(BorrowingMapper.INSTANCE.listResponseFromBorrowing(borrowing));
        }
        return response;
    }

    private void bookShouldBeAvailable(int availableQuantity) {
        if (availableQuantity < DEFAULT_QUANTITY)
            throw new BusinessException("Bu kitap stokta mevcut değildir.");
    }

}
