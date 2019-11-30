package sample;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;

public class Controller_Regist {

    private Model model;
    private View view;
    private MediaPlayer player ;
    private Media media;


    public void setM(Model m){
        this.model=m;

    }

    public void setV(View v){
        this.view=v;

    }


    /**
     * método que trata do evento: clique no botão de uma música na listView do convidado
     * este método inicia o player
     * @param event
     */
    @FXML
    private void handleButtonAction_Reproduzir(ActionEvent event) throws IOException {

        Node node = (Node) event.getSource();
        FileChooser fileChooser;


        fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(node.getScene().getWindow());

        //inicialização do player
        media = new Media(file.toURI().toURL().toExternalForm());
        player = new MediaPlayer(media);

        player.play();

        FXMLLoader l=new FXMLLoader(getClass().getResource( "player.fxml"));
        Parent root = l.load();

        this.view.printPage((Node) event.getSource(),root);

        //set model e view do Player
        Player pl = l.getController();
        pl.setM(model);
        pl.setV(view);
        pl.setMPlayer(player);
        pl.setText(file);
        pl.sett();

    }

    /**
     * método que trata do evento: clique no botão logout na página inicial do registado
     * @param event
     */
    @FXML
    private void handleButtonAction_logout_registado(ActionEvent event) throws IOException {
        FXMLLoader l=new FXMLLoader(getClass().getResource( "mediacenter.fxml"));
        Parent root = l.load();
        this.view.printPage((Node) event.getSource(),root);
    }

    @FXML private void initialize () {

    }


}
