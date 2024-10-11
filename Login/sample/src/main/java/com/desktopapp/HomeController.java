package com.desktopapp;

import java.net.URL;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

public class HomeController {

    public static Scene CreateScene() throws Exception
    {
        URL sceneUrl = HomeController.class
        .getResource("HomeScreen.fxml");
        FXMLLoader loader = new FXMLLoader(sceneUrl);
        Parent root = loader.load();
        Scene scene = new Scene(root);
        
        HomeController controller = loader.getController();
        loader.setController(controller);

        return scene;
    }

    @FXML
    private Button mnLogin;

    @FXML
    private Button mnHome;

    @FXML
    private void backLogin() throws Exception {
         Stage crrStage = (Stage)mnLogin
            .getScene()
            .getWindow();

            Scene newScene = MainController.CreateScene(0);
            crrStage.setScene(newScene);
    }

    @FXML
    private void updateHome() throws Exception {
        Stage crrStage = (Stage)mnLogin
            .getScene()
            .getWindow();

            Scene newScene = HomeController.CreateScene();
            crrStage.setScene(newScene);
    }

    @FXML 
    private Button btCompra1;

    @FXML 
    private Button btCompra2;

    @FXML
    private void telaCompra(MouseEvent e) throws Exception {
        
        Button button = (Button) e.getSource();

        if(button == btCompra1) {
            Stage stage = (Stage)button.getScene().getWindow(); 
            Scene newScene = HomeController.CreateScene();
            stage.setScene(newScene);
        }
        if(button == btCompra2) {
            Stage stage = (Stage)button.getScene().getWindow(); 
            Scene newScene = HomeController.CreateScene();
            stage.setScene(newScene);
        }

    }
}