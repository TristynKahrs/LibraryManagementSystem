package models;

import controllers.FeeManagement;
import controllers.LibraryManagement;
import java.lang.module.FindException;
import java.util.ArrayList;

public class DisplayBooks {
    private static ArrayList<Book> bookSet = setAllBooks();
    private static final int pageSize = 4;
    private static Book[] page;
    public static int pageNumber = 0;
    public static int amountOfPages = 0;

    public static ArrayList<Book> getBookSet() {
        return bookSet;
    }

    public static ArrayList<Book> setAllBooks() {
        bookSet = LibraryManagement.allBooks();
        return bookSet;
    }

    public static void resetPageNumber() {
        pageNumber = 0;
    }

    public static void searchBooks(String search) throws FindException {
        resetPageNumber();
        bookSet = LibraryManagement.search(search);
        if(bookSet.size() == 0) {
            bookSet = setAllBooks();
            throw new FindException();
        }
    }

    public static void setFeesBookSet(User user) throws FindException{
        resetPageNumber();
        bookSet = FeeManagement.getUsersBooksWithFees(user);
        if(bookSet.size() == 0) {
            setEmptySet();
            throw new FindException();
        }
    }

    public static void setLostBooksSet(User user) throws FindException{
        resetPageNumber();
        bookSet = FeeManagement.usersLostBooks(user);
        if(bookSet.size() == 0) {
            setEmptySet();
            throw new FindException();
        }
    }

    public static void setCheckedOutSet(User user) throws FindException{
        resetPageNumber();
        bookSet = LibraryManagement.usersCheckedOutBooks(user);
        if(bookSet.size() == 0) {
            setEmptySet();
            throw new FindException();
        }
    }

    public static void setEmptySet() {
        resetPageNumber();
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
        amountOfPages = bookArrays.size();
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
