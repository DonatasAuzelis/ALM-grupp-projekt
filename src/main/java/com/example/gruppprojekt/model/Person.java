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
    private String dateOfbirth;

    public Person(String firstName, String lastName, String dateOfbirth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfbirth = dateOfbirth;
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

    public String getDateOfbirth() {
        return dateOfbirth;
    }

    public void setDateOfbirth(String dateOfbirth) {
        this.dateOfbirth = dateOfbirth;
    }
}
