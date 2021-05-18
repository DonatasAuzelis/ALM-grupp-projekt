package com.example.gruppprojekt.model;

/**
 * Created by Ashkan Amiri
 * Date:  2021-05-18
 * Time:  10:24
 * Project: ALM-grupp-projekt
 * Copyright: MIT
 */
public class Person {
    private String firstName;
    private String lastName;
    private String dateOfBirth;

    public Person(String firstName, String lastName, String dateOfBirth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
    }

    public Person() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}
