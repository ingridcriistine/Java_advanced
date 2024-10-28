package com.desktopapp;
import com.desktopapp.model.Mensagem;
import com.desktopapp.model.User;

import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class ButtonsTable extends Mensagem {
    private Button btDelete;
    private Button btVerMensagem;

    public ButtonsTable (Mensagem mensagem, TableView<ButtonsTable> tbMensagem, PaginaInicialController controller, User user) {

        this.setId(mensagem.getId());
        this.setTitulo(mensagem.getTitulo());
        this.setOrigem(mensagem.getOrigem());

        this.btVerMensagem = new Button();
        this.btVerMensagem.setText("Abrir");
        this.btVerMensagem.setOnAction((ActionEvent event) -> {
            try {
                var crrStage = (Stage) btVerMensagem
                    .getScene().getWindow();
                crrStage.close();
                var stage = new Stage();
                Scene scene;
                scene = MensagemExpandidaController.CreateScene(mensagem, user);
                stage.setScene(scene);
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        this.btDelete = new Button();
        this.btDelete.setText("❌");
        this.btDelete.setOnAction((ActionEvent event) -> {
            Context ctx = new Context();
            ctx.begin();
            ctx.delete(mensagem);
            ctx.commit();

            tbMensagem.setItems(controller.listaMensagens());
        });
    }

    public Button getBtDelete() {
        return btDelete;
    }

    public void setBtDelete(Button btDelete) {
        this.btDelete = btDelete;
    }

    public Button getBtVerMensagem() {
        return btVerMensagem;
    }
    
    public void setBtVerMensagem(Button btVerMensagem) {
        this.btVerMensagem = btVerMensagem;
    }
}






