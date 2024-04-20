package com.tobeto.java4a.pair4lms.services.concretes;

import com.tobeto.java4a.pair4lms.core.utils.exceptions.types.BusinessException;
import com.tobeto.java4a.pair4lms.entities.Book;
import com.tobeto.java4a.pair4lms.entities.Borrowing;
import com.tobeto.java4a.pair4lms.entities.User;
import com.tobeto.java4a.pair4lms.repositories.BorrowingRepository;
import com.tobeto.java4a.pair4lms.services.abstracts.BookService;
import com.tobeto.java4a.pair4lms.services.abstracts.BorrowingService;
import com.tobeto.java4a.pair4lms.services.abstracts.FineService;
import com.tobeto.java4a.pair4lms.services.abstracts.UserService;
import com.tobeto.java4a.pair4lms.services.dtos.requests.borrowings.AddBorrowingRequest;
import com.tobeto.java4a.pair4lms.services.dtos.requests.borrowings.ReturnBookRequest;
import com.tobeto.java4a.pair4lms.services.dtos.responses.borrowings.AddBorrowingResponse;
import com.tobeto.java4a.pair4lms.services.dtos.responses.borrowings.ReturnBookResponse;
import com.tobeto.java4a.pair4lms.services.dtos.responses.borrowings.ListBorrowingResponse;
import com.tobeto.java4a.pair4lms.services.dtos.responses.borrowings.ListFinedUserResponse;
import com.tobeto.java4a.pair4lms.services.mappers.BorrowingMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BorrowingServiceImpl implements BorrowingService {

    private BorrowingRepository borrowingRepository;
    private FineService fineService;
    private UserService userService;
    private BookService bookService;

    public static final int DEFAULT_QUANTITY = 1;
    public static final int MAX_BORROWING_TIME_IN_DAYS = 30;

    @Override
    public AddBorrowingResponse add(AddBorrowingRequest request) {
        int userId = request.getUserId();
        int bookId = request.getBookId();

        User user = userService.getByUserId(userId);
        Book book = bookService.getByBookId(bookId);

        bookShouldBeAvailable(book.getAvailableQuantity());
        sameBookShouldNotBeBorrowedBeforeReturning(userId, bookId);

        Borrowing borrowing = BorrowingMapper.INSTANCE.borrowingFromAddRequest(request);

        borrowing.setBorrowDate(LocalDate.now());

        LocalDate dueDate = LocalDate.now().plusDays(MAX_BORROWING_TIME_IN_DAYS);
        borrowing.setDueDate(dueDate);

        Borrowing savedBorrowing = borrowingRepository.save(borrowing);

        book.setAvailableQuantity(book.getAvailableQuantity() - DEFAULT_QUANTITY);
        bookService.save(book);

        savedBorrowing.setBook(book);
        savedBorrowing.setUser(user);
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
        return borrowingList.stream().map(BorrowingMapper.INSTANCE::listResponseFromBorrowing)
                .collect(Collectors.toList());
    }

    @Override
    public ReturnBookResponse returnBook(ReturnBookRequest request) {
        int userId = request.getUserId();
        int bookId = request.getBookId();

        userService.getByUserId(userId);
        Book book = bookService.getByBookId(bookId);
        Borrowing borrowingToBeSaved = borrowingRepository.findByUserIdAndBookId(userId, bookId);

        validateBorrowingForReturn(borrowingToBeSaved);

        borrowingToBeSaved.setReturnDate(LocalDate.now());
        Borrowing savedBorrowing = borrowingRepository.save(borrowingToBeSaved);
        book.setAvailableQuantity(book.getAvailableQuantity() + DEFAULT_QUANTITY);
        bookService.save(book);

        double fineAmount = calculateFine(savedBorrowing);

        ReturnBookResponse response = BorrowingMapper.INSTANCE.returnBookResponseFromBorrowing(savedBorrowing);
        response.setTotalFineAmount(fineAmount);
        return response;
    }

    @Override
    public List<ListFinedUserResponse> getFinedUsers() {
        List<ListFinedUserResponse> response = new ArrayList<>();

        List<Borrowing> finedBorrowings = borrowingRepository.findFinedBorrowings();

//		Map<Integer, Double> finedList = finedBorrowings.stream().collect(Collectors
//		.groupingBy(borrowing -> borrowing.getUser().getId(), Collectors.summingDouble(this::calculateFine)));

        List<Integer> userIds = finedBorrowings.stream().map(borrowing -> borrowing.getUser().getId()).distinct()
                .toList();

        for (Integer userId : userIds) {
            List<Borrowing> borrowingsOfUser = finedBorrowings.stream()
                    .filter(borrowing -> borrowing.getUser().getId() == userId).toList();
            double totalFineAmountOfUser = 0;
            for (Borrowing borrowing : borrowingsOfUser) {
                totalFineAmountOfUser += calculateFine(borrowing);
            }
            ListFinedUserResponse finedUserResponse = BorrowingMapper.INSTANCE
                    .listFinedUserResponseFromBorrowing(borrowingsOfUser.get(0));
            finedUserResponse.setTotalFineAmount(totalFineAmountOfUser);
            response.add(finedUserResponse);
        }

        return response;
    }

    private double calculateFine(Borrowing borrowing) {
        LocalDate dueDate = borrowing.getDueDate();
        LocalDate returnDate = borrowing.getReturnDate() == null ? LocalDate.now() : borrowing.getReturnDate();

        if (dueDate.isBefore(returnDate)) {
            long differenceInDays = ChronoUnit.DAYS.between(dueDate, returnDate);
            double dailyAmount = fineService.findCurrentFineAmount().getDailyAmount();
            return differenceInDays * dailyAmount;
        }

        return 0;
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
        if (availableQuantity < DEFAULT_QUANTITY)
            throw new BusinessException("Bu kitap stokta mevcut değildir.");
    }

    private Borrowing getByBorrowingId(int id) {
        return borrowingRepository.findById(id)
                .orElseThrow(() -> new BusinessException(id + " ID'sine sahip bir ödünç alma işlemi bulunamadı."));
    }

    private void sameBookShouldNotBeBorrowedBeforeReturning(int userId, int bookId) {
        Borrowing borrowingToBeSaved = borrowingRepository.findByUserIdAndBookId(userId, bookId);
        if (borrowingToBeSaved != null && borrowingToBeSaved.getReturnDate() == null) {
            throw new BusinessException("Bu kitap için ödünç kaydınız mevcut. Yeniden kayıt açılamaz.");
        }
    }
}
