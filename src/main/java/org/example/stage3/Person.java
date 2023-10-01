package org.example.stage3;

import java.lang.annotation.Retention;
import java.lang.reflect.Method;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

public class Person extends Contact{

    private String surname;
    private LocalDate dateOfBirth;
    private Gender gender;

    public Person(String name, String surname, String number, String dateOfBirth, String gender) {
        super(name, number);
        this.surname = surname;
        try {
            this.dateOfBirth = LocalDate.parse(dateOfBirth, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        }catch (DateTimeParseException e){
            System.err.println("Bad birth date!");
        }
        try {
            this.gender = Gender.valueOf(gender.toUpperCase());
        }catch (IllegalArgumentException e){
            System.err.println("Bad gender!");
        }

    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }
    @Override
    public String toString() {
        return  "Name: " + this.getName() + '\n' +
                "Surname: " + surname + '\n' +
                "Date Of Birth: " + dateOfBirth + '\n' +
                "Gender: " + gender +
                '\n' + super.toString();
    }
}