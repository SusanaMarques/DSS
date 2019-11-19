package sample;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;


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
     * método que trata do evento: clique no botão convidado na página inicial
     * @param evento 
     */
    @FXML
    private void handleButtonAction_Conv(ActionEvent event) {
        this.model.setUserT(0);
        this.view.printPage((Node) event.getSource(),"convidado");

    }

    /**
     * método que trata do evento clique: no botão admin na página inicial
     * @param evento
     */
    @FXML
    private void handleButtonAction_Admin(ActionEvent event) {

        this.model.setUserT(1);
        this.view.printPage((Node) event.getSource(),"login");


    }

    /**
     * método que trata do evento: clique no botão registado na página inicial
     * @param evento
     */
    @FXML
    private void handleButtonAction_Reg(ActionEvent event) {
        this.model.setUserT(2);
        this.view.printPage((Node) event.getSource(),"login");


    }


    /**
     * método que trata do evento: clique no botão logout na página inicial do convidado
     * @param evento
     */
    @FXML
    private void handleButtonAction_logout_conv(ActionEvent event) {
        this.view.printPage((Node) event.getSource(),"mediacenter");

    }


    /**
     * método que trata do evento: clique no botão logout na página inicial do admin
     * @param evento
     */
    @FXML
    private void handleButtonAction_logout_admin(ActionEvent event) {
        this.view.printPage((Node) event.getSource(),"mediacenter");
    }

    /**
     * método que trata do evento: clique no botão logout na página inicial do convidado
     * @param evento
     */
    @FXML
    private void handleButtonAction_logout_registado(ActionEvent event) {
        this.view.printPage((Node) event.getSource(),"mediacenter");
    }


    /**
     * método que trata do evento: clique no botão login
     * @param evento
     */
    @FXML
    private void handleButtonAction_Login(ActionEvent event) {
        if(!this.model.checkLogin()) this.view.printPage((Node) event.getSource(),"Erro_Credenciais");
        else{

        if (this.model.getUserT() == 1) this.view.printPage((Node) event.getSource(),"Utilizador_Registado");
            else{this.view.printPage((Node) event.getSource(),"Administrador");}
    }
    }


    /**
     * método que trata do evento: clique no botão "retroceder" na página do login
     * @param evento
     */
    @FXML
    private void handleButtonAction_goback(ActionEvent event) {
        this.view.printPage((Node) event.getSource(),"mediacenter");
    }


    @Override
    @FXML
    public void initialize(URL url, ResourceBundle rb)
    {
         setModel(new Model());
         setView(new View());
    }

}
