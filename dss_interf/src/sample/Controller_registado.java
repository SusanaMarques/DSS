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

public class Controller_registado implements Initializable {

    @FXML
    private void handleButtonAction_geral(ActionEvent event)
    {

        try {
            Button tempButton = (Button) event.getSource();
            switch (tempButton.getId()) {

                case ("geral"):


                    Parent inic_c_Parent = FXMLLoader.load(getClass().getResource("playlist.fxml"));
                    Scene inic_c_Scene = new Scene(inic_c_Parent);


                    //this line gets stage information
                    Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    window.setScene(inic_c_Scene);
                    window.show();
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