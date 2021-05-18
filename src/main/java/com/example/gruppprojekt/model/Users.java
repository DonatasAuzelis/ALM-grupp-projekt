package com.example.gruppprojekt.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

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
public class Users extends Person{

    @Id
    private String id;
    private Long personalNumber;
}
