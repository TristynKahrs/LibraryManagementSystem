package controllers;

import models.Book;
import models.User;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;

public class DatabaseConnections { //TODO split up this class into DatabaseConnections and DatabaseOperations

    private static Connection connection = null;
    private static String url = "jdbc:mysql://localhost:3306/"
            + "lmsdatabase?allowPublicKeyRetrieval=true&useSSL=false";
    private static String user = "root";
    private static String password = "Password1234!";

    private static Connection SQLConnection() {
        try {
            connection = DriverManager.getConnection(url, user, password);
            return connection;
        } catch (SQLSyntaxErrorException ssee) {
            createDatabase();
            insertBooks();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return connection;
    }

    public static void createDatabase() {
        String schemaSQL = "CREATE SCHEMA IF NOT EXISTS lmsdatabase;";
        String sqlBooks = "CREATE TABLE IF NOT EXISTS books(book_id INT AUTO_INCREMENT PRIMARY KEY, title VARCHAR(255), author VARCHAR(255), genre VARCHAR(255));";
        String sqlUsers = "CREATE TABLE IF NOT EXISTS users(user_id INT AUTO_INCREMENT PRIMARY KEY, full_name VARCHAR(255), username VARCHAR(255), password VARCHAR(255), email VARCHAR(255));";
        String sqlCheckout = "CREATE TABLE IF NOT EXISTS checkout(checkout_id INT AUTO_INCREMENT PRIMARY KEY, book_id INT, user_id INT, FOREIGN KEY (book_id) REFERENCES books(book_id), FOREIGN KEY (user_id) REFERENCES users(user_id));";
        String sqlLost = "CREATE TABLE IF NOT EXISTS lost_books(lost_book_id INT AUTO_INCREMENT PRIMARY KEY, book_id INT, user_id INT, FOREIGN KEY (book_id) REFERENCES books(book_id), FOREIGN KEY (user_id) REFERENCES users(user_id));";
        String sqlFees = "CREATE TABLE IF NOT EXISTS fees(fee_id INT AUTO_INCREMENT PRIMARY KEY, book_id INT, user_id INT, fee_amount DECIMAL(5,2), FOREIGN KEY (book_id) REFERENCES books(book_id), FOREIGN KEY (user_id) REFERENCES users(user_id));";
        String url2 = "jdbc:mysql://localhost:3306/"
                + "?allowPublicKeyRetrieval=true&useSSL=false";

        try {
            Connection con = DriverManager.getConnection(url2, user, password);
            Statement st = con.createStatement();
            st.executeUpdate(schemaSQL);

            st = DriverManager.getConnection(url, user, password).createStatement();
            st.executeUpdate(sqlBooks);
            st.executeUpdate(sqlUsers);
            st.executeUpdate(sqlCheckout);
            st.executeUpdate(sqlLost);
            st.executeUpdate(sqlFees);
            con.close();

        } catch (SQLException SQLe) {
            SQLe.printStackTrace();
        }
    }

    private static void insertBooks() {
        File bookList = new File("src//main//data.bookData//books.csv");
        String insertBook  = "INSERT INTO books(title, author) Values(?,?)";
        PreparedStatement pst = null;
        if (bookList.exists()) {
            BufferedReader br;
            try {
                br = new BufferedReader(new FileReader(bookList));
                String line = br.readLine();
                while ((line = br.readLine()) != null) {
                    String[] bookInfo = line.split("[,]", 0);
                    try {
                        Connection con = SQLConnection();
                        pst = con.prepareStatement(insertBook);
                        pst.setString(1, bookInfo[0]);
                        pst.setString(2, bookInfo[1]);
                        pst.executeUpdate();
                        con.close();
                    } catch (SQLException SQLe) {
                        SQLe.printStackTrace();
                    }
                }
                br.close();
            } catch (IOException io) {
                io.printStackTrace();
            }
        } else {
            System.out.println("File not found");
        }

    }

    public static void runDatabase() {
        SQLConnection();
    }

    /**
     * This method is used to insert newly created user accounts into the lmsdatabase.
     *
     * @param _username String _username to set username
     * @param _password String _password to set password
     * @param _fullName String _fullname to set fullname
     */
    public static void createUser(String _fullName, String _username, String _password) {
        String insertUser = "INSERT INTO users(full_name, username, password) Values(?,?,?)";
        try {
            Connection con = SQLConnection();
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
        String checkUser = "SELECT * FROM lmsdatabase.users WHERE full_name=(?) OR password=(?);";
        ArrayList<String[]> users = new ArrayList<>();
        String[] userInfo = new String[4];

        try {
            Connection con = SQLConnection();
            PreparedStatement pst = con.prepareStatement(checkUser);
            pst.setString(1, field);
            pst.setString(2, field);
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

    public static String[] getUser(String username) {
        String checkUser = "SELECT * FROM lmsdatabase.users WHERE username=(?);";
        String[] userInfo = new String[4];

        try {
            Connection con = SQLConnection();
            PreparedStatement pst = con.prepareStatement(checkUser);
            pst.setString(1, username);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                userInfo[0] = rs.getString(1);
                userInfo[1] = rs.getString(2);
                userInfo[2] = rs.getString(3);
                userInfo[3] = rs.getString(4);
            }
            con.close();
        } catch (SQLException SQLe) {
            SQLe.printStackTrace();
        }
        return userInfo;
    }

    public static void changeUserPassword(String username, String newPassword) {
        String newPassQuery = "UPDATE users SET password=(?) WHERE username=(?);";

        try {
            Connection con = SQLConnection();
            PreparedStatement pst = con.prepareStatement(newPassQuery);
            pst.setString(1, newPassword);
            pst.setString(2, username);
            pst.executeUpdate();
            con.close();
        } catch (SQLException SQLe) {
            SQLe.printStackTrace();
        }
    }

    public static void changeFullName(String newFullName, String username, String password) {
        String newUserName = "UPDATE users SET full_name=(?) WHERE username=(?) AND password=(?);";
        try {
            Connection con = SQLConnection();
            PreparedStatement pst = con.prepareStatement(newUserName);
            pst.setString(1, newFullName);
            pst.setString(2, username);
            pst.setString(3, password);
            pst.executeUpdate();
            con.close();
        } catch (SQLException SQLe) {
            SQLe.printStackTrace();
        }
    }

    private static void runSQLScript(String sqlQuery, String newPassword, String userName) {

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
            Connection con = SQLConnection();
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

    public static ArrayList<String[]> getAllBooks() {
        String checkUser = "SELECT * FROM lmsdatabase.books;";
        ArrayList<String[]> books = new ArrayList<>();


        try {
            Connection con = SQLConnection();
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

    public static String[] getBook(int bookId) {
        String checkUser = "SELECT * FROM lmsdatabase.books WHERE book_id=?;";
        String[] foundBook = new String[3];

        try {
            Connection con = SQLConnection();
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

    public static ArrayList<int[]> getAllCheckedOut() {
        String checkOut = "SELECT * FROM lmsdatabase.checkout;";
        ArrayList<int[]> checkedOut = new ArrayList<>();

        try {
            Connection con = SQLConnection();
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

    public static boolean checkOutBook(Book book, User user){
        String checkedOutBook = "INSERT INTO checkout (book_id, user_id) Values(?,?)";
        try{
            Connection con = SQLConnection();
            PreparedStatement pst = con.prepareStatement(checkedOutBook);
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

    public static boolean checkInBook(Book book, User user){
        String checkInBook = "DELETE FROM checkout WHERE book_id=(?) AND user_id=(?)";
        try{
            Connection con = SQLConnection();
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
}
