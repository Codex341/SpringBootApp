package com.example.BookLibrary;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @PostMapping
    public ResponseEntity<String> addBook(@RequestBody Book book) {
        // Check if a book with the same title and author already exists
        boolean bookExists = bookRepository.existsByTitleAndAuthor(book.getTitle(), book.getAuthor());
        if (bookExists) {
            // Return a message if the book already exists
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Book already exists");
        }

        // Save the book if it doesn't exist
        bookRepository.save(book);
        return ResponseEntity.status(HttpStatus.CREATED).body("Book added successfully");
    }


    @GetMapping
    @ResponseBody
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    // Endpoint to get books by author using a query parameter
    @GetMapping("/author")
    @ResponseBody
    public List<Book> getBooksByAuthor(@RequestParam String author) {
        return bookRepository.findBooksByAuthor(author);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable Long id) {
        if (!bookRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book not found");
        }
        bookRepository.deleteById(id);
        return ResponseEntity.ok("Book deleted successfully");
    }
}

