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

public class Controller_login implements Initializable {



    Utilizador a = new Utilizador(2);

    @FXML
    private void handleButtonAction_login(ActionEvent event)
    {


        try {
            Button tempButton = (Button) event.getSource();
            switch (tempButton.getId()) {

                case ("login"):

                    if(a.getDesignação()==2) {

                        Parent inic_c_Parent = FXMLLoader.load(getClass().getResource("Utilizador_Registado.fxml"));
                        Scene inic_c_Scene = new Scene(inic_c_Parent);


                        //this line gets stage information
                        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        window.setScene(inic_c_Scene);
                        window.show();
                        break;
                    }

                    else {
                        Parent inic_c_Parent = FXMLLoader.load(getClass().getResource("Administrador.fxml"));
                        Scene inic_c_Scene = new Scene(inic_c_Parent);


                        //this line gets stage information
                        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        window.setScene(inic_c_Scene);
                        window.show();
                    }


            }
        }
        catch (IOException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle rb)
    {

    }







}
