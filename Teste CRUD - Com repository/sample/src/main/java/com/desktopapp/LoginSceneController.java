package com.desktopapp;

import java.net.URL;

import com.desktopapp.model.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;

public class LoginSceneController {

    public static Scene CreateScene() throws Exception {
        URL sceneUrl = LoginSceneController.class
                .getResource("LoginSceneController.fxml");
        Parent root = FXMLLoader.load(sceneUrl);
        Scene scene = new Scene(root);

        return scene;
    }

    @FXML
    protected Button btLogin;

    @FXML
    protected TextField tfLogin;

    @FXML
    protected PasswordField tfPassHide;

    @FXML
    protected TextField tfPassShow;

    @FXML
    protected CheckBox cbPass;

    @FXML
    protected Pane modalSenha;

    @FXML
    protected TextField tfUsuario;

    @FXML
    protected TextField tfNewSenha;

    @FXML
    protected TextField tfConfirmSenha;

    @FXML
    protected Button btSetSenha;

    @FXML
    protected Button btCancelar;

    @FXML
    protected Button btCadastro;

    @FXML
    protected void botao(MouseEvent e) throws Exception {
        Context ctx = new Context();
        
        var users = ctx.find(User.class,
                "SELECT u FROM User u WHERE u.name = :arg0",
                tfLogin.getText());

        if (users.size() == 0) {
            Alert alert = new Alert(
                    AlertType.ERROR,
                    "Usuário não está cadastrado!",
                    ButtonType.OK);
            alert.showAndWait();
            return;
        }

        var user = users.get(0);

        if (!tfPassHide.getText().equals(user.getPassword())) {
            Alert alert = new Alert(
                    AlertType.ERROR,
                    "Senha incorreta!",
                    ButtonType.OK);
            alert.showAndWait();
            tfLogin.setStyle("-fx-border-color: red;");
            tfPassHide.setStyle("-fx-border-color: red;");
            tfPassShow.setStyle("-fx-border-color: red;");
            return;
        }

        var crrStage = (Stage) btLogin
                .getScene().getWindow();
        crrStage.close();
        var stage = new Stage();
        var scene = HomeController.CreateScene();
        stage.setScene(scene);
        stage.show();
    }

    boolean changed = false;

    @FXML
    protected void showPass(MouseEvent e) throws Exception
    {   
        if(!changed) {
            tfPassHide.setVisible(false);
            tfPassShow.setVisible(true);
            tfPassShow.setText(tfPassHide.getText());
            changed = true;
        } else {
            tfPassShow.setVisible(false);
            tfPassHide.setVisible(true);
            tfPassHide.setText(tfPassShow.getText());
            changed = false;
        }
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
    protected void showModalSenha(MouseEvent e) throws Exception 
    {
        modalSenha.setVisible(true);
    }

    @FXML
    protected void cancelarSenha(MouseEvent e) throws Exception 
    {
        modalSenha.setVisible(false);
    }

    @FXML
    protected void cadastro(MouseEvent e) throws Exception {    

        var crrStage = (Stage) btCadastro
                .getScene().getWindow();
        crrStage.close();
        var stage = new Stage();
        var scene = CadastroSceneController.CreateScene();
        stage.setScene(scene);
        stage.show();
    }
}