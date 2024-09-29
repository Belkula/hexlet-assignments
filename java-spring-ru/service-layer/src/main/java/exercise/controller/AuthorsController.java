package exercise.controller;

import exercise.dto.AuthorDTO;
import exercise.dto.AuthorCreateDTO;
import exercise.dto.AuthorUpdateDTO;
import exercise.service.AuthorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.http.HttpStatus;

import java.util.List;

import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/authors")
public class AuthorsController {

    @Autowired
    private AuthorService authorService;

    // BEGIN
    @GetMapping(path = "")
    public ResponseEntity<List<AuthorDTO>> index() {
        List<AuthorDTO> authors = authorService.getAllAuthors();
        return ResponseEntity.ok().header("X-Total-Count", String.valueOf(authors.size())).body(authors);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<AuthorDTO> show(@PathVariable Long id) {
        return ResponseEntity.ok().body(authorService.getAuthor(id));
    }

    @PostMapping(path = "")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<AuthorDTO> create(@Valid @RequestBody AuthorCreateDTO createDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(authorService.createAuthor(createDTO));
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<AuthorDTO> update(@PathVariable Long id, @Valid @RequestBody AuthorUpdateDTO updateDTO) {
        return ResponseEntity.ok().body(authorService.updateAuthor(id, updateDTO));
    }

    @DeleteMapping(path = "/{id}")
    public void delete(@PathVariable Long id) {
        authorService.deleteAuthor(id);
    }
    // END
}
