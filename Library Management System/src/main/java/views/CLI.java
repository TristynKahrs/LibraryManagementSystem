package views;

import controllers.AccountManagement;
import controllers.LibraryManagement;
import models.Book;
import models.DisplayBooks;
import models.User;

import javax.naming.CannotProceedException;
import javax.naming.SizeLimitExceededException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.module.FindException;
import java.util.ArrayList;

public class CLI {
    public static void startupMenu() {
        int choice = -1;

        int min = 0;
        int max = 3;
        StringBuilder sb = new StringBuilder("\nPlease, choose one of the following\n\n");
        sb.append("1) Log-in\n");
        sb.append("2) Create Account\n");
        sb.append("3) Forgot Password\n");
        sb.append("\n0) Exit\n");
        sb.append("\nEnter the number of your selection: ");

        String menu = sb.toString();

        do {
            switch (choice) {
                case 1 -> logIn();
                case 2 -> createAccount();
                case 3 -> recoverPassword();
            }
            choice = promptForInt(menu, min, max);
        } while (choice > 0);
    }

    /*
    I saw that when you try to recover the password, the full name and the username are case-insensitive
    This is probably due to SQL being case-insensitive by default, don't know if it's able to be fixed or needs to be fixed overall
     */

    private static void logIn() {
        boolean logInFail;
        do {
            System.out.println("\nLog In");
            String username = promptForString("Please, enter your username: ", false);
            String password = promptForString("\nPlease, enter your password: ", false);
            try {
                User activeUser = AccountManagement.login(username, password);
                mainUserMenu(activeUser);
                logInFail = false;
            } catch (SecurityException se) {
                System.out.println("\nFailed login\n");
                logInFail = true;
            }
        } while (logInFail);
    }

    private static void createAccount() {
        boolean creationFailure;
        do {
            System.out.println("\nCreate Account");
            String fullName = promptForString("Please, enter your full name. Ex. Alex Turro: ", false);
            String username = promptForString("\nPlease, create your username: ", false);
            String password = promptForString("\nPlease, create your password: ", false);
            try {
                AccountManagement.createAccount(fullName, username, password);
                creationFailure = false;
            } catch (SecurityException se) {
                creationFailure = true;
                System.out.println("\nFailed to create account\n");
            }

        } while (creationFailure);
    }

    private static void recoverPassword() {
        System.out.println("\nRecover Password");
        String userName = promptForString("Please, enter your username: ", false);
        String fullName = promptForString("\nPlease, enter your full name: ", false);
        try {
            String recoveredPassword = AccountManagement.recoverPassword(fullName, userName);
            System.out.println("\nPassword: " + recoveredPassword);
        } catch (SecurityException e) {
            System.out.println("\nFailed to recover the password\n");
        }
    }

    private static void mainUserMenu(User activeUser) {
        int choice = -1;

        int min = 0;
        int max = 4;
        StringBuilder sb = new StringBuilder("\n\nHey " + activeUser.getFullName() + "!\n\nPlease, choose one of the following\n\n");
        sb.append("1) Check-in books\n");
        sb.append("2) Browse book\n");
        sb.append("3) Change Password\n");
        sb.append("\n0) Log-out\n");
        sb.append("\nEnter the number of your selection: ");

        String menu = sb.toString();
        do {
            switch (choice) {
                case 1 -> checkInBook(activeUser);
                case 2 -> browse(activeUser);
                case 3 -> changePassword(activeUser);
                //fees not implemented
            }
            choice = promptForInt(menu, min, max);
        } while (choice > 0);
    }

    private static void checkInBook(User activeUser) {
        DisplayBooks.setAllBooks();
        ArrayList<Book> checkedOutBooks = LibraryManagement.usersCheckedOutBooks(activeUser);
        System.out.println("\n\nCheck-in Book");
        for (int i = 0; i < checkedOutBooks.size(); i++) {
            System.out.println((i + 1) + ") " + checkedOutBooks.get(i));
        }
        System.out.println("\n\n0) Exit Check-In Menu\n");
        int choice = promptForInt("Please enter your selection: ", 0, checkedOutBooks.size());
        if (choice == 0) {
            return;
        }
        Book checkInBook = checkedOutBooks.get(choice - 1);
        LibraryManagement.checkIn(activeUser, checkInBook);
    }

