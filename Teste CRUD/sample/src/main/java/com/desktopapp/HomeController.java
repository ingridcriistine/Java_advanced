package com.desktopapp;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import com.desktopapp.model.Product;
import com.desktopapp.model.User;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class HomeController implements Initializable {

    public static Scene CreateScene(User user) throws Exception {
        URL sceneUrl = HomeController.class
                .getResource("HomeScreen.fxml");
        FXMLLoader loader = new FXMLLoader(sceneUrl);
        Parent root = loader.load();
        Scene scene = new Scene(root);

        HomeController controller = loader.getController();
        controller.setUser(user);
        loader.setController(controller);

        return scene;
    }

    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @FXML
    private Button mnHome;

    @FXML
    private Button mnPerfil;

    @FXML
    private Button btCadastro;

    @FXML
    private TextField tfPesquisa;

    @FXML
    private Button btPesquisar;

    @FXML
    private TableView<ButtonsTable> tbProdutos;

    @FXML
    private TableColumn<ButtonsTable, String> colID;

    @FXML
    private TableColumn<ButtonsTable, String> colNome;

    @FXML
    private TableColumn<ButtonsTable, String> colDescricao;

    @FXML
    private TableColumn<ButtonsTable, String> colPreco;

    @FXML
    private TableColumn<ButtonsTable, Button> colDelete;

    @FXML
    private TableColumn<ButtonsTable, Button> colEditar;

    @FXML
    private void perfil(MouseEvent e) throws Exception {
        Stage crrStage = (Stage) mnPerfil
                .getScene()
                .getWindow();

        Scene newScene = PerfilController.CreateScene(user);
        crrStage.setScene(newScene);
    }

    @FXML
    private void updateHome(MouseEvent e) throws Exception {
        Stage crrStage = (Stage) mnHome
                .getScene()
                .getWindow();

        Scene newScene = HomeController.CreateScene(user);
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

    protected ObservableList<ButtonsTable> listaProdutos() {
        Context ctx = new Context();
        ctx.begin();
        List<Product> lista = ctx.findAll(Product.class);

        List<ButtonsTable> buttons = lista.stream().map(n -> {
            var btn = new ButtonsTable(n, tbProdutos, this, user);
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
        var scene = CadastroProdutosController.CreateScene(user);
        stage.setScene(scene);
        stage.show();
    }

    @FXML 
    protected void pesquisar(MouseEvent e) throws Exception {
        tbProdutos.setItems(resultadoPesquisa());
    }

    @FXML 
    protected void pesquisarEnter(KeyEvent e) throws Exception {
        if (e.getCode().equals(KeyCode.ENTER)) {
            tbProdutos.setItems(resultadoPesquisa());
        }
    }

    protected ObservableList<ButtonsTable> resultadoPesquisa() {
        String pesquisa = tfPesquisa.getText();
        
        Context ctx = new Context();
        ctx.begin();
        
        List<Product> lista = ctx.find(Product.class, "select p from Product p where name LIKE :arg0", "%" + pesquisa + "%");

        List<ButtonsTable> buttons = lista.stream().map(n -> {
            var btn = new ButtonsTable(n, tbProdutos, this, user);
            return btn;
        })
        .collect(Collectors.toList());

        return FXCollections.observableArrayList(
            buttons
        );
    }

}