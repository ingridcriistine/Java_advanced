package com.desktopapp;

import java.net.URL;

import com.desktopapp.model.Mensagem;
import com.desktopapp.model.User;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MensagemExpandidaController {
    public static Scene CreateScene(Mensagem mensagem, User user) throws Exception {

        
        URL sceneUrl = MensagemExpandidaController.class
        .getResource("MensagemExpandida.fxml");
        FXMLLoader loader = new FXMLLoader(sceneUrl);
        Parent root = loader.load();
        Scene scene = new Scene(root);
        
        MensagemExpandidaController controller = loader.getController();
        controller.setUser(user);
        controller.txtTitulo.setText(mensagem.getTitulo());
        controller.txtMensagem.setText(mensagem.getTexto());
        controller.txtPessoa.setText(mensagem.getOrigem());
        controller.txtData.setText(mensagem.getData());
        controller.setMensagem(mensagem);
        
        return scene;
    }

    protected Mensagem mensagem;
    
    protected User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Mensagem getMensagem() {
        return mensagem;
    }

    public void setMensagem(Mensagem mensagem) {
        this.mensagem = mensagem;
    }

    @FXML
    protected Text txtTitulo;

    @FXML
    protected TextArea txtMensagem;

    @FXML
    protected Text txtData;

    @FXML
    protected Text txtPessoa;

    @FXML
    protected Button btVoltar;

    // @FXML
    // protected Button btResponder;

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

    // @FXML
    // protected void responder(MouseEvent e) throws Exception {    

    //     var crrStage = (Stage) btResponder
    //             .getScene().getWindow();
    //     crrStage.close();
    //     var stage = new Stage();
    //     var scene = PaginaInicialController.CreateScene(user);
    //     stage.setScene(scene);
    //     stage.show();
    // }

    
}
