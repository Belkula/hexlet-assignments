package exercise.service;

import exercise.dto.AuthorCreateDTO;
import exercise.dto.AuthorDTO;
import exercise.dto.AuthorUpdateDTO;
import exercise.exception.ResourceNotFoundException;
import exercise.mapper.AuthorMapper;
import exercise.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import exercise.model.*;

@Service
public class AuthorService {
    // BEGIN
    @Autowired
    private AuthorRepository repository;
    @Autowired
    private AuthorMapper mapper;

    public List<AuthorDTO> getAllAuthors() {
        List<Author> authors = repository.findAll();
        return authors.stream()
                .map(mapper::map)
                .toList();
    }

    public AuthorDTO getAuthor(Long id) {
        Author author = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Author with id " + id + " not found"));
        return mapper.map(author);
    }

    public AuthorDTO createAuthor(AuthorCreateDTO createDTO) {
        Author author = mapper.map(createDTO);
        // ?
        repository.save(author);
        return mapper.map(author);
    }

    public AuthorDTO updateAuthor(Long id, AuthorUpdateDTO updateDTO) {
        Author author = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Author with id " + id + " not found"));
        mapper.update(updateDTO, author);
        repository.save(author);
        return mapper.map(author);
    }

    public void deleteAuthor(Long id) {
        repository.deleteById(id);
    }
    // END
}
