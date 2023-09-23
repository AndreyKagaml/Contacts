package org.example.stage2;

import org.example.stage1.FirstContact;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Contact {
    public class Person{
        private String firstName;
        private String lastName;

        private Person(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
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
    }
    private Person person;
    private String number;

    public Contact(String firstName, String lastName, String number) {
        this.person = new Person(firstName, lastName);
            if (numberValidate(number)) {
                this.number = number;
            }
            else System.err.println("Wrong number format!");
        System.out.println("A record created!");
    }

    private boolean numberValidate(String number){
        Pattern pattern = Pattern.compile("^\\+\\d \\(\\d{3}\\) (\\d{3}-){2}[A-Z]{2}[a-z]{2}$");
        // +0 (123) 456-789-ABcd
        Matcher matcher = pattern.matcher(number);
        return matcher.find();
    }


    @Override
    public String toString() {
        return String.format("%s %s, %s", person.firstName, person.lastName, number == null ? "[no number]" : number);
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
