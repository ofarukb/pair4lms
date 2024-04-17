package com.tobeto.java4a.pair4lms.services.concretes;

import com.tobeto.java4a.pair4lms.core.utils.exceptions.types.BusinessException;
import com.tobeto.java4a.pair4lms.entities.Author;
import com.tobeto.java4a.pair4lms.repositories.AuthorRepository;
import com.tobeto.java4a.pair4lms.services.abstracts.AuthorService;
import com.tobeto.java4a.pair4lms.services.dtos.requests.authors.AddAuthorRequest;
import com.tobeto.java4a.pair4lms.services.dtos.requests.authors.UpdateAuthorRequest;
import com.tobeto.java4a.pair4lms.services.dtos.responses.authors.AddAuthorResponse;
import com.tobeto.java4a.pair4lms.services.dtos.responses.authors.ListAuthorResponse;
import com.tobeto.java4a.pair4lms.services.dtos.responses.authors.UpdateAuthorResponse;
import com.tobeto.java4a.pair4lms.services.mappers.AuthorMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class AuthorServiceImpl implements AuthorService {

    private AuthorRepository authorRepository;

    @Override
    public AddAuthorResponse add(AddAuthorRequest request) {
        Author author = AuthorMapper.INSTANCE.authorFromAddRequest(request);
        Author savedAuthor = authorRepository.save(author);

        return AuthorMapper.INSTANCE.addResponseFromAuthor(savedAuthor);
    }

    @Override
    public UpdateAuthorResponse update(UpdateAuthorRequest request) {
        Author author = AuthorMapper.INSTANCE.authorFromUpdateRequest(request);
        Author savedAuthor = authorRepository.save(author);

        return AuthorMapper.INSTANCE.updateResponseFromAuthor(savedAuthor);
    }

    @Override
    public List<ListAuthorResponse> getAll() {
        List<Author> authorList = authorRepository.findAll();
        List<ListAuthorResponse> response = new ArrayList<>();
        for (Author author : authorList) {
            response.add(AuthorMapper.INSTANCE.listResponseFromAuthor(author));
        }
        return response;
    }

    @Override
    public ListAuthorResponse getById(int id) {
        Author author = authorRepository.findById(id).orElse(null);

        return AuthorMapper.INSTANCE.listResponseFromAuthor(author);
    }

    @Override
    public void delete(int id) {
        authorRepository.deleteById(id);
    }

    @Override
    public Author getByAuthorId(int id) {
        return authorRepository.findById(id).orElseThrow(() -> new BusinessException(id + " ID'sine sahip bir yazar bulunamadı."));
    }
}
