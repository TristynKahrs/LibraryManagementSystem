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
        double lostFeeAmount = 20.00;
        if(LibraryManagement.usersCheckedOutBooks(user).contains(book)){
            DatabaseOperations.createLostBook(book, user);
            DatabaseOperations.checkInBook(book, user);
            //TODO add fee, then done
            DatabaseOperations.createFee(book, user, lostFeeAmount);
        }
    }

    public static void foundBook(User user, Book book) {
        if(allLostBooks().contains(book)) {
            DatabaseOperations.deleteLostBook(book, user);
            //TODO remove fee, then done
        }
    }

    public static ArrayList<String[]> seeFees(User user) {
        ArrayList<String[]> lateBooks = new ArrayList<>();
        int bookNameCount = 0;
        String[] feeInfo = null;
        Book lateBook = null;
        for(double[] bookInfo: DatabaseOperations.checkForFees(user)) {
            feeInfo = new String[2];

            lateBook = new Book(DatabaseOperations.getBook((int)bookInfo[bookNameCount]));

            feeInfo[0] = lateBook.toString();
            feeInfo[1] = String.valueOf(bookInfo[1]);
            lateBooks.add(feeInfo);
            bookNameCount++;
        }

        return lateBooks;
    }

    public static void updateFees() {

    }
}
