package org.example.stage3;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class AdvancePhoneBook {
    private List<Contact> contacts = new LinkedList<>();

    public void addPerson(String name, String surname, String number, String dateOfBirth, String gender){
        contacts.add(new Person(name, surname, number, dateOfBirth, gender));
    }

    public void addOrganization(String name, String address, String number){
        contacts.add(new Organization(name, address, number));
    }

    public void remove(int index){
        contacts.remove(index);
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public Contact getContactFromIndex(int index){
        return contacts.get(index);
    }

    public int count(){
        return contacts.size();
    }

    public boolean phoneBookEmpty(){
        return contacts.isEmpty();
    }
}
