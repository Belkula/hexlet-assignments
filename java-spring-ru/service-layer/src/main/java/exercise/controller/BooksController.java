package exercise.controller;

import java.util.List;

import exercise.dto.BookCreateDTO;
import exercise.dto.BookDTO;
import exercise.dto.BookUpdateDTO;
import exercise.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;

import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/books")
public class BooksController {
    @Autowired
    private BookService bookService;

    // BEGIN
    @GetMapping(path = "")
    public ResponseEntity<List<BookDTO>> index() {
        List<BookDTO> authors = bookService.getAllBooks();
        return ResponseEntity.ok().header("X-Total-Count", String.valueOf(authors.size())).body(authors);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<BookDTO> show(@PathVariable Long id) {
        return ResponseEntity.ok().body(bookService.getBook(id));
    }

    @PostMapping(path = "")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<BookDTO> create(@Valid @RequestBody BookCreateDTO createDTO) {
            return ResponseEntity.status(HttpStatus.CREATED).body(bookService.createBook(createDTO));
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<BookDTO> update(@PathVariable Long id, @Valid @RequestBody BookUpdateDTO updateDTO) {
        return ResponseEntity.ok().body(bookService.updateBook(id, updateDTO));
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable Long id) {
        bookService.deleteBook(id);
    }
    // END
}
