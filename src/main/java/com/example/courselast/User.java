package com.example.courselast;

public class User {
    private String email;
    private String password;
    private String name;
    private String surname;
    private String country;

    public User(String email, String password, String name, String surname, String country) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.surname = surname;
        this.country = country;
    }

    public User() {}

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}