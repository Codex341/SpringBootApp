package com.example.BookLibrary;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Auto-generated ID (INTEGER AUTO_INCREMENT in MySQL)

    private String title;
    private String author;
}
