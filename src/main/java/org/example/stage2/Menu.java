package org.example.stage2;

import java.util.List;
import java.util.Scanner;

public class Menu {

    PhoneBook phoneBook = new PhoneBook();
    boolean exit = false;
    static Scanner scanner = new Scanner(System.in);

    public void doAdd(){
        System.out.print("Enter the name: > ");
        String name = scanner.next();
        System.out.print("Enter the surname: > ");
        String surname = scanner.next();
        System.out.print("Enter the number: > ");
        String number = scanner.next();
        phoneBook.add(name, surname, number);
    }

    public void doRemove(){
        if(phoneBook.phoneBookEmpty()){
            System.out.println("No records to remove!");
        } else {
            showAllContacts();
            System.out.print("Select a record: > ");
            phoneBook.remove(scanner.nextInt() - 1);
            System.out.println("The record removed!");
        }
    }

    public void doEdit(){
        showAllContacts();

        System.out.print("Select a record: > ");
        Contact editedContact = phoneBook.getContactFromIndex(scanner.nextInt() - 1);
        System.out.print("Select a field (name, surname, number): > ");
        String field = scanner.next();
        switch (field){
            case "name" -> {
                System.out.format("Enter %s: > ", field);
                editedContact.getPerson().setFirstName(scanner.next());
            }
            case "surname" -> {
                System.out.format("Enter %s: > ", field);
                editedContact.getPerson().setLastName(scanner.next());
            }
            case "number" -> {
                System.out.format("Enter %s: > ", field);
                editedContact.setNumber(scanner.nextLine());
            }
        }
        System.out.println("The record updated!");
    }

    public void showCount(){
        System.out.format("The Phone Book has %d records.\n", phoneBook.count());
    }


    public void showAllContacts(){
        var temporary = phoneBook.getContacts();
        temporary.forEach(i -> System.out.println((temporary.indexOf(i) + 1) + ". " + i));
    }

    public static void execute(){
        Menu menu = new Menu();
        while (!menu.exit){
            System.out.print("Enter action (add, remove, edit, count, list, exit): > ");
            String command = scanner.nextLine();
            switch (command){
                case "add" -> menu.doAdd();
                case "remove" -> menu.doRemove();
                case "edit" -> menu.doEdit();
                case "count" -> menu.showCount();
                case "list" -> menu.showAllContacts();
                case "exit" -> menu.exit = true;
            }
        }
    }


    public static void main(String[] args) {
        execute();
    }
}
