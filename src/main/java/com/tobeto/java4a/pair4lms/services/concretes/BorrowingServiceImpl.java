package com.tobeto.java4a.pair4lms.services.concretes;


import com.tobeto.java4a.pair4lms.core.utils.exceptions.types.BusinessException;
import com.tobeto.java4a.pair4lms.entities.Book;
import com.tobeto.java4a.pair4lms.entities.Borrowing;
import com.tobeto.java4a.pair4lms.repositories.BookRepository;
import com.tobeto.java4a.pair4lms.repositories.BorrowingRepository;
import com.tobeto.java4a.pair4lms.repositories.FineRepository;
import com.tobeto.java4a.pair4lms.services.abstracts.BookService;
import com.tobeto.java4a.pair4lms.services.abstracts.BorrowingService;
import com.tobeto.java4a.pair4lms.services.abstracts.UserService;
import com.tobeto.java4a.pair4lms.services.dtos.requests.borrowings.AddBorrowingRequest;
import com.tobeto.java4a.pair4lms.services.dtos.requests.borrowings.ReturnBookRequest;
import com.tobeto.java4a.pair4lms.services.dtos.responses.borrowings.AddBorrowingResponse;
import com.tobeto.java4a.pair4lms.services.dtos.responses.borrowings.ReturnBookResponse;
import com.tobeto.java4a.pair4lms.services.dtos.responses.borrowings.ListBorrowingResponse;
import com.tobeto.java4a.pair4lms.services.mappers.BorrowingMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BorrowingServiceImpl implements BorrowingService {

    private BorrowingRepository borrowingRepository;
    private BookRepository bookRepository;
    private FineRepository fineRepository;
    private UserService userService;
    private BookService bookService;

    public final int DEFAULT_QUANTITY = 1;
    public final long MAX_BORROWING_INTERVAL_IN_DAYS = 30;

    @Override
    public AddBorrowingResponse add(AddBorrowingRequest request) {
        userService.getByUserId(request.getUserId());
        Book book = bookService.getByBookId(request.getBookId());

        bookShouldBeAvailable(book.getAvailableQuantity());

        Borrowing borrowing = BorrowingMapper.INSTANCE.borrowingFromAddRequest(request);

        borrowing.setBorrowDate(LocalDate.now());

        LocalDate dueDate = LocalDate.now().plusDays(MAX_BORROWING_INTERVAL_IN_DAYS);
        borrowing.setDueDate(dueDate);

        Borrowing savedBorrowing = borrowingRepository.save(borrowing);

        book.setAvailableQuantity(book.getAvailableQuantity() - DEFAULT_QUANTITY);
        bookRepository.save(book);

        return BorrowingMapper.INSTANCE.addResponseFromBorrowing(savedBorrowing);
    }

    @Override
    public ListBorrowingResponse getById(int id) {
        Borrowing borrowing = getByBorrowingId(id);
        return BorrowingMapper.INSTANCE.listResponseFromBorrowing(borrowing);
    }

    @Override
    public List<ListBorrowingResponse> getByUserId(int userId) {
        userService.getByUserId(userId);
        List<Borrowing> borrowingList = borrowingRepository.findByUserId(userId);
        return borrowingList.stream()
                .map(BorrowingMapper.INSTANCE::listResponseFromBorrowing)
                .collect(Collectors.toList());
    }

    @Override
    public ReturnBookResponse returnBook(ReturnBookRequest request) {
        int userId = request.getUserId();
        int bookId = request.getBookId();

        // Kullanıcı ve Kitap bilgilerini al
        userService.getByUserId(userId);
        Book book = bookService.getByBookId(bookId);

        // Ödünç alma kaydını bul
        Borrowing borrowingToBeSaved = borrowingRepository.findByUserIdAndBookId(userId, bookId);

        validateBorrowingForReturn(borrowingToBeSaved);

        // İlgili ödünç alma kaydını güncelle (iade işlemi)
        borrowingToBeSaved.setReturnDate(LocalDate.now());

        Borrowing savedBorrowing = borrowingRepository.save(borrowingToBeSaved);
        book.setAvailableQuantity(book.getAvailableQuantity() + DEFAULT_QUANTITY);
        bookRepository.save(book);

        // Gecikme ücretini hesapla
        double fineAmount = calculateLateFine(borrowingToBeSaved);

        // ReturnBookResponse oluştur ve doldur
        ReturnBookResponse response = BorrowingMapper.INSTANCE.returnBookResponseFromBorrowing(savedBorrowing);
        response.setTotalFineAmount(fineAmount);

        return response;
    }

    private double calculateLateFine(Borrowing borrowing) {
        LocalDate dueDate = borrowing.getDueDate();
        LocalDate returnDate = borrowing.getReturnDate();

        if (dueDate.isBefore(returnDate)) {
            long differenceInDays = ChronoUnit.DAYS.between(dueDate,returnDate);
            double dailyAmount = fineRepository.findCurrentFineAmount().getDailyAmount();
            return differenceInDays * dailyAmount;
        }

        return 0.0;
    }

    private void validateBorrowingForReturn(Borrowing borrowing) {
        if (borrowing == null) {
            throw new BusinessException("Verilen kullanıcı ve kitaba ait iade edilmemiş ödünç alma kaydı bulunamadı.");
        }

        if (borrowing.getReturnDate() != null) {
            throw new BusinessException("Bu kitap zaten iade edilmiş.");
        }
    }

    private void bookShouldBeAvailable(int availableQuantity) {
        if (availableQuantity < DEFAULT_QUANTITY) throw new BusinessException("Bu kitap stokta mevcut değildir.");
    }

    private Borrowing getByBorrowingId(int id){
        return borrowingRepository.findById(id).orElseThrow(() -> new BusinessException(id + " ID'sine sahip bir ödünç alma işlemi bulunamadı."));
    }
}