    private static void show() {
        StringBuilder retString = new StringBuilder();
        Book[] books = DisplayBooks.page();
        for (int i = 0; i < books.length; i++) {
            Book book = books[i];
            if (book != null) {
                retString.append("\n").append(i + 1).append(") ").append(book).append("\n");
            }
        }
        System.out.println(retString);
    }

    private static void browse(User activeUser) {
        boolean keepLooping = true;
        do {
            show();
            System.out.println("\n0) Exit Browse Menu");
            String input = promptForString("\nSelect a book, search for a book, or press '+' (next) or '-' (previous): ", true).trim();
            try {
                int choice = Integer.parseInt(input);
                if (0 < choice && choice <= DisplayBooks.getPageSize()) {
                    Book userSelectedBook = DisplayBooks.getSelectedBook(choice - 1);
                    try {
                        DisplayBooks.setAllBooks();
                        LibraryManagement.checkOut(activeUser, userSelectedBook);
                    } catch (NullPointerException npe) {
                        System.out.println("\nSorry, please select an option that is present.\n");
                    } catch (SizeLimitExceededException slee) {
                        System.out.println("\nSorry, you are already at max capacity on books.\n");
                    } catch (Exception e) {
                        System.out.println("\nSorry, we don't know what happened, please try again.\n");
                    }
                    keepLooping = false;
                } else if (choice == 0) {
                    keepLooping = false;
                }
            } catch (NumberFormatException nfe) {
                switch (input) {
                    case "+":
                        DisplayBooks.next();
                        break;
                    case "-":
                        DisplayBooks.prev();
                        break;
                    case "":
                        DisplayBooks.setAllBooks();
                        break;
                    default:
                        try {
                            DisplayBooks.searchBooks(input);
                        }catch (FindException fe) {
                            System.out.println("\nSorry, we don't have anything that matches your result.\n");
                        }
                        DisplayBooks.setAllBooks();
                        break;
                }
            }
        } while (keepLooping);
    }

    private static void changePassword(User activeUser) {
        System.out.println("\nChange Password");
        String oldPassword = promptForString("Please, enter your old password: ", false);
        String newPassword = promptForString("\nPlease, enter your new password: ", false);
        String confirmPassword = promptForString("\nPlease, confirm your new password: ", false);
        if (newPassword.equals(confirmPassword)) {
            try {
                AccountManagement.changePassword(activeUser, oldPassword, newPassword);
            } catch (CannotProceedException cpe) {
                System.out.println("\nNew Password cannot be the same as your old.");
            } catch (SecurityException se) {
                System.out.println("\nFailed to change password, you must correctly enter your old password.");
            } catch (Exception e) {
                System.out.println("\nWe're Unsure what happened, please try again.");
            }
        } else {
            System.out.println("\nPassword confirmation must be the same password.");
        }
    }

    private static String promptForString(String prompt, boolean allowBlank) {
        if (prompt == null || prompt.isBlank()) {
            throw new IllegalArgumentException("The prompt cannot be null, empty, or white space. prompt=" + prompt);
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = null;
        boolean inputIsInvalid = true;

        do {
            System.out.print(prompt);
            try {
                input = br.readLine();
                inputIsInvalid = input == null || (!allowBlank && input.isBlank());

                if (inputIsInvalid) {
                    System.out.println("Your input was invalid. Please, try again");
                }
            } catch (IOException ioe) {
                System.out.println("There was a problem and your input was not received. Please, try again");
            }
        } while (inputIsInvalid);
        return input;
    }

    private static int promptForInt(String prompt, int min, int max) {
        if (min > max) {
            throw new IllegalArgumentException("The min cannot be greater than the max. min=" + min + "; max=" + max);
        }

        int num = -1;
        boolean numIsInvalid = true;

        do {
            String input = promptForString(prompt, false);
            try {
                num = Integer.parseInt(input);
                numIsInvalid = num < min || num > max;
            } catch (NumberFormatException nfe) {
                //No need to do anything here
            }

            if (numIsInvalid) {
                System.out.println("You must enter a whole number between " + min + " and " + max + ". Please, try again");
            }

        } while (numIsInvalid);
        return num;
    }
}
