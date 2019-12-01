package Presentation;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;

public class Controller_Conv {

    private int i=1;
    private View view = new View();

    @FXML
    private ListView<String> list;

    private ObservableList<String> items = FXCollections.observableArrayList();



    /**
     * método que trata do evento: clique no botão logout na página inicial do convidado
     * @param event
     */
    @FXML
    private void handleButtonAction_logout_conv(ActionEvent event) throws IOException {
        FXMLLoader l=new FXMLLoader(getClass().getResource( "mediacenter.fxml"));
        Parent root = l.load();
        this.view.printPage((Node) event.getSource(),root);

    }


    /**
     * método que trata do evento: clique no botão de uma música na listView do convidado
     * este método inicia o player
     * @param event
     */
    @FXML
    private void handleButtonAction_Reproduzir(MouseEvent event) throws IOException {

        Node node = (Node) event.getSource();
        FileChooser fileChooser;


        fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(node.getScene().getWindow());

        //inicialização do player
        Media media = new Media(file.toURI().toURL().toExternalForm());
        MediaPlayer player = new MediaPlayer(media);
        player.play();

        FXMLLoader l=new FXMLLoader(getClass().getResource( "player.fxml"));
        Parent root = l.load();
        this.view.printPage((Node) event.getSource(),root);


        //set model e view do Player
        Player pl = l.getController();
        pl.setV(view);
        pl.setMPlayer(player);
        pl.setText(file);
        pl.sett();

    }


    /**
     * inicializa o Controller_conv já com a lista de id das músicas e vídeos
     */
    @FXML private void initialize () {
        list.setItems(items);

        while(i <= 9){
            items.add(i+".  "+"Música:                    " + "                            Artista:                    ");
            i++;
        }
        while(i <= 50 && i>9){
            items.add(i+". "+"Música:                    " + "                            Artista:                    ");
            i++;
        }
    }

}
