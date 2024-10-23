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

public class CadastroSceneController {
    
    public static Scene CreateScene() throws Exception {
        URL sceneUrl = LoginSceneController.class
                .getResource("CadastroSceneController.fxml");
        Parent root = FXMLLoader.load(sceneUrl);
        Scene scene = new Scene(root);

        return scene;
    }

    @FXML
    protected TextField tfLogin;

    @FXML
    protected PasswordField tfPassHide;

    @FXML
    protected TextField tfPassShow;

    @FXML
    protected CheckBox cbPass;

    @FXML
    protected Button btCadastro;

    @FXML
    protected Button btCancelar;

    @FXML
    protected void cadastro(MouseEvent e) throws Exception {    

        Context ctx = new Context();
        
        var users = ctx.find(User.class,
                "SELECT u FROM User u WHERE u.name = :arg0",
                tfLogin.getText());

        if (users.size() == 1) {
            Alert alert = new Alert(
                    AlertType.ERROR,
                    "Usuário já cadastrado!",
                    ButtonType.OK);
            alert.showAndWait();
            return;
        }
        else {
            User user = new User();
            user.setName(tfLogin.getText());
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
