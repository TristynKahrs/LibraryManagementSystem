package models;

import controllers.FeeManagement;
import controllers.LibraryManagement;
import models.Book;

import java.lang.module.FindException;
import java.util.ArrayList;
import java.util.Arrays;

public class DisplayBooks {
    private static ArrayList<Book> bookSet = setAllBooks();

    private static final int pageSize = 4;
    private static int pageNumber = 0;
    private static Book[] page;

    public static ArrayList<Book> setAllBooks() {
        pageNumber = 0;
        bookSet = LibraryManagement.allBooks();
        return bookSet;
    }

    public static void searchBooks(String search) throws FindException {
        pageNumber = 0;
        bookSet = LibraryManagement.search(search);
        if(bookSet.size() == 0) {
            bookSet = setAllBooks();
            throw new FindException();
        }
    }

    //TODO different book pages here
    public static void setFeesBookSet(User user) throws FindException{
        bookSet = FeeManagement.getUsersBooksWithFees(user);
        if(bookSet.size() == 0) {
            setEmptySet();
            throw new FindException();
        }
        //TODO get all books that have fees (AL); then go get the fee amounts
    }

    public static void setLostBooksSet(User user) {
        bookSet = FeeManagement.usersLostBooks(user);
        if(bookSet.size() == 0) {
            setEmptySet();
            throw new FindException();
        }
    }

    public static void setCheckedOutSet(User user) {
        bookSet = LibraryManagement.usersCheckedOutBooks(user);
        if(bookSet.size() == 0) {
            setEmptySet();
            throw new FindException();
        }
    }

    public static void setEmptySet() {
        bookSet.clear();
        for (int i = 0; i < 4; i++) {
            bookSet.add(null);
        }
    }

    public static Book[] page() {
        page = splitBooks().get(pageNumber);
        return page;
    }

    private static ArrayList<Book[]> splitBooks() {
        ArrayList<Book[]> bookArrays = new ArrayList<>();
        for (int i = 0; i < bookSet.size(); i += pageSize) {
            Book[] bookArray = new Book[pageSize];
            for (int j = i; j < (i + pageSize); j++) {
                try {
                    bookArray[j % pageSize] = bookSet.get(j);
                } catch (Exception ignored) {}
            }

            bookArrays.add(bookArray);
        }
        return bookArrays;
    }


    public static Book getSelectedBook(int selection) {
        return page[selection];
    }

    public static int getPageSize() {
        return pageSize;
    }

    public static void prev() {
        if (pageNumber == 0) {
            pageNumber = splitBooks().size();
        }
        pageNumber--;
    }

    public static void next() {
        if (pageNumber == splitBooks().size() - 1) {
            pageNumber = -1;
        }
        pageNumber++;
    }
}
