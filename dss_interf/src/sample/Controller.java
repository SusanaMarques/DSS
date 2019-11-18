package sample;



import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;



public class Controller implements Initializable
{

    private Model model;
    private View view;


    //Setters
    public void setModel(Model m){
        this.model=m;
    }

    public void setView(View v){
        this.view=v;
    }


    /**
     * método que trata do evento clique no botão convidado
     * @param evento 
     */
    @FXML
    private void handleButtonAction_Conv(ActionEvent event) {
        this.model.setUserT(0);
        this.view.printPage((Node) event.getSource(),"convidado");

    }

    @FXML
    private void handleButtonAction_Admin(ActionEvent event) {

        this.model.setUserT(1);
        this.view.printPage((Node) event.getSource(),"login");


    }


    //
    @FXML
    private void handleButtonAction_logout_conv(ActionEvent event) {
        this.model.setUserT(0);
        this.view.printPage((Node) event.getSource(),"mediacenter");

        //free de todas as coisas
    }


    //
    @FXML
    private void handleButtonAction_logout_admin(ActionEvent event) {
        this.model.setUserT(0);
        this.view.printPage((Node) event.getSource(),"mediacenter");

        //free de todas as coisas
    }

    //
    @FXML
    private void handleButtonAction_logout_registado(ActionEvent event) {
        this.model.setUserT(0);
        this.view.printPage((Node) event.getSource(),"mediacenter");

        //free de todas as coisas
    }



    @FXML
    private void handleButtonAction_Reg(ActionEvent event) {
        this.model.setUserT(2);
        this.view.printPage((Node) event.getSource(),"login");


    }

    @FXML
    private void handleButtonAction_Login(ActionEvent event) {
        if(!this.model.checkLogin()) this.view.printPage((Node) event.getSource(),"Erro_Credenciais");
        else{

        if (this.model.getUserT() == 1) this.view.printPage((Node) event.getSource(),"Utilizador_Registado");
            else{this.view.printPage((Node) event.getSource(),"Administrador");}
    }
    }


    @Override
    @FXML
    public void initialize(URL url, ResourceBundle rb)
    {
         setModel(new Model());
         setView(new View());
    }

}
