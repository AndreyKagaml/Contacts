package org.example.stage3;

import java.time.LocalDateTime;

public abstract class Contact {
    private String name;
    private String number;
    private LocalDateTime created;
    private LocalDateTime modified;

    public Contact(String name, String number) {
        this.name = name;
        this.number = number;
        this.created = LocalDateTime.now();
        this.modified = LocalDateTime.now();
    }

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setModified(LocalDateTime modified) {
        this.modified = modified;
    }

    @Override
    public String toString() {
        return
                "Number: " + number + '\n' +
                "Time created: " + created + '\n' +
                "Time last modified: " + modified +
                '\n';
    }
}
