package Presentation;
import Business.MC;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.text.Text;

import java.io.File;
import java.io.IOException;


public class Player {

    private MC model;
    private View view;
    private MediaPlayer player;

    @FXML
    private MediaView v;

    @FXML
    private Text t;

    public void setM(MC m) {
        this.model = m;

    }

    public void setV(View v) {
        this.view = v;

    }
    public void setMPlayer(MediaPlayer pl) {
        this.player = pl;
    }

    public void setText(File file){
        t.setText(file.getName());
    }

    public void sett() {
        v.setMediaPlayer(player);
    }



    @FXML
    private void handleButtonAction_buttonreproduz(ActionEvent e) {
        Button b = (Button) e.getSource();
        MediaPlayer.Status status = player.getStatus(); //get status of player

        if (status == MediaPlayer.Status.PLAYING) {
            //If the status is Video playing
            player.pause();
            b.setText(">");
        }

        if (status == MediaPlayer.Status.HALTED || status == MediaPlayer.Status.STOPPED || status == MediaPlayer.Status.PAUSED) {
            player.play(); //Start
            b.setText("||");
        }


    }

    @FXML
    //Providing functionality to time slider
    private void handleButtonAction_timeslider(MouseEvent e) {
        Slider time = (Slider) e.getSource();
        updatesValues(time);
        player.seek(player.getMedia().getDuration().multiply(time.getValue() / 100));

    }

    // providing functionality to volume slider
    @FXML
    private void handleButtonAction_volume(MouseEvent e) {
        Slider vol = (Slider) e.getSource();
        player.setVolume(vol.getValue() / 100); // It would set the volume

    }

    private void updatesValues(Slider time) {
        Platform.runLater(new Runnable() {
            public void run() {
                time.setValue(player.getCurrentTime().toMillis() / player.getTotalDuration().toMillis() * 100);
            }
        });
    }


    /**
     * método que trata do evento: clique no botão "retroceder" na página do player
     *
     * @param event
     */
    @FXML
    private void handleButtonAction_goback_player(ActionEvent event) throws IOException {
        MediaPlayer.Status status = player.getStatus(); //get status of player
        if (status == MediaPlayer.Status.PLAYING) {
            //If the status is Video playing
            player.pause();
        }

        if (this.model!= null) {
            FXMLLoader l = new FXMLLoader(getClass().getResource("Utilizador_Registado.fxml"));
            Parent root = l.load();

            // set model e view do Controller_Reg
            Controller_Regist control = l.getController();
            control.setM(model);
            control.setV(view);
            this.view.printPage((Node) event.getSource(), root);
        }
        else {
            //retorna página inicial do convidado: não necessitamos do admin uma vez que este nao acessa o player
            FXMLLoader l = new FXMLLoader(getClass().getResource("convidado.fxml"));
            Parent root = l.load();
            this.view.printPage((Node) event.getSource(), root);
        }
    }




    @FXML private void initialize () {

    }


}




