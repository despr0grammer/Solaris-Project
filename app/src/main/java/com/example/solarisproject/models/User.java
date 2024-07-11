package com.example.solarisproject.models;

public class User {
    private int id;
    private String name;
    private String lastName;
    private int age;
    private String email;
    private String phone;
    private String password;
    private String role;

    public User(int id, String name, String lastName, int age, String email, String phone, String password, String role) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.age = age;
        this.email = email;
        this.phone = phone;
        this.password = password;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }
}
