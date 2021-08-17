package com.example.librarymanagementsystem.models;

import com.example.librarymanagementsystem.controllers.DatabaseConnections;
import com.example.librarymanagementsystem.controllers.DatabaseOperations;

import java.util.Objects;

public class User {
    private int primaryKey;
    private int numberOfCheckedOutBooks;
    private String fullName;
    public String username;
    private String password;
//    private HashMap fees;
    //private String email;

    public User(String fullName, String username, String password) {
        this.fullName = fullName;
        this.username = username;
        this.password = password;
    }

    public User(String[] userInfo) {
        primaryKey = Integer.parseInt(userInfo[0]);
        setFullName(userInfo[1]);
        setUsername(userInfo[2]);
        setPassword(userInfo[3]);
    }


    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    private void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(int primaryKey) {
        this.primaryKey = primaryKey;
    }

    //    public String getEmail() {
//        return email;
//    }
//
//    public void setEmail(String email) {
//        this.email = email;
//    }

    public boolean passwordMatches(User candidate) {
        String candidatePassword = candidate.getPassword();
        return candidatePassword.equals(getPassword());
    }

    public boolean passwordMatches(String password) {
        return password.equals(getPassword());
    }

    public boolean equals(User candidate) {
        return this.username.equals(candidate.username) && this.fullName.equals(candidate.fullName);
    }

    public int numOfBooksCheckedOut(User user){
        int counter = 0;
        for(int[] numBooks : DatabaseOperations.getAllCheckedOut()){
            if (numBooks[2] == user.getPrimaryKey()) {
                counter++;
            }
        }
        return counter;
    }

    @Override
    public String toString() {
        return getUsername() + " " + getFullName();
    }
}
