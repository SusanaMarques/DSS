package sample;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;


public class Controller implements Initializable
{
    private Model model;
    private View view;
    private int buttonid;

    public Controller(){
        model=new Model();
        view=new View();
    }

    @FXML
    private void handleButtonAction_Conv(ActionEvent event) {

        view.printPage("convidado", (Node) event.getSource());
        model.setUserT(0);
    }

    @FXML
    private void handleButtonAction_Admin(ActionEvent event) {
        view.printPage("login", (Node) event.getSource());
        model.setUserT(1);

    }

    @FXML
    private void handleButtonAction_Reg(ActionEvent event) {
        view.printPage("login", (Node) event.getSource());
        model.setUserT(2);
    }

    @FXML
    private void handleButtonAction_Login(ActionEvent event) {
        if(!model.checkLogin()) view.printPage("Erro_Credenciais", (Node) event.getSource());
        else{

        if (model.getUserT() == 1) view.printPage("Utilizador_Registado", (Node) event.getSource());
            else{view.printPage("Administrador", (Node) event.getSource());}
    }
    }


    @Override
    public void initialize(URL url, ResourceBundle rb)
    {

    }

}
