package org.example.stage1;

import java.util.Objects;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FirstContact {
    static Scanner scanner = new Scanner(System.in);
    private static class Person{
        private String firstName;
        private String lastName;

        private Person(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Person person = (Person) o;
            return Objects.equals(firstName, person.firstName) && Objects.equals(lastName, person.lastName);
        }

    }

    private static class Contact {
        private Person person;
        private String number;

        private Contact(String firstName, String lastName, String number) {
            Pattern pattern = Pattern.compile("^\\+380[5|6|7|9][0|3|6|7|8|9]\\d{7}$");
            Matcher matcher = pattern.matcher(number);
            try {
                if (matcher.find()) {
                    this.person = new Person(firstName, lastName);
                    this.number = number;
                    System.out.println("A record created!");
                    System.out.println("A Phone Book with a single record created!");
                }
                else throw new IllegalArgumentException("Incorrect format of number");
            }catch (IllegalArgumentException e){
                System.err.println(e.getMessage());
            }

        }

        @Override
        public String toString() {
            return String.format("First name: %s, Last name: %s, Number: %s", person.firstName, person.lastName, number);
        }
    }

    public static void main(String[] args) {
        System.out.print("Enter the name of the person:\n> ");
        String name =scanner.nextLine();
        System.out.print("Enter the surname of the person:\n> ");
        String surname =scanner.nextLine();
        System.out.print("Enter the number:\n> ");
        String number =scanner.nextLine();

        FirstContact.Contact firstContact = new Contact(name, surname, number);
        System.out.println(firstContact);
    }
}
