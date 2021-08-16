package controllers;

import models.Book;
import models.User;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class FeeManagement {
    public static ArrayList<Book> allLostBooks() {
        ArrayList<Book> allLostBooks = new ArrayList<>();
        for (int bookID : DatabaseOperations.getAllLostBookIds()) {
            allLostBooks.add(new Book(DatabaseOperations.getBook(bookID)));
        }
        return allLostBooks;
    }

    public static void lostBook(User user, Book book) {
        if(LibraryManagement.usersCheckedOutBooks(user).contains(book)){
            DatabaseOperations.createLostBook(book, user);
            DatabaseOperations.checkInBook(book, user);
            //TODO add fee, then done
        }
    }

    public static void foundBook(User user, Book book) {
        if(allLostBooks().contains(book)) {
            DatabaseOperations.deleteLostBook(book, user);
            //TODO remove fee, then done
        }
    }

    public static String seeFees(User user) {
        return null;
    }

    public static void updateFees() {

    }
    //see fees
    //pay fees
}
