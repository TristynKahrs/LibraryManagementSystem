package controllers;
import views.LoginGUIController;
public class Main {

    public static void main(String[] args){
        try {
            LoginGUIController.launch();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
