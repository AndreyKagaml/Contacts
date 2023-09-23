package org.example.stage2;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

public class PhoneBook {
    Scanner scanner = new Scanner(System.in);
    private List<Contact> contacts = new LinkedList<>();

    public void add(String name, String surname, String number){
        contacts.add(new Contact(name, surname, number));
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
