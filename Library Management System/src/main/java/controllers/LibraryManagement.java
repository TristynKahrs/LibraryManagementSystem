package controllers;
import models.Book;
import models.User;

import javax.naming.SizeLimitExceededException;
import java.util.ArrayList;
public class LibraryManagement {
    private static final int bookLimit = 3;

    /**
     * This method gets all users checked out books
     * @param user A user Object used to retrieve all of their checked out books.
     * @return Returns an ArrayList of Book Objects that a user has checked out.
     */
    public static ArrayList<Book> usersCheckedOutBooks(User user) {
        ArrayList<Book> foundBooks = new ArrayList<>();
        for(int[] checkOut : DatabaseOperations.getAllCheckedOut()) {
            if(checkOut[2] == user.getPrimaryKey()) {
                Book book = new Book(DatabaseOperations.getBook(checkOut[1]));
                foundBooks.add(book);
            }
        }
        return foundBooks;
    }

    /**
     * This method lets a user check out a book from the lmsdatabse.
     * @param user A user Object used to check in a book from checkout table.
     * @param book A book Object used to check in a book from checkout table.
     * @return Returns true if checkin was successful, false if not successful.
     */
    public static boolean checkIn(User user, Book book) {
        ArrayList<Book> checkCheckedOut = usersCheckedOutBooks(user);
        for(Book checkedOutBook : checkCheckedOut) {
            if(checkedOutBook.getPrimaryKey() == book.getPrimaryKey()) {
                return DatabaseOperations.checkInBook(book, user);
            }
        }

        return false;
    }

    /**
     * This method lets a user check out a book from the lmsdatabase.
     * @param user A user Object used to check out a book into checkout table.
     * @param book A book Object used to check out a book into checkout table.
     * @return Returns true if checkout was successful, false if not successful.
     * @throws Exception If the user has either reached all of their checked out books limit,
     * or a book is null an Exception will be thrown.
     */
    public static boolean checkOut(User user, Book book) throws Exception {
        if(usersCheckedOutBooks(user).size() <= bookLimit) {
            ArrayList<int[]> checkedOut = DatabaseOperations.getAllCheckedOut();
            for (int[] books : checkedOut) {
                if (books[1] == book.getPrimaryKey()) {
                    return false;
                }
            }
            if (book == null) {
                throw new NullPointerException();
            }
            return DatabaseOperations.checkOutBook(book, user);
        }else {
            throw new SizeLimitExceededException();
        }
    }

    /*
    This method should be able to search through the database the string given by the user
    if it can't find anything, inform the user
     */

    /**
     * This method search's the database and checks to see if a result is checked out, if not then it will add it to the found books.
     * @param searchBooks String searchBooks can be any string, and it will check book titles and author names.
     * @return Returns ArrayList of Books where the search was able to find a result.
     */
    public static ArrayList<Book> search(String searchBooks) {
        ArrayList<Book> books = new ArrayList<>();
        for (String[] bookInfo : DatabaseOperations.search(searchBooks)) {
            Book currentBook = new Book(bookInfo);
            if(!currentBook.isCheckedOut()) {
                if(!currentBook.isLost()) {
                    books.add(currentBook);
                }
            }
        }
        return books;
    }

    /*
   This method should simply show the user all the books available at our database
    */

    /**
     * This method gets all books from lmsdatabase.
     * @return Returns an ArrayList of Book Objects that are not checked out.
     */
    public static ArrayList<Book> allBooks() {
        ArrayList<Book> books = new ArrayList<>();
        for (String[] bookInfo : DatabaseOperations.getAllBooks()) {
            Book currentBook = new Book(bookInfo);
            if(!currentBook.isCheckedOut()) {
                if(!currentBook.isLost()) {
                    books.add(currentBook);
                }
            }
        }
        return books;
    }

    /**
     * This method checks fees for a user
     * @param username String username is used to check the fees of a user based on there username.
     * @return Returns an ArrayList of double[] which contain the book_id and the fee amount of that book.
     */
    public static ArrayList<double[]> checkFees(String username) {
       return DatabaseOperations.checkForFees(new User(DatabaseOperations.getUser(username)));
    }

    /**
     * This gets a singular book from the lmsdatasbase.
     * @param bookID int bookId used to retrieve a book from books table.
     * @return Returns a Book Object retrieved from the database.
     */
    public static Book getBook(int bookID) {
        return new Book(DatabaseOperations.getBook(bookID));
    }
}
