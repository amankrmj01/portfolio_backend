package com.amankrmj.portfolio_backend.model.contact.details;

import java.io.Serializable;

public class ContactModel implements Serializable {
    private String name;
    private String countryCode;
    private String phoneNumber;
    private String email;
    private String message;
    private String date;
    private String time;


    public ContactModel() {
    }

    public ContactModel(String name, String countryCode, String phoneNumber, String email, String message, String date, String time) {
        this.name = name;
        this.countryCode = countryCode;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.message = message;
        this.date = date;
        this.time = time;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
