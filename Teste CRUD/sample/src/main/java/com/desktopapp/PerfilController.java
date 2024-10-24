package com.desktopapp;

import java.net.URL;

import com.desktopapp.model.User;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

public class PerfilController {

    public static Scene CreateScene(User user) throws Exception {

        URL sceneUrl = PerfilController.class
        .getResource("PerfilScreen.fxml");
        FXMLLoader loader = new FXMLLoader(sceneUrl);
        Parent root = loader.load();
        Scene scene = new Scene(root);
        
        PerfilController controller = loader.getController();
        controller.tfID.setText(String.valueOf(user.getId()));
        controller.tfNome.setText(user.getName());
        controller.setUser(user);

        return scene;
    }

    protected User user;

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
    private TextField tfID;

    @FXML
    private TextField tfNome;

    @FXML
    private Button btAlterarFoto;

    @FXML
    private Button btModalEditar;

    @FXML
    private Button btExcluirConta;

    @FXML
    private Button btAlterarSenha;

    @FXML
    private Button btSair;

    @FXML
    private ImageView ImgPerfil;

    @FXML
    protected Pane modalEditar;

    @FXML
    protected Pane campoInfo;

    @FXML
    private TextField tfEditarNome;

    @FXML
    private Button btEditarInfo;

    @FXML
    protected Pane modalSenha;

    @FXML
    protected TextField tfUsuario;

    @FXML
    protected TextField tfNewSenha;

    @FXML
    protected TextField tfConfirmSenha;

    @FXML
    protected Button btCancelar;

    @FXML
    protected void alterarFoto(MouseEvent e) throws Exception {

    }

    @FXML
    protected void modalEditarInfo(MouseEvent e) throws Exception {
        campoInfo.setVisible(false);
        modalEditar.setVisible(true);
        tfEditarNome.setText(tfNome.getText());
    }

    @FXML
    protected void cancelarEdicao(MouseEvent e) throws Exception 
    {
        campoInfo.setVisible(true);
        modalEditar.setVisible(false);
    }

    @FXML
    protected void editarInfo(MouseEvent e) throws Exception {
        
    }

    @FXML
    protected void excluirConta(MouseEvent e) throws Exception {

    }

    @FXML
    protected void alterarSenha(MouseEvent e) throws Exception {
        campoInfo.setVisible(false);
        modalSenha.setVisible(true);
    }

    String newSenha;

    @FXML
    protected void setSenha(MouseEvent e) throws Exception 
    {
        Context ctx = new Context();
        ctx.begin();
        
        var users = ctx.find(User.class,
                "SELECT u FROM User u WHERE u.name = :arg0",
                tfUsuario.getText());

        if (users.size() == 0) {
            Alert alert = new Alert(
                    AlertType.ERROR,
                    "Usuário não está cadastrado!",
                    ButtonType.OK);
            alert.showAndWait();
            return;
        }

        if(!tfNewSenha.getText().equals(tfConfirmSenha.getText())) {
            Alert alert = new Alert(AlertType.ERROR, "As senhas informadas são diferentes", ButtonType.OK);
            alert.showAndWait();
            return;
        }

        users.get(0).setPassword(tfConfirmSenha.getText());
        modalSenha.setVisible(false);
        
        ctx.update(users.get(0));
        ctx.commit();
    }

    @FXML
    protected void cancelarSenha(MouseEvent e) throws Exception 
    {
        modalSenha.setVisible(false);
        campoInfo.setVisible(true);
    }

    @FXML
    protected void sair(MouseEvent e) throws Exception {
        Stage crrStage = (Stage) btSair
                .getScene()
                .getWindow();

        Scene newScene = LoginSceneController.CreateScene();
        crrStage.setScene(newScene);
    }

    @FXML
    private void home(MouseEvent e) throws Exception {
        Stage crrStage = (Stage) mnHome
                .getScene()
                .getWindow();

        Scene newScene = HomeController.CreateScene(user);
        crrStage.setScene(newScene);
    }

    @FXML
    private void updatePerfil(MouseEvent e) throws Exception {
        Stage crrStage = (Stage) mnPerfil
                .getScene()
                .getWindow();

        Scene newScene = PerfilController.CreateScene(this.user);
        crrStage.setScene(newScene);
    }
}