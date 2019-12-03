package Presentation;
import Business.MC;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.FileChooser;
import javafx.stage.Popup;

import java.io.File;
import java.io.IOException;

public class Controller_Regist {

    private MC model;
    private View view;
    private MediaPlayer player ;
    private Media media;


    public void setM(MC m){
        this.model=m;

    }

    public void setV(View v){
        this.view=v;

    }


    /**
     * método que trata do evento: clique no botão de uma música
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


        //set model e view do Player
        Player pl = l.getController();
        pl.setM(model);
        pl.setV(view);
        pl.setMPlayer(player);
        pl.setText(file);
        pl.sett();

        this.view.printPage((Node) event.getSource(),root);

    }



    /**
     * método que trata do evento: upload de conteúdo
     * @param event
     */
    @FXML
    private void handleButtonAction_upload(ActionEvent event) throws IOException {
        Node node = (Node) event.getSource();
        FileChooser fileChooser;
        fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(node.getScene().getWindow());
      //  model.uploadConteudo(file);
        FXMLLoader l=new FXMLLoader(getClass().getResource( "Upload_Sucesso.fxml"));
        Parent root = l.load();
        Controller_upload up = l.getController();
        up.setM(model);
        up.setV(view);
        this.view.printPage((Node) event.getSource(),root);
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
