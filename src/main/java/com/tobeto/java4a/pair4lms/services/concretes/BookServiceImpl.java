package com.tobeto.java4a.pair4lms.services.concretes;

import com.tobeto.java4a.pair4lms.core.utils.exceptions.types.BusinessException;
import com.tobeto.java4a.pair4lms.entities.Author;
import com.tobeto.java4a.pair4lms.entities.Book;
import com.tobeto.java4a.pair4lms.repositories.BookRepository;
import com.tobeto.java4a.pair4lms.services.abstracts.AuthorService;
import com.tobeto.java4a.pair4lms.services.abstracts.BookService;
import com.tobeto.java4a.pair4lms.services.dtos.requests.books.AddBookRequest;
import com.tobeto.java4a.pair4lms.services.dtos.requests.books.UpdateBookRequest;
import com.tobeto.java4a.pair4lms.services.dtos.responses.books.AddBookResponse;
import com.tobeto.java4a.pair4lms.services.dtos.responses.books.ListBookResponse;
import com.tobeto.java4a.pair4lms.services.dtos.responses.books.UpdateBookResponse;
import lombok.AllArgsConstructor;
import com.tobeto.java4a.pair4lms.services.mappers.BookMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {
	private BookRepository bookRepository;
	private AuthorService authorService;

	@Override
	public AddBookResponse add(AddBookRequest request) {
		Author author = authorService.getByAuthorId(request.getAuthorId());
		Book book = BookMapper.INSTANCE.bookFromAddRequest(request);
		Book savedBook = bookRepository.save(book);

		savedBook.setAuthor(author);
		return BookMapper.INSTANCE.addResponseFromBook(savedBook);
	}

	@Override
	public UpdateBookResponse update(UpdateBookRequest request) {
		getByBookId(request.getId());
		authorService.getByAuthorId(request.getAuthorId());
		Book bookToBeSaved = BookMapper.INSTANCE.bookFromUpdateRequest(request);
		Book savedBook = bookRepository.save(bookToBeSaved);

		return BookMapper.INSTANCE.updateResponseFromBook(savedBook);
	}

	@Override
	public List<ListBookResponse> getAll() {
		List<Book> bookList = bookRepository.findAll();
		return bookList.stream().map(BookMapper.INSTANCE::listResponseFromBook).collect(Collectors.toList());
	}

	@Override
	public ListBookResponse getById(int id) {
		Book book = getByBookId(id);
		return BookMapper.INSTANCE.listResponseFromBook(book);
	}

	@Override
	public void delete(int id) {
		bookRepository.deleteById(id);
	}

	@Override
	public Book getByBookId(int id) {
		return bookRepository.findById(id)
				.orElseThrow(() -> new BusinessException(id + " ID'sine sahip bir kitap bulunamadı."));
	}

	@Override
	public Book save(Book book) {
		return bookRepository.save(book);
	}
}
