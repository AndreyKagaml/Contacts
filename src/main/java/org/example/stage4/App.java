package org.example.stage4;


import org.example.stage3.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

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

    public void doEdit(Contact editedContact){
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

    private List<String> mapToStringName(List<Contact> list){
       return list.stream().map(i -> (list.indexOf(i) + 1) + ". "
                + (i instanceof Person ? (i.getName() + " " + ((Person) i).getSurname()) : i.getName()))
                .toList();
    }

    public void inSearch(){
        boolean back = false;
        System.out.print("Enter search query: > ");
        String query = scanner.next();

        List<String> namesFromSearch = mapToStringName(phoneBook.getContacts()).stream()
                .filter(i -> i.toLowerCase().contains(query.toLowerCase()))
                .toList();

        List<String> stringAllNames = mapToStringName(phoneBook.getContacts());

        List<Contact> contacts = phoneBook.getContacts();

        List<Contact> contactsFromSearch = IntStream.range(0, stringAllNames.size())
                .filter(i -> namesFromSearch.contains(stringAllNames.get(i)))
                .mapToObj(contacts::get).toList();

        mapToStringName(contactsFromSearch).forEach(System.out::println);

        while (!back){
            System.out.print("[search] Enter action ([number], back, again): > ");
            String command = scanner.next();
            try {
                int index = Integer.parseInt(command) - 1;
                Contact temporary = contactsFromSearch.get(index);
                System.out.println(temporary);
                back = true;
                inRecord(temporary);
            }catch (NumberFormatException | IndexOutOfBoundsException e){
                switch (command.toLowerCase()){
                    case "back" -> back = true;
                    case "again" -> {back = true; inSearch();}
                }
            }
        }
    }

    public void inRecord(Contact contact){
        boolean back = false;
        while (!back) {
            System.out.print("[record] Enter action (edit, delete, menu): > ");
            String command = scanner.next();
            switch (command.toLowerCase()) {
                case "edit" -> doEdit(contact);
                case "delete" -> {phoneBook.getContacts().remove(contact); back = true;}
                case "menu" -> back = true;
            }
        }
    }

    public void inList(){
        boolean back = false;
        mapToStringName(phoneBook.getContacts()).forEach(System.out::println);
        while (!back) {
            System.out.print("[list] Enter action ([number], back): > ");
            String command = scanner.next();
            try {
                int index = Integer.parseInt(command) - 1;
                Contact temporary = phoneBook.getContacts().get(index);
                System.out.println(temporary);
                back = true;
                inRecord(temporary);
            }catch (NumberFormatException | IndexOutOfBoundsException e){
                if(command.equalsIgnoreCase("back"))
                 back = true;
                }
        }
    }

    private void init(){
        phoneBook.addPerson("Tom", "Kriss", "+23 (123) 12-23-34-45", "23.10.1998", "Male");
        phoneBook.addPerson("John", "Smith", "+23 (321) 12-12 12 12", "23.10.2003", "Male");
        phoneBook.addOrganization("New Car Shop", "Wall St. 3", "+0 (123) 456-789-9999");
        phoneBook.addOrganization("Decent Pizza Shop", "Wall St. 90", "+23 (321) 12-12 12 12");
    }

    public static void execute(){
        App menu = new App();
        menu.init();
        while (!menu.exit){
            System.out.print("[menu] Enter action (add, list, search, count, exit): > ");
            String command = scanner.nextLine();
            switch (command.toLowerCase()){
                case "add" -> {
                    System.out.print("Enter the type (person, organization): > ");
                    switch (scanner.next().toLowerCase()){
                        case "person" -> menu.doAddPerson();
                        case "organization" -> menu.doAddOrganization();
                    }
                }
                case "list" -> menu.inList();
                case "search" -> menu.inSearch();
                case "count" -> menu.showCount();
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
