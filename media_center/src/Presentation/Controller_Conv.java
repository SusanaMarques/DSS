package Presentation;
import Business.MC;
import Business.Musica;

import Business.Video;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.FileChooser;
import javafx.util.Callback;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;

public class Controller_Conv {

    private int i=1;
    private View view = new View();
    private MC model = new MC();


    @FXML
    private TableView<Musica> table1;


    @FXML
    private TableColumn<Musica, String> nome_m;

    @FXML
    private TableColumn<Musica, String> artista;

    @FXML
    private TableColumn<Musica, String> cat_m;

    @FXML
    private TableView<Video> table2;


    @FXML
    private TableColumn<Video, String> nome_v;

    @FXML
    private TableColumn<Video, String > realizador;

    @FXML
    private TableColumn<Video, String> cat_v;




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

        nome_m.setCellValueFactory( new PropertyValueFactory<>("nome"));
        artista.setCellValueFactory( new PropertyValueFactory<>("artista"));
        cat_m.setCellValueFactory( new PropertyValueFactory<>("categoria"));



        nome_v.setCellValueFactory( new PropertyValueFactory<>("nome"));
        realizador.setCellValueFactory( new PropertyValueFactory<>("realizador"));
        cat_v.setCellValueFactory( new PropertyValueFactory<>("categoria"));

        Set<Musica> mus=  model.showMusicas();

        Set<Video> vid = model.showVideos();
        ObservableList<Musica> m = FXCollections.observableArrayList();
        m.addAll(mus);
        table1.getItems().setAll(m);


        ObservableList<Video> v = FXCollections.observableArrayList();
        v.addAll(vid);
        table2.getItems().setAll(v);






        }

}
