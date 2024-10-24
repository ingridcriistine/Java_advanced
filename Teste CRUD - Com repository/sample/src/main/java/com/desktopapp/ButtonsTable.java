package com.desktopapp;
import com.desktopapp.implementations.DatabaseProductRepository;
import com.desktopapp.model.Product;
import com.desktopapp.repositories.ProductRepository;

import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class ButtonsTable extends Product {
    private Button btDelete;
    private Button btEditar;
    
    public ButtonsTable (Product product, TableView<Product> tbProd, HomeController controller) {

        ProductRepository repo = new DatabaseProductRepository();

        this.setId(product.getId());
        this.setName(product.getName());
        this.setDescricao(product.getDescricao());
        this.setValorUnitario(product.getValorUnitario());
        
        this.btDelete = new Button();
        this.btDelete.setText("❌");
        this.btDelete.setOnAction((ActionEvent event) -> {
            repo.delete(product);
            tbProd.setItems(controller.listaProdutos());
        });

        this.btEditar = new Button();
        this.btEditar.setText("📝");
        this.btEditar.setOnAction((ActionEvent event) -> {
            try {
                var crrStage = (Stage) btEditar.getScene().getWindow();
                crrStage.close();
                var stage = new Stage();
                Scene scene;
                scene = EditarProdutosController.CreateScene(product, repo);
                stage.setScene(scene);
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }

        });
    }

    public Button getBtDelete() {
        return btDelete;
    }

    public void setBtDelete(Button btDelete) {
        this.btDelete = btDelete;
    }

    public Button getBtEditar() {
        return btEditar;
    }

    public void setBtEditar(Button btEditar) {
        this.btEditar = btEditar;
    }
}
