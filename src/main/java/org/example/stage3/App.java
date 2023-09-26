package org.example.stage3;


import org.example.stage2.Menu;
import org.example.stage2.PhoneBook;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class App {
    AdvancePhoneBook phoneBook = new AdvancePhoneBook();

    boolean exit = false;
    static Scanner scanner = new Scanner(System.in);

    public void doRemove(){
        if(phoneBook.phoneBookEmpty()){
            System.out.println("No records to remove!");
        } else {
            var temporary = phoneBook.getContacts();
            temporary.forEach(i -> System.out.println((temporary.indexOf(i) + 1) + ". "
                    + (i instanceof Person ? (i.getName() + " " + ((Person) i).getSurname()) : i.getName() ) ));
            System.out.print("Select a record: > ");
            phoneBook.remove(scanner.nextInt() - 1);
            System.out.println("The record removed!");
        }
    }

    public void doEdit(){
        var temporary = phoneBook.getContacts();
        temporary.forEach(i -> System.out.println((temporary.indexOf(i) + 1) + ". "
                + (i instanceof Person ? (i.getName() + " " + ((Person) i).getSurname()) : i.getName() ) ));

        System.out.print("Select a record: > ");
        Contact editedContact = phoneBook.getContactFromIndex(scanner.nextInt() - 1);
        if(editedContact instanceof Person) {
            System.out.print("Select a field (name, surname, number, birth, gender): > ");
            String field = scanner.next();
            switch (field.toLowerCase()) {
                case "name" -> {
                    System.out.format("Enter %s: > ", field);
                    editedContact.setName(scanner.next());
                }
                case "surname" -> {
                    System.out.format("Enter %s: > ", field);
                    ((Person) editedContact).setSurname(scanner.next());
                }
                case "number" -> {
                    System.out.format("Enter %s: > ", field);
                    editedContact.setNumber(scanner.nextLine());
                }
                case "birth" -> {
                    System.out.format("Enter %s: > ", field);
                    ((Person) editedContact).setDateOfBirth(LocalDate.parse(scanner.nextLine(), DateTimeFormatter.ofPattern("dd.MM.yyyy")));
                }
                case "gender" -> {
                    System.out.format("Enter %s: > ", field);
                    ((Person) editedContact).setGender(Gender.valueOf(scanner.nextLine()));
                }
            }
            editedContact.setModified(LocalDateTime.now());
            System.out.println("The record updated!");
        }
        else {
            System.out.print("Select a field (address, number): > ");
            String field = scanner.next();
            switch (field.toLowerCase()) {
                case "address" -> {
                    System.out.format("Enter %s: > ", field);
                    ((Organization) editedContact).setAddress(scanner.next());
                }
                case "number" -> {
                    System.out.format("Enter %s: > ", field);
                    editedContact.setNumber(scanner.nextLine());
                }
            }
            editedContact.setModified(LocalDateTime.now());
            System.out.println("The record updated!");
        }
    }

    public void showCount(){
        System.out.format("The Phone Book has %d records.\n", phoneBook.count());
    }


    public void showAllContacts(){
        var temporary = phoneBook.getContacts();
        if(!phoneBook.phoneBookEmpty()) {
            temporary.forEach(i -> System.out.println((temporary.indexOf(i) + 1) + ". "
                    + (i instanceof Person ? (i.getName() + " " + ((Person) i).getSurname()) : i.getName())));
            System.out.print("Select a record: > ");
            System.out.println(phoneBook.getContactFromIndex(scanner.nextInt() - 1));
        }
    }

    public static void execute(){
        App menu = new App();
        while (!menu.exit){
            System.out.print("Enter action (add, remove, edit, count, info, exit): > ");
            String command = scanner.nextLine();
            switch (command.toLowerCase()){
                case "add" -> {
                    System.out.print("Enter the type (person, organization): > ");
                    switch (scanner.next().toLowerCase()){
                        case "person" -> menu.doAddPerson();
                        case "organization" -> menu.doAddOrganization();
                    }
                }
                case "remove" -> menu.doRemove();
                case "edit" -> menu.doEdit();
                case "count" -> menu.showCount();
                case "info" -> menu.showAllContacts();
                case "exit" -> menu.exit = true;
            }
        }
    }

    private void doAddOrganization() {
        System.out.print("Enter the organization name: > ");
        String name = scanner.next();
        System.out.print("Enter the address: > ");
        String address = scanner.next();
        System.out.print("Enter the number: > ");
        String number = scanner.next();
        phoneBook.addOrganization(name, address, number);
    }

    private void doAddPerson() {
        System.out.print("Enter the name: > ");
        String name = scanner.next();
        System.out.print("Enter the surname: > ");
        String surname = scanner.next();
        System.out.print("Enter the birthday: > ");
        String birthday = scanner.next();
        System.out.print("Enter the gender: > ");
        String gender = scanner.next();
        System.out.print("Enter the number: > ");
        String number = scanner.next();
        phoneBook.addPerson(name, surname, number, birthday, gender);
    }


    public static void main(String[] args) {
        execute();
    }
}
