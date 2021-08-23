package models;

import controllers.DatabaseConnections;
import controllers.DatabaseOperations;

public class Book {
    public int primaryKey;
    public String title;
    public String author;
    //Enum genre
    //image bit data

    public Book(int primaryKey, String title, String author) {
        setPrimaryKey(primaryKey);
        setTitle(title);
        setAuthor(author);
    }

    public Book(String[] bookInfo){
        primaryKey = Integer.parseInt(bookInfo[0]);
        setTitle(bookInfo[1]);
        setAuthor(bookInfo[2]);
    }

    public void setPrimaryKey(int primaryKey) {
        this.primaryKey = primaryKey;
    }

    public int getPrimaryKey() {
        return primaryKey;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }

    public boolean isCheckedOut() {
        for(int[] checkedOutInfo : DatabaseOperations.getAllCheckedOut()) {
            if(getPrimaryKey() == checkedOutInfo[1]) {
                return true;
            }
        }
        return false;
    }

    public boolean isLost() {
        for(Integer book_id : DatabaseOperations.getAllLostBookIds()) {
            if(getPrimaryKey() == book_id) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return getTitle()  + " by " + getAuthor();
    }
}
