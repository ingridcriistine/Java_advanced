package com.desktopapp;

import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application
{
    @Override
    public void start(Stage primaryStage) throws Exception {
        var validate = MyEmailValidator.Validate("ingrid@email.com");
        System.out.println(validate);
    }
    
    public static void main(String[] args) {
        launch(args);
    }

}
