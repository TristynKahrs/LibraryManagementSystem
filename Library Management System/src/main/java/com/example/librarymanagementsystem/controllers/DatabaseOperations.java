package com.example.librarymanagementsystem.controllers;

import com.example.librarymanagementsystem.models.Book;
import com.example.librarymanagementsystem.models.User;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class DatabaseOperations {
    /**
     * This method is used to insert newly created user accounts into the lmsdatabase.
     *
     * @param _username String _username to set username
     * @param _password String _password to set password
     * @param _fullName String _fullname to set fullname
     */
    public static void createUser(String _fullName, String _username, String _password) {
        String insertUser = "INSERT INTO users(full_name, username, password) Values(?, ?, aes_encrypt(?, 'pass1234!'))";
        try {
            Connection con = DatabaseConnections.SQLConnection();
            PreparedStatement pst = con.prepareStatement(insertUser);
            pst.setString(1, _fullName);
            pst.setString(2, _username);
            pst.setString(3, _password);
            pst.executeUpdate();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * This method is used to check the credentials of login information to see if they are in the database.
     *
     * @param field String field requires either username or password or full_name to retrieve all information about persons
     * @return Returns true if a match is found within database, otherwise false
     */
    public static ArrayList<String[]> getUsers(String field) {
        String checkUser = "SELECT * FROM lmsdatabase.users WHERE full_name=(?);";
        ArrayList<String[]> users = new ArrayList<>();
        String[] userInfo = new String[4];

        try {
            Connection con = DatabaseConnections.SQLConnection();
            PreparedStatement pst = con.prepareStatement(checkUser);
            pst.setString(1, field);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                userInfo[0] = rs.getString(1);
                userInfo[1] = rs.getString(2);
                userInfo[2] = rs.getString(3);
                userInfo[3] = rs.getString(4);

                users.add(userInfo);
            }
            con.close();
        } catch (SQLException SQLe) {
            SQLe.printStackTrace();
        }
        return users;
    }

    /**
     * This method retrieves a User from the lmsdatabase.
     * @param username String username used to retrive user
     * @return Returns a String[] containing Users id, fullname, username, and password
     */
    public static String[] getUser(String username) {
        String checkUser = "SELECT *, cast(aes_decrypt(password, 'pass1234!') as char) FROM lmsdatabase.users WHERE username=(?);";
        String[] userInfo = new String[4];

        try {
            Connection con = DatabaseConnections.SQLConnection();
            PreparedStatement pst = con.prepareStatement(checkUser);
            pst.setString(1, username);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                userInfo[0] = rs.getString(1);
                userInfo[1] = rs.getString(2);
                userInfo[2] = rs.getString(3);
                userInfo[3] = rs.getString(5);
            }
            con.close();
        } catch (SQLException SQLe) {
            SQLe.printStackTrace();
        }
        return userInfo;
    }

    /**
     * This method is used to update a users password in the lmsdatabase.
     * @param username String username used to update Users password in database
     * @param newPassword String newPassword sets newPassword into database.
     */
    public static void changeUserPassword(String username, String newPassword) {
        String newPassQuery = "UPDATE users SET password=aes_encrypt(?, 'pass1234!') WHERE username=(?);";

        try {
            Connection con = DatabaseConnections.SQLConnection();
            PreparedStatement pst = con.prepareStatement(newPassQuery);
            pst.setString(1, newPassword);
            pst.setString(2, username);
            pst.executeUpdate();
            con.close();
        } catch (SQLException SQLe) {
            SQLe.printStackTrace();
        }
    }

    /**
     * This method is used to update a users fullname n the lmsdatabase.
     * @param newFullName String newFull name sets Users Full-Name in the database.
     * @param username String username gets specific user to update their full name.
     */
    public static void changeFullName(String newFullName, String username) {
        String newUserName = "UPDATE users SET full_name=(?) WHERE username=(?)";
        try {
            Connection con = DatabaseConnections.SQLConnection();
            PreparedStatement pst = con.prepareStatement(newUserName);
            pst.setString(1, newFullName);
            pst.setString(2, username);
            pst.executeUpdate();
            con.close();
        } catch (SQLException SQLe) {
            SQLe.printStackTrace();
        }
    }

    /**
     * This method is used to return a result set of found (or not found) information from the database based on the
     * search parameter.
     *
     * @param searchParameter String searchParameter allows for search on book title, author and genre;
     * @return Returns a ResultSet from pulled information form the Database.
     */
    public static ArrayList<String[]> search(String searchParameter) {
        String search = "SELECT * FROM books WHERE title LIKE ? OR author LIKE ?;";
        ArrayList<String[]> books = new ArrayList<>();

        try {
            Connection con = DatabaseConnections.SQLConnection();
            PreparedStatement pst = con.prepareStatement(search);
            pst.setString(1, "%" + searchParameter + "%");
            pst.setString(2, "%" + searchParameter + "%");
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                String[] bookInfo = new String[3];
                bookInfo[0] = rs.getString(1);
                bookInfo[1] = rs.getString(2);
                bookInfo[2] = rs.getString(3);
                books.add(bookInfo);
            }
            con.close();
        } catch (SQLException SQLe) {
            SQLe.printStackTrace();
        }
        return books;
    }

    /**
     * This method gets all books in the lmsdatabase.
     * @return Returns a ArrayList of String[] with each String[] holing the Books id, title, and author.
     */
    public static ArrayList<String[]> getAllBooks() {
        String checkUser = "SELECT * FROM lmsdatabase.books;";
        ArrayList<String[]> books = new ArrayList<>();


        try {
            Connection con = DatabaseConnections.SQLConnection();
            PreparedStatement pst = con.prepareStatement(checkUser);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                String[] bookInfo = new String[3];
                bookInfo[0] = rs.getString(1);
                bookInfo[1] = rs.getString(2);
                bookInfo[2] = rs.getString(3);
                books.add(bookInfo);
            }
            con.close();
        } catch (SQLException SQLe) {
            SQLe.printStackTrace();
        }
        return books;
    }

    /**
     * This method retrieves a singular book from lmsdatabase.
     * @param bookId int bookId is used to retrieve the book from the databse.
     * @return Returns a String[] containing the singular books id, title, and author.
     */
    public static String[] getBook(int bookId) {
        String checkUser = "SELECT * FROM lmsdatabase.books WHERE book_id=?;";
        String[] foundBook = new String[3];

        try {
            Connection con = DatabaseConnections.SQLConnection();
            PreparedStatement pst = con.prepareStatement(checkUser);
            pst.setInt(1, bookId);
            ResultSet rs = pst.executeQuery();

            if(rs.next()) {
                foundBook[0] = rs.getString(1);
                foundBook[1] = rs.getString(2);
                foundBook[2] = rs.getString(3);
            }
            con.close();

        } catch (SQLException SQLe) {
            SQLe.printStackTrace();
        }
        return foundBook;
    }

    /**
     * This method returns all books currently checked out of the lmsdatabase.
     * @return Returns a ArrayList of int[] which contains a check_out_id, book_id, and user_id.
     */
    public static ArrayList<int[]> getAllCheckedOut() {
        String checkOut = "SELECT * FROM lmsdatabase.checkout;";
        ArrayList<int[]> checkedOut = new ArrayList<>();

        try {
            Connection con = DatabaseConnections.SQLConnection();
            PreparedStatement pst = con.prepareStatement(checkOut);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                int[] checkedOutBook = new int[3];
                checkedOutBook[0] = rs.getInt(1);
                checkedOutBook[1] = rs.getInt(2);
                checkedOutBook[2] = rs.getInt(3);
                checkedOut.add(checkedOutBook);
            }
            con.close();
        } catch (SQLException SQLe) {
            SQLe.printStackTrace();
        }

        return checkedOut;
    }

    /**
     * This method will insert a book an duser into the checkout table in lmsdatabase.
     * @param book A book Object used to store information into database.
     * @param user A user Object used to store information into datbase.
     * @return Returns true if successfully added into lmsdatabase, false if not successful.
     */
    public static boolean checkOutBook(Book book, User user){
        String checkedOutBook = "INSERT INTO checkout (book_id, user_id, checkOutDate) Values(?,?,?)";
        try{
            Connection con = DatabaseConnections.SQLConnection();
            PreparedStatement pst = con.prepareStatement(checkedOutBook);
            pst.setInt(1, book.getPrimaryKey());
            pst.setInt(2, user.getPrimaryKey());
            pst.setDate(3, Date.valueOf(LocalDate.now()));
            pst.executeUpdate();
            con.close();
            return true;
        }catch (SQLException SQLe){
            SQLe.printStackTrace();
        }
        return false;
    }

    /**
     * This method gets a books checkOutDate from lmsdatabase.
     * @param book A book Object used to retrieve information from database.
     * @param user A user Object used to retrieve information from database.
     * @return Returns a Date retrieved from the checkout table in lmsdatabase.
     */
    public static Date getCheckOutDate(Book book, User user) {
        String getDate = "SELECT checkOutDate FROM checkout WHERE book_id=? AND user_id=?";
        Date checkOutDate = null;
        try {
            Connection con = DatabaseConnections.SQLConnection();
            PreparedStatement pst = con.prepareStatement(getDate);
            pst.setInt(1, book.getPrimaryKey());
            pst.setInt(2, user.getPrimaryKey());
            ResultSet rs = pst.executeQuery();

            if(rs.next()) {
                checkOutDate = rs.getDate(1);
                return checkOutDate;
            }
        } catch(SQLException SQLe) {
            SQLe.printStackTrace();
        }

        return checkOutDate;
    }

    /**
     * This method removes a book and user from checkout table if they checked in a book.
     * @param book A book Object used to delete information from database.
     * @param user A user Object used to delete information from database.
     * @return Returns true if successfully deleted from lmsdatabase, false if not successful.
     */
    public static boolean checkInBook(Book book, User user){
        String checkInBook = "DELETE FROM checkout WHERE book_id=(?) AND user_id=(?)";
        try{
            Connection con = DatabaseConnections.SQLConnection();
            PreparedStatement pst = con.prepareStatement(checkInBook);
            pst.setInt(1, book.getPrimaryKey());
            pst.setInt(2, user.getPrimaryKey());
            pst.executeUpdate();
            con.close();
            return true;
        }catch (SQLException SQLe){
            SQLe.printStackTrace();
        }
        return false;
    }

    /**
     * This method inserts a book and user into lost_books table in lmsdatabase if called.
     * @param book A book Object used to insert into lost_books table.
     * @param user A user Object used to insert into lost_books table.
     * @return Returns true if successfully inserted into lmsdatabase, false if not successful.
     */
    public static boolean createLostBook(Book book, User user) {
        String createLostBook = "INSERT INTO lost_books(book_id, user_id) VALEUS(?, ?)";

        try {
            Connection con = DatabaseConnections.SQLConnection();
            PreparedStatement pst = con.prepareStatement(createLostBook);
            pst.setInt(1, book.getPrimaryKey());
            pst.setInt(2, user.getPrimaryKey());
            pst.executeUpdate();
            con.close();
            return true;
        } catch(SQLException SQLe) {
            SQLe.printStackTrace();
        }

        return false;
    }

    /**
     * This method removes a lost book from lost_books table in lmsdatabase.
     * @param book A book Object used to delete from lost_books table.
     * @param user A user Obkect used to delete from lost_books table.
     * @return Returns true if successfully deleted from lmsdatabase, false if not successful.
     */
    public static boolean deleteLostBook(Book book, User user) {
        String foundLostBook = "DELETE FROM lost_books WHERE book_id=(?) AND user_id=(?)";
        try {
            Connection con = DatabaseConnections.SQLConnection();
            PreparedStatement pst = con.prepareStatement(foundLostBook);
            pst.setInt(1, book.getPrimaryKey());
            pst.setInt(2, user.getPrimaryKey());
            pst.executeUpdate();
            con.close();
            return true;
        } catch(SQLException SQLe) {
            SQLe.printStackTrace();
        }

        return false;
    }

    /**
     * This methods returns all lost books from lost_books table.
     * @return Returns an ArrayList of Integers which contains book_id.
     */
    public static ArrayList<Integer> getAllLostBookIds() {
        String getAllLostBooks = "SELECT book_id FROM lost_books";
        ArrayList<Integer> bookIds = new ArrayList<>();
        try {
            Connection con = DatabaseConnections.SQLConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(getAllLostBooks);

            while(rs.next()) {
                bookIds.add(rs.getInt(1));
            }

            return bookIds;
        } catch (SQLException SQLe) {
            SQLe.printStackTrace();
        }

        return bookIds;
    }

    /**
     * This method will insert a fee into fees table in lmsdatabase.
     * @param book A book Object to insert information into fees table.
     * @param user A user Object to insert information into fees table.
     * @param fee A double (Price amount) to insert into fees table.
     * @return Returns true if successfully inserted into lmsdatabase, false if not successful.
     */
    public static boolean createFee(Book book, User user, double fee) {
        String insertFee = "INSERT INTO fees(book_id, user_id, fee_amount) VALUES(?, ?, ?)";
        try {
            Connection con = DatabaseConnections.SQLConnection();
            PreparedStatement pst = con.prepareStatement(insertFee);
            pst.setInt(1, book.getPrimaryKey());
            pst.setInt(2, user.getPrimaryKey());
            pst.setDouble(3, fee);
            return true;
        } catch(SQLException SQLe) {
            SQLe.printStackTrace();
        }
        return false;
    }

    /**
     * This method checks if a user has any fees form the fees table in lmsdatabase.
     * @param user A user Object used to retrieve information from fees table.
     * @return Returns an ArrayList of double[] which contains the book_id, and fee_amount.
     */
    public static ArrayList<double[]> checkForFees(User user) {
        String getFees = "SELECT * FROM fees WHERE user_id=(?)";
        ArrayList<double[]> bookFees = new ArrayList<>();
        double[] bookFeeInfo = null;
        try {
            Connection con = DatabaseConnections.SQLConnection();
            PreparedStatement pst = con.prepareStatement(getFees);
            pst.setInt(1, user.getPrimaryKey());
            ResultSet rs = pst.executeQuery();

            while(rs.next()) {
                bookFeeInfo = new double[2];
                bookFeeInfo[0] = rs.getInt(2);
                bookFeeInfo[1] = rs.getDouble(4);
                bookFees.add(bookFeeInfo);
            }

            return bookFees;
        } catch(SQLException SQLe) {
            SQLe.printStackTrace();
        }

        return bookFees;
    }

    /**
     * This method retrieves the current total fee for a book that a user has checked out from lmsdatabase.
     * @param book A book Object used to retrieve information from fees table.
     * @param user A user Object used to retrieve information from fees table.
     * @return Returns a double, which is the current fee for that book.
     */
    public static double getCurrentFee(Book book, User user) {
        String getFeeAmount = "SELECT fee_amount FROM fees WHERE book_id=(?) AND user_id=(?)";
        double currentFee = 0;
        try {
            Connection con = DatabaseConnections.SQLConnection();
            PreparedStatement pst = con.prepareStatement(getFeeAmount);
            pst.setInt(1, book.getPrimaryKey());
            pst.setInt(2, user.getPrimaryKey());
            ResultSet rs = pst.executeQuery();

            if(rs.next()) {
                currentFee = rs.getDouble(1);
                return currentFee;
            }
        } catch(SQLException SQLe) {
            SQLe.printStackTrace();
        }

        return currentFee;
    }

    /**
     * This method is used to update a users fee for a book either, paying off or giving them more fees.
     * @param book A book Object used to update fees table.
     * @param user A user Object used to update fees table.
     * @param payment A double which is used to either subtract or add a fee into fees table.
     * @return Returns true if successfully updated into lmsdatabase, false if not successful.
     */
    public static boolean updateFee(Book book, User user, double payment) {
        String updateFee = "UPDATE fees SET fee_amount=? WHERE book_id=? AND user_id=?";
        try {
            Connection con = DatabaseConnections.SQLConnection();
            PreparedStatement pst = con.prepareStatement(updateFee);
            pst.setDouble(1, getCurrentFee(book, user) + payment);
            pst.setInt(2, book.getPrimaryKey());
            pst.setInt(3, user.getPrimaryKey());
            pst.executeUpdate();

            return true;
        } catch(SQLException SQLe) {
            SQLe.printStackTrace();
        }

        return false;
    }
}
