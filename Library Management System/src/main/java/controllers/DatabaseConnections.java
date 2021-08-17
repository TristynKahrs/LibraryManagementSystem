package controllers;

import models.Book;
import models.User;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class DatabaseConnections {

    private static Connection connection = null;
    private static String url = "jdbc:mysql://localhost:3306/"
            + "lmsdatabase?allowPublicKeyRetrieval=true&useSSL=false";
    private static String user = "root";
    private static String password = "Password1234!";

    /**
     * This method creates a connection from java program to the localhost of MySQL.
     * @return Returns a Connection to the lmsdatabase.
     */
    public static Connection SQLConnection() {
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

    /**
     * This method creates a lmsdatabase in the localhost of MySQL and creates a books, users, checkout, lost_books, and fees
     * table.
     */
    public static void createDatabase() {
        String schemaSQL = "CREATE SCHEMA IF NOT EXISTS lmsdatabase;";
        String sqlBooks = "CREATE TABLE IF NOT EXISTS books(book_id INT AUTO_INCREMENT PRIMARY KEY, title VARCHAR(255), author VARCHAR(255));";
        String sqlUsers = "CREATE TABLE IF NOT EXISTS users(user_id INT AUTO_INCREMENT PRIMARY KEY, full_name VARCHAR(255), username VARCHAR(255), password VARBINARY(255));";
        String sqlCheckout = "CREATE TABLE IF NOT EXISTS checkout(checkout_id INT AUTO_INCREMENT PRIMARY KEY, book_id INT, user_id INT, checkOutDate DATE, FOREIGN KEY (book_id) REFERENCES books(book_id), FOREIGN KEY (user_id) REFERENCES users(user_id));";
        String sqlLost = "CREATE TABLE IF NOT EXISTS lost_books(lost_book_id INT AUTO_INCREMENT PRIMARY KEY, book_id INT, user_id INT, FOREIGN KEY (book_id) REFERENCES books(book_id), FOREIGN KEY (user_id) REFERENCES users(user_id));";
        String sqlFees = "CREATE TABLE IF NOT EXISTS fees(fee_id INT AUTO_INCREMENT PRIMARY KEY, book_id INT, user_id INT, fee_amount DOUBLE PRECISION(5,2), FOREIGN KEY (book_id) REFERENCES books(book_id), FOREIGN KEY (user_id) REFERENCES users(user_id));";
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

    /**
     * This method inserts books into books table in lmsdatabase using books.csv file.
     */
    private static void insertBooks() {
        File bookList = new File("src//main//java//data.bookData//books.csv");
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
}

