package sample;


import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


public class View {

    public View() {
    }

    public void printPage(Node node, String id) {
        FXMLLoader l;
        try {
            l=new FXMLLoader(getClass().getResource(id + ".fxml"));


            Scene inic_c_Scene = new Scene (l.load());
            //this line gets stage information
            Stage window = (Stage) node.getScene().getWindow();
            window.setScene(inic_c_Scene);
            window.show();
        }
        catch (IOException exception) {
        }


    }
}