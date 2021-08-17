package controllers;

import models.Book;
import models.User;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class FeeManagement {

    /**
     * This method retrieves all books from lmsdatabse that are in the lost_books table.
     * @return Returns an ArrayList of Book Objects.
     */
    public static ArrayList<Book> allLostBooks() {
        ArrayList<Book> allLostBooks = new ArrayList<>();
        for (int bookID : DatabaseOperations.getAllLostBookIds()) {
            allLostBooks.add(new Book(DatabaseOperations.getBook(bookID)));
        }
        return allLostBooks;
    }

    /**
     * This method is used to create a lost book into the lmsdatabase.
     * @param user A user Object used to create lost book.
     * @param book A book Object used to create a lost book.
     */
    public static void lostBook(User user, Book book) {
        if(LibraryManagement.usersCheckedOutBooks(user).contains(book)){
            double lostFeeAmount = -20.00;
            DatabaseOperations.createLostBook(book, user);
            DatabaseOperations.checkInBook(book, user);
            if(DatabaseOperations.getCurrentFee(book, user) == 0) {
                DatabaseOperations.createFee(book, user, lostFeeAmount);
            } else {
                DatabaseOperations.updateFee(book, user, lostFeeAmount);
            }
        }
    }

    /**
     * This method is used to day a book has been found and update lmsdatabase.
     * @param user A user Object used to remove a found book from lost_books.
     * @param book A book Object used to remove a found book from lost_books.
     */
    public static void foundBook(User user, Book book) {
        double lostFeeRefund = 20.00;
        if(allLostBooks().contains(book)) {
            DatabaseOperations.deleteLostBook(book, user);
            DatabaseOperations.updateFee(book, user, lostFeeRefund);
        }
    }

    /**
     * This method returns all fees that a user has from the lmsdatabase.
     * @param user A user Object used to check if their primary_key is withing the fees table.
     * @return Returns an ArrayList of String[] which contains the book Object toString, and the fee amount.
     */
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

    /**
     * This method is used to act as a buffer between GUI and DatabaseOperations, updates fees
     * @param book A book Object used to update fees table.
     * @param user A user Object used to update fees table.
     * @param feeAmount A double (price amount) to be updated to the fees table.
     */
    public static void updateFees(Book book, User user, double feeAmount) {
        DatabaseOperations.updateFee(book, user, feeAmount);
    }
}
