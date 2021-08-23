package controllers;

import models.Book;
import models.User;

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
     * This method gets all of the users Lost books
     * @param user A user Object used to get users lost books
     * @return Returns an ArrayList of Books
     */
    public static ArrayList<Book> usersLostBooks(User user) {
        ArrayList<Book> allLostBooks = new ArrayList<>();
        for(Integer book_id : DatabaseOperations.getAllUsersLostBooks(user)) {
            Book book = new Book(DatabaseOperations.getBook(book_id));
            allLostBooks.add(book);
        }

        return allLostBooks;
    }

    /**
     * This method is used to create a lost book into the lmsdatabase.
     * @param user A user Object used to create lost book.
     * @param book A book Object used to create a lost book.
     */
    public static void lostBook(User user, Book book) {
        for(Book checkedOutBook : LibraryManagement.usersCheckedOutBooks(user)) {
            if(checkedOutBook.getTitle().equals(book.getTitle())){
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

    /**
     * This method gets an Arraylist of books that a user has a fee on.
     * @param user A user Object used to retrieve all of their books that have fees
     * @return Returns a ArrayList of book Objects
     */
    public static ArrayList<Book> getUsersBooksWithFees(User  user) {
        ArrayList<Book> books = new ArrayList<>();
        ArrayList<double[]> getBookIds = DatabaseOperations.checkForFees(user);
        for(double[] bookFee :getBookIds) {
            Book book = new Book(DatabaseOperations.getBook((int)bookFee[0]));
            books.add(book);
        }

        return books;
    }

    /**
     * This method gets the fee for a book
     * @param book A book Object to retrieve the fee
     * @return Returns fee amount
     */
    public static double getUserFee(Book book) {
        double feeForBook = DatabaseOperations.getBookFee(book);
        return feeForBook;
    }
}
