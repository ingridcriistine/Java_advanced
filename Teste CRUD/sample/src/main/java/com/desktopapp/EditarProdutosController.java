package com.desktopapp;

import java.net.URL;

import com.desktopapp.model.Product;
import com.desktopapp.model.User;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class EditarProdutosController {
    public static Scene CreateScene(Product product, User user) throws Exception {

        
        URL sceneUrl = EditarProdutosController.class
        .getResource("EditarProdutos.fxml");
        FXMLLoader loader = new FXMLLoader(sceneUrl);
        Parent root = loader.load();
        Scene scene = new Scene(root);
        
        EditarProdutosController controller = loader.getController();
        controller.tfNome.setText(product.getName());
        controller.tfDescricao.setText(product.getDescricao());
        controller.tfValorUnitario.setText(product.getValorUnitario());
        controller.setProduct(product);
        controller.setUser(user);
        
        return scene;
    }

    protected Product product;
    
    protected User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @FXML
    protected TextField tfNome;

    @FXML
    protected TextField tfDescricao;

    @FXML
    protected TextField tfValorUnitario;

    @FXML
    protected Button btEditar;

    @FXML
    protected Button btCancelar;

    @FXML
    protected void editar(MouseEvent e) throws Exception {    

        Context ctx = new Context();
        ctx.begin();

        product.setName(tfNome.getText());
        product.setDescricao(tfDescricao.getText());
        product.setValorUnitario(tfValorUnitario.getText());
        
        ctx.update(product);
        ctx.commit();

        var crrStage = (Stage) btCancelar
                .getScene().getWindow();
        crrStage.close();
        var stage = new Stage();
        var scene = HomeController.CreateScene(user);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    protected void cancelar(MouseEvent e) throws Exception {    

        var crrStage = (Stage) btCancelar
                .getScene().getWindow();
        crrStage.close();
        var stage = new Stage();
        var scene = HomeController.CreateScene(user);
        stage.setScene(scene);
        stage.show();
    }

    
}
