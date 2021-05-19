package com.example.gruppprojekt.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.Persistable;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

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

    @CreatedDate
    private LocalDateTime createdDate;
    @LastModifiedDate
    private LocalDateTime lastModifiedDate;



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
