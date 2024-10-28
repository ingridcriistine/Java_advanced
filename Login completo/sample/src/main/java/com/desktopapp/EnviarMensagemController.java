package com.desktopapp;

import java.net.URL;
import java.util.List;

import com.desktopapp.model.Mensagem;
import com.desktopapp.model.User;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class EnviarMensagemController {
    public static Scene CreateScene(User user) throws Exception {

        URL sceneUrl = EnviarMensagemController.class
        .getResource("EnviarMensagem.fxml");
        FXMLLoader loader = new FXMLLoader(sceneUrl);
        Parent root = loader.load();
        Scene scene = new Scene(root);

        EnviarMensagemController controller = loader.getController();
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
    protected TextField tfOrigem;

    @FXML
    protected TextField tfTitulo;

    @FXML
    protected TextField tfData;

    @FXML
    protected TextArea txtMensagem;

    @FXML
    protected Button btVoltar;

    @FXML
    protected Button btEnviar;

    @FXML
    protected void voltar(MouseEvent e) throws Exception {    

        var crrStage = (Stage) btVoltar
                .getScene().getWindow();
        crrStage.close();
        var stage = new Stage();
        var scene = PaginaInicialController.CreateScene(user);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    protected void enviar(MouseEvent e) throws Exception {    

        Context ctx = new Context();
        
        List<User> userDestino = ctx.find(User.class, "select u from User u where u.email = :arg0", tfOrigem.getText()); 
        
        if (userDestino.isEmpty()) {
            Alert alert = new Alert(
                AlertType.ERROR,
                "Usuário não encontrado!",
                ButtonType.OK);
                alert.showAndWait();
                return;
            }
        
        Mensagem newMensagem = new Mensagem();
        newMensagem.setTitulo(tfTitulo.getText());
        newMensagem.setData(tfData.getText());
        newMensagem.setOrigem(user.getEmail());
        newMensagem.setDestino(userDestino.get(0));
        newMensagem.setTexto(txtMensagem.getText());

        ctx.begin();
        ctx.persist(newMensagem);
        ctx.commit();
        
        Alert alert = new Alert(
                AlertType.INFORMATION,
                "Mensagem enviada com sucesso!",
                ButtonType.OK);
        alert.showAndWait();

        var crrStage = (Stage) btEnviar
                .getScene().getWindow();
        crrStage.close();
        var stage = new Stage();
        var scene = PaginaInicialController.CreateScene(user);
        stage.setScene(scene);
        stage.show();


    }

    
}
