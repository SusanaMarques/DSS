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


    @FXML
private void handleButtonAction_Main(ActionEvent event)
{
    try {
        Button tempButton = (Button) event.getSource();
        switch (tempButton.getId()) {

            case ("conv"):
            Parent inic_c_Parent = FXMLLoader.load(getClass().getResource("inic_conv.fxml"));
            Scene inic_c_Scene = new Scene(inic_c_Parent);


            //this line gets stage information
            Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
            window.setScene(inic_c_Scene);
            window.show();
            break;

            case ("admin"):

                Parent login_Parent = FXMLLoader.load(getClass().getResource("login.fxml"));
                Scene login_Scene = new Scene(login_Parent);


                //this line gets stage information
                Stage windoww = (Stage) ((Node) event.getSource()).getScene().getWindow();
                windoww.setScene(login_Scene);
                windoww.show();
            break;

            case ("reg"):
                Parent loginn_Parent = FXMLLoader.load(getClass().getResource("login.fxml"));
                Scene loginn_Scene = new Scene(loginn_Parent);


                //this line gets stage information
                Stage windowww = (Stage) ((Node) event.getSource()).getScene().getWindow();
                windowww.setScene(loginn_Scene);
                windowww.show();
                break;

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
