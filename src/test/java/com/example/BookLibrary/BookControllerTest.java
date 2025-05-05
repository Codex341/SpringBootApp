package com.example.BookLibrary;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BookController.class)
public class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private BookRepository bookRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private Book sampleBook;

    @BeforeEach
    void setup() {
        sampleBook = new Book();
        sampleBook.setId(1L);
        sampleBook.setTitle("The Art of Mocking");
        sampleBook.setAuthor("Vlad Jubea");
    }

    @Test
    void testAddBook_Success() throws Exception {
        Mockito.when(bookRepository.existsByTitleAndAuthor(anyString(), anyString()))
                .thenReturn(false);
        Mockito.when(bookRepository.save(any(Book.class)))
                .thenReturn(sampleBook);

        mockMvc.perform(post("/api/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(sampleBook)))
                .andExpect(status().isCreated())
                .andExpect(content().string("Book added successfully"));
    }

    @Test
    void testAddBook_AlreadyExists() throws Exception {
        Mockito.when(bookRepository.existsByTitleAndAuthor(anyString(), anyString()))
                .thenReturn(true);

        mockMvc.perform(post("/api/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(sampleBook)))
                .andExpect(status().isConflict())
                .andExpect(content().string("Book already exists"));
    }

    @Test
    void testGetAllBooks() throws Exception {
        Mockito.when(bookRepository.findAll())
                .thenReturn(Collections.singletonList(sampleBook));

        mockMvc.perform(get("/api/books"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("The Art of Mocking"))
                .andExpect(jsonPath("$[0].author").value("Vlad Jubea"));
    }

    @Test
    void testGetBooksByAuthor() throws Exception {
        Mockito.when(bookRepository.findBooksByAuthor("Vlad Jubea"))
                .thenReturn(Collections.singletonList(sampleBook));

        mockMvc.perform(get("/api/books/author")
                        .param("author", "Vlad Jubea"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("The Art of Mocking"))
                .andExpect(jsonPath("$[0].author").value("Vlad Jubea"));
    }

    @Test
    void testDeleteBook_Success() throws Exception {
        Mockito.when(bookRepository.existsById(1L)).thenReturn(true);

        mockMvc.perform(delete("/api/books/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Book deleted successfully"));
    }

    @Test
    void testDeleteBook_NotFound() throws Exception {
        Mockito.when(bookRepository.existsById(1L)).thenReturn(false);

        mockMvc.perform(delete("/api/books/1"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Book not found"));
    }
}