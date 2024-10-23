package com.desktopapp;

import java.net.URL;

import com.desktopapp.model.Product;
import com.desktopapp.model.User;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class CadastroProdutosController {
    public static Scene CreateScene() throws Exception {
        URL sceneUrl = CadastroProdutosController.class
                .getResource("CadastroProdutos.fxml");
        Parent root = FXMLLoader.load(sceneUrl);
        Scene scene = new Scene(root);

        return scene;
    }

    @FXML
    protected TextField tfNome;

    @FXML
    protected TextField tfDescricao;

    @FXML
    protected TextField tfValorUnitario;

    @FXML
    protected Button btCadastro;

    @FXML
    protected Button btCancelar;

    @FXML
    protected void cadastro(MouseEvent e) throws Exception {    

        Context ctx = new Context();
        
        var users = ctx.find(User.class,
                "SELECT p FROM Product p WHERE p.name = :arg0",
                tfNome.getText());

        if (users.size() == 1) {
            Alert alert = new Alert(
                    AlertType.ERROR,
                    "Produto já cadastrado!",
                    ButtonType.OK);
            alert.showAndWait();
            return;
        }
        else {
            Product product = new Product();
            product.setName(tfNome.getText());
            product.setDescricao(tfDescricao.getText());
            product.setValorUnitario(tfValorUnitario.getText());

            ctx.begin();
            ctx.persist(product);
            ctx.commit();
        }

        var crrStage = (Stage) btCadastro
                .getScene().getWindow();
        crrStage.close();
        var stage = new Stage();
        var scene = HomeController.CreateScene();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    protected void cancelar(MouseEvent e) throws Exception {    

        var crrStage = (Stage) btCancelar
                .getScene().getWindow();
        crrStage.close();
        var stage = new Stage();
        var scene = HomeController.CreateScene();
        stage.setScene(scene);
        stage.show();
    }
}