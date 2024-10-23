package com.desktopapp;
import com.desktopapp.model.Product;

import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ButtonsTable extends Product {
    private Button button;
    
    public ButtonsTable (Product product) {
        var btn = new Button();
        btn.setText("X");
        btn.setOnAction((ActionEvent event) -> {
            Context ctx = new Context();
            ctx.begin();
            ctx.delete(product);
            ctx.commit();

            try {
                var stage = new Stage();
                Scene scene;
                scene = HomeController.CreateScene();
                stage.setScene(scene);
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        
        this.button = btn;
        this.setId(product.getId());
        this.setName(product.getName());
        this.setDescricao(product.getDescricao());
        this.setValorUnitario(product.getValorUnitario());
    }
    
    public Button getButton() {
        return button;
    }
    
    public void setButton(Button button) {
        this.button = button;
    }
}
