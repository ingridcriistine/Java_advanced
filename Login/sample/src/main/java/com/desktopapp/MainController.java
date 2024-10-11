package com.desktopapp;

import java.net.URL;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import java.util.ResourceBundle;

public class MainController {

    public static Scene CreateScene(Integer id) throws Exception
    {
        URL sceneUrl = MainController.class
        .getResource("MainScreen.fxml");
        FXMLLoader loader = new FXMLLoader(sceneUrl);
        Parent root = loader.load();
        Scene scene = new Scene(root);
        
        MainController controller = loader.getController();
        controller.setId(id);
        loader.setController(controller);

        return scene;
    }

    // public MainController(Integer id) {
    //     this.id = id;
    // }

    private Integer id;
    public void setId(Integer id) {
        this.id = id;
    }

    @FXML
    protected void initialize(URL location, ResourceBundle Resources) {
        // this.btLogin.setOnAction(e -> {
        //     System.err.println();
        // });
        System.out.println("oi");
        this.btLogin.setText(this.id.toString());
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
    protected TextField tfNewSenha;

    @FXML
    protected TextField tfConfirmSenha;

    @FXML
    protected Button btSetSenha;

    @FXML
    protected Button btCancelar;
    
    String newSenha;

    @FXML
    protected void setSenha(MouseEvent e) throws Exception 
    {
        if(tfNewSenha.getText().equals(tfConfirmSenha.getText())) {
            newSenha = tfNewSenha.getText();
            modalSenha.setVisible(false);
        }
        else  {
            Alert alert = new Alert(AlertType.ERROR, "As senhas informadas são diferentes", ButtonType.OK);
            alert.showAndWait();
        }
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
    protected void botao(MouseEvent e) throws Exception
    {
        Alert alert = new Alert(AlertType.ERROR, "Email e/ou senha inválido(s)", ButtonType.OK);
        
        if(tfLogin.getText().equals("ingrid") && ((tfPassHide.getText().equals("ingrid123") || tfPassShow.getText().equals("ingrid123")) || (tfPassHide.getText().equals(newSenha) || tfPassShow.getText().equals(newSenha)))) {
            Stage crrStage = (Stage)btLogin
                .getScene()
                .getWindow();
    
                crrStage.close();
                
                Stage newStage = new Stage();
                Scene newScene = HomeController.CreateScene();
                newStage.setScene(newScene);
                newStage.show();
    
                // crrStage.setScene(newScene);
        }
        else {
            alert.showAndWait();
            tfLogin.setStyle("-fx-border-color: red;");
            tfPassHide.setStyle("-fx-border-color: red;");
            tfPassShow.setStyle("-fx-border-color: red;");
            return;
        }
        
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
}