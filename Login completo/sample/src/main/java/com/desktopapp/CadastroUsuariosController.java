package com.desktopapp;

import java.net.URL;

import com.desktopapp.model.User;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class CadastroUsuariosController {
    
    public static Scene CreateScene() throws Exception {
        URL sceneUrl = LoginSceneController.class
                .getResource("CadastroUsuarios.fxml");
        Parent root = FXMLLoader.load(sceneUrl);
        Scene scene = new Scene(root);

        return scene;
    }

    @FXML
    protected TextField tfNome;

    @FXML
    protected TextField tfEmail;

    @FXML
    protected PasswordField tfPassHide;

    @FXML
    protected PasswordField tfPassHideConfirm;

    @FXML
    protected TextField tfPassShow;

    @FXML
    protected TextField tfPassShowConfirm;

    @FXML
    protected CheckBox cbPass;

    @FXML
    protected Button btCadastro;

    @FXML
    protected Button btCancelar;

    @FXML
    protected void cadastro(MouseEvent e) throws Exception {    

        Context ctx = new Context();
        
        var usersEmail = ctx.find(User.class,
                "SELECT u FROM User u WHERE u.email = :arg0",
                tfEmail.getText());

        var usersNome = ctx.find(User.class,
                "SELECT u FROM User u WHERE u.name = :arg0",
                tfNome.getText());

        if (usersEmail.size() == 1 || usersNome.size() == 1) {
            Alert alert = new Alert(
                    AlertType.ERROR,
                    "Usuário já cadastrado!",
                    ButtonType.OK);
            alert.showAndWait();
            return;
        }
        else {

            if(!tfPassHide.getText().equals(tfPassHideConfirm.getText())) {
                Alert alert = new Alert(AlertType.ERROR, "As senhas informadas são diferentes", ButtonType.OK);
                alert.showAndWait();
                tfPassHide.setStyle("-fx-border-color: red;");
                tfPassShow.setStyle("-fx-border-color: red;");
                tfPassHideConfirm.setStyle("-fx-border-color: red;");
                tfPassShowConfirm.setStyle("-fx-border-color: red;");
                return;
            }

            if(!MyValidator.ValidateEmail(tfEmail.getText())) {
                Alert alert = new Alert(AlertType.ERROR, "Email inválido! Seu email deve seguir o formato x@y.z", ButtonType.OK);
                alert.showAndWait();
                tfEmail.setStyle("-fx-border-color: red;");
                return;
            }

            if(!MyValidator.ValidatePassword(tfPassHide.getText())) {
                Alert alert = new Alert(AlertType.ERROR, "Senha fraca! Sua senha deve conter no mínimo 8 caracteres, sendo eles: caracteres especiais, letras e números.", ButtonType.OK);
                alert.showAndWait();
                tfPassHide.setStyle("-fx-border-color: red;");
                tfPassShow.setStyle("-fx-border-color: red;");
                tfPassHideConfirm.setStyle("-fx-border-color: red;");
                tfPassShowConfirm.setStyle("-fx-border-color: red;");
                return;
            }

            User user = new User();
            user.setName(tfNome.getText());
            user.setEmail(tfEmail.getText());
            user.setPassword(tfPassHide.getText());

            ctx.begin();
            ctx.persist(user);
            ctx.commit();
        }

        var crrStage = (Stage) btCadastro
                .getScene().getWindow();
        crrStage.close();
        var stage = new Stage();
        var scene = LoginSceneController.CreateScene();
        stage.setScene(scene);
        stage.show();

        Alert alert = new Alert(
                AlertType.INFORMATION,
                "Usuário cadastrado com sucesso!",
                ButtonType.OK);
        alert.showAndWait();
    }

    boolean changed = false;

    @FXML
    protected void showPass(MouseEvent e) throws Exception
    {   
        if(!changed) {
            tfPassHide.setVisible(false);
            tfPassHideConfirm.setVisible(false);
            tfPassShow.setVisible(true);
            tfPassShow.setText(tfPassHide.getText());
            tfPassShowConfirm.setVisible(true);
            tfPassShowConfirm.setText(tfPassHideConfirm.getText());
            changed = true;
        } else {
            tfPassShow.setVisible(false);
            tfPassShowConfirm.setVisible(false);
            tfPassHide.setVisible(true);
            tfPassHide.setText(tfPassShow.getText());
            tfPassHideConfirm.setVisible(true);
            tfPassHideConfirm.setText(tfPassShowConfirm.getText());
            changed = false;
        }
    }

    @FXML
    protected void cancelar(MouseEvent e) throws Exception {    

        var crrStage = (Stage) btCancelar
                .getScene().getWindow();
        crrStage.close();
        var stage = new Stage();
        var scene = LoginSceneController.CreateScene();
        stage.setScene(scene);
        stage.show();
    }
}
