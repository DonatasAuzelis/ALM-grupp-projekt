package com.example.gruppprojekt.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.Persistable;
import org.springframework.data.mongodb.core.aggregation.DateOperators;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.annotation.PostConstruct;
import java.lang.reflect.Field;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Stream;

/**
 * Created by Ashkan Amiri
 * Date:  2021-05-18
 * Time:  10:27
 * Project: ALM-grupp-projekt
 * Copyright: MIT
 */
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = "Users")
public class Users extends Person  {

    @Id
    private String id;
    @Indexed(unique = true)
    private Long personalNumber;
    private String password;
    @Indexed(unique = true)
    private String email;
    @DBRef
    private List<Book> books;

    @PostConstruct
    public void setTimeZone() {
        TimeZone.setDefault(TimeZone.getTimeZone("Europe/Stockholm"));
    }

    @CreatedDate
    private String createdDate;
    @LastModifiedDate
    private String lastModifiedDate;

    public Users(String firstName, String lastName, String dateOfBirth, String id, Long personalNumber,
                 String password, String email, List<Book> books, String createdDate, String lastModifiedDate) {
        super(firstName, lastName, dateOfBirth);
        this.id = id;
        this.personalNumber = personalNumber;
        this.password = password;
        this.email = email;
        this.books = books;
        this.createdDate = createdDate;
        this.lastModifiedDate = lastModifiedDate;
    }

/* ********************************************************************************************************
    public boolean checkNull() throws IllegalAccessException {
        for (Field f : getClass().getDeclaredFields())
            if (f.get(this) != null)
                return false;
        return true;
    }

    public boolean isUserEmpty1() {
        boolean isEmpty;
        isEmpty  = Stream.of(
                this.getFirstName(), this.getLastName(), this.getPersonalNumber())
                .anyMatch(Objects::nonNull);

        return isEmpty;
    }

    public boolean isUserEmpty() {
        long isEmpty;
        isEmpty = Stream.of(id,
                this.getFirstName(), this.getLastName())
                .filter(Objects::nonNull).count();

        return isEmpty > 0;
    }
    *********************************************************************************************/

}
