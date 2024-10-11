package com.desktopapp;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
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
    private VBox box;

    @FXML
    private Canvas canva;

    private ArrayList<Float> values = new ArrayList<>();
    private ArrayList<Color> colors = new ArrayList<>();
    private int selected = -1;

    public void add(Float value, Color color) {
        this.values.add(value);
        this.colors.add(color);
    }

    @FXML
    private void interact(MouseEvent e) {
        double width = canva.getWidth();
        double height = canva.getHeight();
        double total = values.stream().reduce(0f, (a, x) -> a + x);

        double cx = width / 2;
        double cy = height / 2;
        double dx = e.getX() - cx;
        double dy = e.getY() - cy;
        double angle = 180 * Math.atan2(dy, -dx) / Math.PI + 180;

        double distance = Math.sqrt(dx * dx + dy * dy);
        if(distance > width / 2) {
            selected = -1;
            draw();
            box.requestLayout();
            return;
        }

        double currentArc = 0;
        
        for (int i = 0; i < values.size(); i++) {
            Float value = values.get(i);

            double arc = 360 * value / total;
            double initialAngle = currentArc;
            double finalAngle = currentArc + arc;
            currentArc = finalAngle;

            if(angle > initialAngle && angle < finalAngle) {
                selected = i;
            }
        }

        draw();
        box.requestLayout();

    }

    @FXML
    private void pressed(MouseEvent e) {
        
    }

    @FXML
    private void released(MouseEvent e) {
        
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        add(60f, Color.RED);
        add(40f, Color.BLUE);
        add(30f, Color.GREEN);
        add(20f, Color.YELLOW);
        // add(90f, color.web(#090909));
        draw();
        
    }

    private void draw() {
        var g = canva.getGraphicsContext2D();

        double width = canva.getWidth();
        double height = canva.getHeight();
        double total = values.stream().reduce(0f, (a, x) -> a + x);

        double currArc =0;

        for (int i = 0; i < values.size(); i++) {
            Float value = values.get(i);
            Color color = colors.get(i);

            double arc = 360 * value / total;

            if(selected == i) {
                color = new Color(
                    0.6 * color.getRed() + 0.4, 
                    0.6 * color.getGreen() + 0.4, 
                    0.6 * color.getBlue() + 0.4,
                    1f
                );
            }

            g.setFill(color);
            g.fillArc(0, 0, width, height, currArc, arc, javafx.scene.shape.ArcType.ROUND);

            currArc += arc;
        }

        // g.setFill(Color.YELLOW);
        // g.fillRect(0, 0, 400, 400);

    }

}
