package Presentation;

import Business.MC;
import Business.Musica;
import Business.Video;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.IOException;

public class Controller_opt {

    private int idd; // música=1; video=2;
    private MC model;
    private View view;
    private Musica m;
    private Video v;



    public void setIdd(int a){
        this.idd=a;
    }

    public void setM(MC m){
        this.model=m;

    }

    public void setV(View v){
        this.view=v;

    }

    public void setMusica(Musica mm){
        this.m=mm;
    }

    public void setVideo(Video vv){
        this.v=vv;
    }


    @FXML
    private void handleButtonAction_Reproduzir(ActionEvent event) throws IOException {

        if(idd==1) {

            //inicialização do player
            Media media = new Media(model.getPath('m', m.getId()));
            MediaPlayer player = new MediaPlayer(media);
            player.play();

            FXMLLoader l = new FXMLLoader(getClass().getResource("player.fxml"));
            Parent root = l.load();
            this.view.printPage((Node) event.getSource(), root);


            //set model e view do Player
            Player pl = l.getController();
            pl.setV(view);
            pl.sett();
            pl.setMPlayer(player);
            pl.setText(m.getNome());
            pl.setModel(model);
            pl.setMus(model.showMusicas());
            pl.setId(m.getId());
            pl.inic();
        }

        if(idd==2) {
            //inicialização do player
            Media media = new Media(model.getPath('v',v.getId()));
            MediaPlayer player = new MediaPlayer(media);
            player.play();

            FXMLLoader l=new FXMLLoader(getClass().getResource( "player_video.fxml"));
            Parent root = l.load();
            this.view.printPage((Node) event.getSource(),root);


            //set model e view do Player_video
            PLayer_video pl = l.getController();
            pl.setV(view);
            pl.setMPlayer(player);
            pl.sett();
            pl.setModel(model);
            pl.setVid(model.showVideos());
            pl.setId(v.getId());
        }


    }

    @FXML
    private void handleButtonAction_goback(ActionEvent event) throws IOException {
        FXMLLoader l=new FXMLLoader(getClass().getResource( "geral.fxml"));
        Parent root = l.load();
        this.view.printPage((Node) event.getSource(),root);

        Controller_geral control = l.getController();
        control.setM(model);
        control.setV(view);

    }


    @FXML
    private void handleButtonAction_alteracat(ActionEvent event) throws IOException {

        if(idd==1) {

            FXMLLoader l = new FXMLLoader(getClass().getResource("Alterar_Genero.fxml"));
            Parent root = l.load();
            this.view.printPage((Node) event.getSource(), root);
            Controller_genero pl = l.getController();
            pl.setV(view);
            pl.setM(model);
            pl.setIdd(1);
            pl.setMusica(m);
            pl.setText(m.getCategoria());
            pl.setChoice(FXCollections.observableArrayList(
                    "Pop", "Rock", "Jazz", "Country", "Classic","Punk","R&B"));
        }

        if(idd==2){
            FXMLLoader l = new FXMLLoader(getClass().getResource("Alterar_Genero.fxml"));
            Parent root = l.load();
            this.view.printPage((Node) event.getSource(), root);
            Controller_genero pl = l.getController();
            pl.setV(view);
            pl.setM(model);
            pl.setIdd(2);
            pl.setVideo(v);
            pl.setText(v.getCategoria());
            pl.setChoice(FXCollections.observableArrayList(
                    "Pop", "Rock", "Jazz", "Country", "Season1","Season2","Season3"));
        }

    }

}
