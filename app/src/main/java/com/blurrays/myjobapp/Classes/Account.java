package com.blurrays.myjobapp.Classes;

import java.time.LocalDate;

public class Account {

    public Account( String firstname, String lastname, String phone, UserType type) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.phone = phone;
        this.type = type;
    }


    public Account() {
    }

    private String firstname;
    private String lastname;
    private String phone;
    private UserType type;


    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    public UserType getType() {
        return type;
    }

    public void setType(UserType type) {
        this.type = type;
    }

}
