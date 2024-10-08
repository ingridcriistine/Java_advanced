package com.desktopapp;

import java.net.URL;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.Parent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

public class MainController {

    public static Scene CreateScene() throws Exception
    {
        URL sceneUrl = MainController.class
        .getResource("MainScreen.fxml");
        Parent root = FXMLLoader.load(sceneUrl);
        Scene scene = new Scene(root);
        return scene;
    }

    @FXML
    protected Button btLogin;

    @FXML
    protected CheckBox cbLogin;

    @FXML
    protected void botao(MouseEvent e)
    {

    }
}