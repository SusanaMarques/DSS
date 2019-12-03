package Presentation;


import Business.MC;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.TextField;

import java.io.IOException;

public class Controller_login {

    private MC model;
    private View view;
    private String email;
    private String pass;


    public void setModell(MC m){
        this.model=m;

    }

    public void setVieww(View v){
        this.view=v;

    }

    /**
     * método que trata do evento: insere mail
     * @param event
     */
    @FXML
    private void handleButtonAction_Mail(ActionEvent event) throws IOException {
        email =((TextField) event.getSource()).getText();
        }

    @FXML
    private void handleButtonAction_Pass(ActionEvent event) throws IOException {
        pass =((TextField) event.getSource()).getText();
    }



    /**
     * método que trata do evento: clique no botão login
     * @param event
     */
    @FXML
    private void handleButtonAction_Login(ActionEvent event) throws IOException {
         if(!this.model.iniciarSessao(email, pass)) {  FXMLLoader l=new FXMLLoader(getClass().getResource( "Erro_Credenciais.fxml"));
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
                FXMLLoader l=new FXMLLoader(getClass().getResource( "Administrador.fxml"));
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
