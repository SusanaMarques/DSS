package Presentation;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;

import java.io.IOException;

public class Controller_login {

    private Model model;
    private View view;


    public void setModell(Model m){
        this.model=m;

    }

    public void setVieww(View v){
        this.view=v;

    }

    /**
     * método que trata do evento: clique no botão login
     * @param event
     */
    @FXML
    private void handleButtonAction_Login(ActionEvent event) throws IOException {
         if(!this.model.checkLogin()) {  FXMLLoader l=new FXMLLoader(getClass().getResource( "Erro_Credenciais.fxml"));
            Parent root = l.load();
            this.view.printPage((Node) event.getSource(),root);
         }
        else{

            if (this.model.getUserT() == 2) {  FXMLLoader l=new FXMLLoader(getClass().getResource( "Utilizador_Registado.fxml"));
                Parent root = l.load();
                // set model e view do Controller_Regist
                Controller_Regist control = l.getController();
                control.setM(model);
                control.setV(view);

                this.view.printPage((Node) event.getSource(),root);}
            else{
                FXMLLoader l=new FXMLLoader(getClass().getResource( "administrador.fxml"));
                Parent root = l.load();

                // set model e view do Controller_Admin
                Controller_Admin control = l.getController();
                control.setM(model);
                control.setV(view);
                this.view.printPage((Node) event.getSource(),root);}
        }
    }

    /**
     * método que trata do evento: clique no botão "retroceder" na página do login
     * @param event
     */
    @FXML
    private void handleButtonAction_goback(ActionEvent event) throws IOException {
        FXMLLoader l=new FXMLLoader(getClass().getResource( "mediacenter.fxml"));
        Parent root = l.load();
        this.view.printPage((Node) event.getSource(),root);
    }



    @FXML private void initialize() {


    }









}
