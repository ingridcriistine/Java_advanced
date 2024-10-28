package com.desktopapp;

import com.desktopapp.model.Mensagem;
import com.desktopapp.model.User;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application
{
    public static void main(String[] args) {
        User user = new User();
        user.setName("ingrid");
        user.setEmail("ingrid@email.com");;
        user.setPassword("ingrid@1234");

        User user2 = new User();
        user2.setName("teste");
        user2.setEmail("teste@email.com");;
        user2.setPassword("teste@1234");


        Context ctx = new Context();
        ctx.begin();
        ctx.persist(user);
        ctx.commit();

        launch(args);

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Scene scene = LoginSceneController.CreateScene();
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
