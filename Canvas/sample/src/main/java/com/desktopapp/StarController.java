package com.desktopapp;

import java.net.URL;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
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

public class StarController implements Initializable {

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

    Timer timer = new Timer();

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                rotation += Math.PI / 20;
                draw();   
                box.requestLayout();
            }
        }, 50, 50);
    }

    double rotation = 0;
    private void draw() {
        var g = canva.getGraphicsContext2D();

        g.clearRect(0, 0, canva.getWidth(), canva.getHeight());

        double[] xs = new double[10];
        double[] ys = new double[10];
        double theta = 0f;

        //definir a estrela
        for (int i = 0; i < 10; i++) {

            double rho = i % 2 == 0 ? 200 : 80;
            
            xs[i] = rho * Math.cos(theta);
            ys[i] = rho * Math.sin(theta);

            theta += 2 * Math.PI / 10;
        }

        //rotacionar
        for (int i = 0; i < 10; i++) {
            var x = xs[i];
            var y = ys[i];

            xs[i] = x * Math.cos(rotation) + y * Math.sin(rotation);
            // ys[i] = x * Math.cos(rotation) - y * Math.sin(rotation);
            ys[i] = x * Math.sin(rotation) - y * Math.cos(rotation);
        }

        //desloca para o centro
        for (int i = 0; i < 10; i++) {  
            xs[i] += canva.getWidth() / 2;
            ys[i] += canva.getHeight() / 2;
        }

        g.fillPolygon(xs, ys, 10);

    }

}
