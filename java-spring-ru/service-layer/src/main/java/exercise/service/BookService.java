package exercise.service;

import exercise.dto.BookCreateDTO;
import exercise.dto.BookDTO;
import exercise.dto.BookUpdateDTO;
import exercise.exception.ResourceNotFoundException;
import exercise.mapper.BookMapper;
import exercise.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import exercise.repository.AuthorRepository;
import exercise.model.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

@Service
public class BookService {
    // BEGIN
    @Autowired
    private AuthorRepository authorRepository;
    
    @Autowired
    private BookRepository repository;
    
    @Autowired
    private BookMapper mapper;

    public List<BookDTO> getAllBooks() {
        List<Book> books = repository.findAll();
        return books.stream()
                .map(mapper::map)
                .toList();
    }

    public BookDTO getBook(Long id) {
        Book book = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Book with id " + id + " not found"));
        return mapper.map(book);
    }

    public BookDTO createBook(BookCreateDTO createDTO) {
        Author author = authorRepository.findById(createDTO.getAuthorId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Author not found"));
        
        Book book = mapper.map(createDTO);
        book.setAuthor(author);
        repository.save(book);
        return mapper.map(book);
    }

    public BookDTO updateBook(Long id, BookUpdateDTO updateDTO) {
        Book book = repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Book with id " + id + " not found"));
        mapper.update(updateDTO, book);
        repository.save(book);
        return mapper.map(book);
    }

    public void deleteBook(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Book with id " + id + " not found");
        }
        repository.deleteById(id);
    }
    // END
}
