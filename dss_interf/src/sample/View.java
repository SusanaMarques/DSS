package sample;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class View {

    public View() {
    }

    public void printPage(String id, Node node ) {
        try {



            Parent inic_c_Parent = FXMLLoader.load(getClass().getResource(id + ".fxml"));
            Scene inic_c_Scene = new Scene(inic_c_Parent);

            //this line gets stage information
            Stage window = (Stage) node.getScene().getWindow();
            window.setScene(inic_c_Scene);
            window.show();
        } catch (IOException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}