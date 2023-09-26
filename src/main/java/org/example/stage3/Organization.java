package org.example.stage3;

public class Organization extends Contact{
    private String address;

    public Organization(String name, String address, String number) {
        super(name, number);
        this.address = address;
    }

    @Override
    public String toString() {
        return  "Organization name: " + this.getName() + '\n' +
                "Address: " + address + '\n' +
                 super.toString();
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
