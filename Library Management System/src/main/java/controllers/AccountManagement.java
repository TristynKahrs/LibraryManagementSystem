package controllers;

import models.DisplayBooks;
import models.User;

import javax.naming.CannotProceedException;

public class AccountManagement {
    public static User activeUser;

    public static void setActiveUser(User newActiveUser) {
        activeUser = newActiveUser;
    }
    /**
     * createAccount should be able to grab the full name of the user, the username and the password.
     * After that, it should make a new user and save it to the database
     *
     * @param fullName legal name
     * @param username selected username
     * @param password preferred password
     */
    public static void createAccount(String fullName, String username, String password) throws SecurityException {
        if (DatabaseOperations.getUser(username)[1] != null) {
            throw new SecurityException();
        }
        DatabaseOperations.createUser(fullName, username, password);
        DisplayBooks.setAllBooks();
    }

    /**
     * login should be able to grab the strings of the username and the password.
     * After that, it should be able to find that user and check if the password
     * is correct, if not, throw error or something.
     *
     * @param username account selection
     * @param password account access
     */
    public static User login(String username, String password) throws SecurityException {
        activeUser = new User(DatabaseOperations.getUser(username));
        if (activeUser.passwordMatches(password)) {
            DisplayBooks.setAllBooks();
            return activeUser;
        }
        throw new SecurityException();
    }

    /**
     * verifyAccount should get the username and the full name and check if it is
     * in our database, if so, give password
     *
     * @param fullName matches to account to verify user
     * @param username account access
     */
    public static User verifiedAccount(String fullName, String username) throws SecurityException {
        User activeUser = new User(DatabaseOperations.getUser(username));
        for (String[] userInfo : DatabaseOperations.getUsers(fullName)) {
            if (activeUser.passwordMatches(new User(userInfo))) {
                return activeUser;
            }
        }
        throw new SecurityException();
    }

    /**
     * recoverPassword should look for a certain user and grab the password from there and spit it out
     * UI should handle exception, it is passed when there is no match OR that username doesn't exist
     *
     * @param fullName acts as a recovery key as of now
     * @param username account access
     */
    public static String recoverPassword(String fullName, String username) throws SecurityException {
        User activeUser = verifiedAccount(fullName, username);
        if (activeUser != null) {
            return activeUser.getPassword();
        }
        throw new SecurityException();
    }

    /**
     * changePassword should grab the user, be able to match the old password and update the new password
     *
     * @param activeUser  current user
     * @param oldPassword confirming they entered the correct old password
     * @param newPassword updated password they want
     */
    public static void changePassword(User activeUser, String oldPassword, String newPassword) throws CannotProceedException, SecurityException {
        if (!activeUser.passwordMatches(newPassword)) {
            if (activeUser.passwordMatches(oldPassword)) {
                DatabaseOperations.changeUserPassword(activeUser.getUsername(), newPassword);
            } else {
                throw new SecurityException();
            }
        } else {
            throw new CannotProceedException();
        }
    }

    /**
     * This method acts as a buffer and changes users full name.
     *
     * @param activeUser A user Object used to change users full name.
     * @param newName    String newName is used to set to their new full name.
     */
    public static void changeFullName(User activeUser, String newName) {
        activeUser.setFullName(newName);
        DatabaseOperations.changeFullName(newName, activeUser.getUsername());
    }
}