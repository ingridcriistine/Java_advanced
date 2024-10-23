package com.desktopapp;

import java.net.URL;

import com.desktopapp.model.Product;

import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class HomeController implements Initializable {

    public static Scene CreateScene() throws Exception {
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
    private Button btCadastro;

    @FXML
    private TableView<Product> tbProdutos;

    @FXML
    private TableColumn<Product, String> colID;

    @FXML
    private TableColumn<Product, String> colNome;

    @FXML
    private TableColumn<Product, String> colDescricao;

    @FXML
    private TableColumn<Product, String> colPreco;

    @FXML
    private TableColumn<Product, String> colDelete;

    @FXML
    private TableColumn<Product, String> colEditar;

    @FXML
    private void backLogin(MouseEvent e) throws Exception {
        Stage crrStage = (Stage) mnLogin
                .getScene()
                .getWindow();

        Scene newScene = LoginSceneController.CreateScene();
        crrStage.setScene(newScene);
    }

    @FXML
    private void updateHome(MouseEvent e) throws Exception {
        Stage crrStage = (Stage) mnLogin
                .getScene()
                .getWindow();

        Scene newScene = HomeController.CreateScene();
        crrStage.setScene(newScene);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colNome.setCellValueFactory(new PropertyValueFactory<>("name"));
        colDescricao.setCellValueFactory(new PropertyValueFactory<>("descricao"));
        colPreco.setCellValueFactory(new PropertyValueFactory<>("valorUnitario"));
        colDelete.setCellValueFactory(new PropertyValueFactory<>("btDelete"));
        colEditar.setCellValueFactory(new PropertyValueFactory<>("btEditar"));

        tbProdutos.setItems(listaProdutos());
        System.out.println(listaProdutos());
    }

    protected ObservableList<Product> listaProdutos() {
        Context ctx = new Context();
        ctx.begin();
        List<Product> lista = ctx.findAll(Product.class);

        List<ButtonsTable> buttons = lista.stream().map(n -> {
            var btn = new ButtonsTable(n, tbProdutos, this);
            return btn;
        })
        .collect(Collectors.toList());

        return FXCollections.observableArrayList(
            buttons
        );
    }

    @FXML
    protected void cadastrarProduto(MouseEvent e) throws Exception {

        var crrStage = (Stage) btCadastro
                .getScene().getWindow();
        crrStage.close();
        var stage = new Stage();
        var scene = CadastroProdutosController.CreateScene();
        stage.setScene(scene);
        stage.show();
    }

}