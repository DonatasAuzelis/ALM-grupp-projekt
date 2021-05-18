package com.example.gruppprojekt.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = "Book")
public class Book {

    @Id
    private String id;
    @DBRef
    private Author author;
    private String title;
    private double price;
    private int pageCount;

}
