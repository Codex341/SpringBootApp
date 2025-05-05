package com.example.BookLibrary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    // You get save(), findById(), deleteById(), etc. for free
    boolean existsByTitleAndAuthor(String title, String author);

    // Custom SELECT query using native SQL
    @Query(value = "SELECT * FROM book WHERE author = :author", nativeQuery = true)
    List<Book> findBooksByAuthor(@Param("author") String author);

    // You can also add custom queries for updating, deleting, etc.
}
