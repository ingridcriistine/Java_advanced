package com.desktopapp;
import com.desktopapp.model.Product;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class ButtonsTable extends Product {
    private Button button;
    
    public ButtonsTable (Product product, TableView<Product> tbProd, HomeController controller) {
        var btn = new Button();
        btn.setText("X");
        btn.setOnAction((ActionEvent event) -> {
            Context ctx = new Context();
            ctx.begin();
            ctx.delete(product);
            ctx.commit();

            tbProd.setItems(controller.listaProdutos());

            // try {
            //     var crrStage = (Stage) button.getScene().getWindow();
            //     crrStage.close();
            //     var stage = new Stage();
            //     var scene = HomeController.CreateScene();
            //     stage.setScene(scene);
            //     stage.show();

            // } catch (Exception e) {
            //     e.printStackTrace();
            // }
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
