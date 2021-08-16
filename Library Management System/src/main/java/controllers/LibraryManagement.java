package controllers;
import models.Book;
import models.User;

import javax.naming.SizeLimitExceededException;
import java.util.ArrayList;
public class LibraryManagement {
    private static final int bookLimit = 3;
    /*
   CheckedOut should basically grab the user and the book that is being used and check out that book
    */
    public static ArrayList<Book> usersCheckedOutBooks(User user) {
        ArrayList<Book> foundBooks = new ArrayList<>();
        for(int[] checkOut : DatabaseConnections.getAllCheckedOut()) {
            if(checkOut[2] == user.getPrimaryKey()) {
                Book book = new Book(DatabaseConnections.getBook(checkOut[1]));
                foundBooks.add(book);
            }
        }
        return foundBooks;
    }

    /*
    This method should be able to get the user and the book, and confirm that it is all good and check in the book
     */
    public static boolean checkIn(User user, Book book) {
        ArrayList<Book> checkCheckedOut = usersCheckedOutBooks(user);
        for(Book checkedOutBook : checkCheckedOut) {
            if(checkedOutBook.getPrimaryKey() == book.getPrimaryKey()) {
                return DatabaseConnections.checkInBook(book, user);
            }
        }

        return false;
    }

    public static boolean checkOut(User user, Book book) throws Exception {
        if(usersCheckedOutBooks(user).size() <= bookLimit) {
            ArrayList<int[]> checkedOut = DatabaseConnections.getAllCheckedOut();
            for (int[] books : checkedOut) {
                if (books[1] == book.getPrimaryKey()) {
                    return false;
                }
            }
            if (book == null) {
                throw new NullPointerException();
            }
            return DatabaseConnections.checkOutBook(book, user);
        }else {
            throw new SizeLimitExceededException();
        }
    }

    /*
    This method should be able to search through the database the string given by the user
    if it can't find anything, inform the user
     */
    public static ArrayList<Book> search(String searchBooks) {
        ArrayList<Book> books = new ArrayList<>();
        for (String[] bookInfo : DatabaseConnections.search(searchBooks)) {
            Book currentBook = new Book(bookInfo);
            if(!currentBook.isCheckedOut()) {
                books.add(currentBook);
            }
        }
        return books;
    }

    /*
   This method should simply show the user all the books available at our database
    */
    public static ArrayList<Book> allBooks() {
        ArrayList<Book> books = new ArrayList<>();
        for (String[] bookInfo : DatabaseConnections.getAllBooks()) {
            Book currentBook = new Book(bookInfo);
            if(!currentBook.isCheckedOut()) {
                books.add(currentBook);
            }
        }
        return books;
    }

    //TODO lost books and fees
    /*
    lost book basically reports to the database that book is lost
     */
//    public static void lostBook(User user, Book book) {
//
//    }

//    public static void updateFees() {
//
//    }
    //see fees
    //pay fees
}
