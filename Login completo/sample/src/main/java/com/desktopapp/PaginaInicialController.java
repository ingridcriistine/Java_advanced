package com.desktopapp;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import com.desktopapp.model.Mensagem;
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
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class PaginaInicialController implements Initializable {

    public static Scene CreateScene(User user) throws Exception {
        URL sceneUrl = PaginaInicialController.class
                .getResource("PaginaInicial.fxml");
        FXMLLoader loader = new FXMLLoader(sceneUrl);
        Parent root = loader.load();
        Scene scene = new Scene(root);

        PaginaInicialController controller = loader.getController();
        controller.setUser(user);
        controller.txtOla.setText("Olá, " + user.getName());
        loader.setController(controller);

        System.out.println(user.getEmail());
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
    protected Text txtOla;

    @FXML
    private Button btNovaMensagem;

    @FXML
    private TextField tfPesquisa;

    @FXML
    private Button btPesquisar;

    @FXML
    private TableView<ButtonsTable> tbMensagens;

    @FXML
    private TableColumn<ButtonsTable, String> colID;

    @FXML
    private TableColumn<ButtonsTable, String> colTitulo;

    @FXML
    private TableColumn<ButtonsTable, String> colPessoa;

    @FXML
    private TableColumn<ButtonsTable, Button> colVerMensagem;

    @FXML
    private TableColumn<ButtonsTable, Button> colDelete;

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

        Scene newScene = PaginaInicialController.CreateScene(user);
        crrStage.setScene(newScene);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        colID.setCellValueFactory(new PropertyValueFactory<>("id"));
        colTitulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        colPessoa.setCellValueFactory(new PropertyValueFactory<>("origem"));
        colVerMensagem.setCellValueFactory(new PropertyValueFactory<>("btVerMensagem"));
        colDelete.setCellValueFactory(new PropertyValueFactory<>("btDelete"));

        tbMensagens.setItems(listaMensagens());
        System.out.println(listaMensagens());
    }

    protected ObservableList<ButtonsTable> listaMensagens() {
        
        Context ctx = new Context();
        ctx.begin();
        List<Mensagem> lista = ctx.findAll(Mensagem.class);
        // List<Mensagem> lista = ctx.find(Mensagem.class, "select m from Mensagem m where m.destino LIKE :arg0", "%" + user.getEmail() + "%");

        List<ButtonsTable> buttons = lista.stream().map(n -> {
            var btn = new ButtonsTable(n, tbMensagens, this, user);
            return btn;
        })
        .collect(Collectors.toList());

        return FXCollections.observableArrayList(
            buttons
        );
    }

    @FXML
    protected void novaMensagem(MouseEvent e) throws Exception {

        var crrStage = (Stage) btNovaMensagem
                .getScene().getWindow();
        crrStage.close();
        var stage = new Stage();
        var scene = EnviarMensagemController.CreateScene(user);
        stage.setScene(scene);
        stage.show();
    }

    @FXML 
    protected void pesquisar(MouseEvent e) throws Exception {
        tbMensagens.setItems(resultadoPesquisa());
    }

    @FXML 
    protected void pesquisarEnter(KeyEvent e) throws Exception {
        if (e.getCode().equals(KeyCode.ENTER)) {
            tbMensagens.setItems(resultadoPesquisa());
        }
    }

    protected ObservableList<ButtonsTable> resultadoPesquisa() {
        String pesquisa = tfPesquisa.getText();
        
        Context ctx = new Context();
        ctx.begin();
        
        List<Mensagem> lista = ctx.find(Mensagem.class, "select m from Mensagem m where titulo LIKE :arg0", "%" + pesquisa + "%");

        List<ButtonsTable> buttons = lista.stream().map(n -> {
            var btn = new ButtonsTable(n, tbMensagens, this, user);
            return btn;
        })
        .collect(Collectors.toList());

        return FXCollections.observableArrayList(
            buttons
        );
    }

}