package com.desktopapp;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class DrawController implements Initializable {

    public static Scene CreateScene() throws Exception
    {
        URL sceneUrl = DrawController.class
        .getResource("CanvasScreen.fxml");
        FXMLLoader loader = new FXMLLoader(sceneUrl);
        Parent root = loader.load();
        Scene scene = new Scene(root);

        return scene;
    }

    @FXML
    private Canvas canvas;

    @FXML
    private void moved(MouseEvent e) {

    }

    @FXML
    private void pressed(MouseEvent e) {
        
    }

    @FXML
    private void released(MouseEvent e) {
        
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        draw();
    }

    private void draw() {
        var g = canvas.getGraphicsContext2D();

        g.setFill(Color.YELLOW);
        g.fillRect(0, 0, 400, 400);
    }

}
