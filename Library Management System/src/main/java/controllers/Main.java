package controllers;
import views.CLI;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello Chris_1");
        try {
            CLI.startupMenu();
        }catch(Exception e){
            e.printStackTrace();
        }
        System.out.println("Hello Chris_2");
    }
}
