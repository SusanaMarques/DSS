package Business;

import javafx.collections.MapChangeListener;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.FileChooser;

import java.io.*;
import java.net.MalformedURLException;
import java.time.Duration;

import javax.sound.sampled.UnsupportedAudioFileException;

import static javax.sound.sampled.AudioSystem.getAudioFileFormat;

public class MC {
    private GestaoConteudo gc = new GestaoConteudo();
    private GestaoUtilizador gu = new GestaoUtilizador();
    private int idUtilizadorAtual;
    private int idType;
    private String album;
    private String artist;
    private String title;
    private String categoria;
    private int duracao=0;
    //tipo de utilizador: administrador:1; registado:2


    public MC() {
    }

    public void iniciarSessao(String mail, String pass) throws CredenciaisInvalidasException {

            gu.iniciarSessao(mail, pass, idType);

    }

    public void terminarSessao() {
        idUtilizadorAtual = -1;
        idType = -1;
    }

    public void uploadConteudo(String p) throws FormatoDesconhecidoException, MalformedURLException, ConteudoDuplicadoException {
        String path[] = f.toURI().toURL().toExternalForm().split(".", 2);
        char type;
        Conteudo c;
        System.out.println(p);

        //----------------------------------------------------------------
        Media media = new Media(p);
        MediaPlayer player = new MediaPlayer(media);
        media.getMetadata().addListener((MapChangeListener<String, Object>) change -> {
            if (change.wasAdded()) listnerHandle(change.getKey(),change.getValueAdded());
            }
        );




        //---------------------------------------------------------value------
        if(title== null) title= "Toxic";
        if(artist == null)artist = "Britney Spears";
        if(categoria== null) categoria = "Pop";
        if(album== null) album = "In The Zone";

        if (path[1].equals("mp3")){type='m'; c = new Musica(p.hashCode(), title, artist, duracao, "mp3", categoria);}
       else if (path[1].equals("mp4")){type='v'; c = new Video();}
       else throw new FormatoDesconhecidoException();

        if(!gc.verificaDuplicacoes(c,type)) throw new ConteudoDuplicadoException();




    }
    private void listnerHandle(String key, Object value){
            switch (key) {
                case "album":
                    album = (String) value;
                    break;

                case "artist":
                    artist = (String) value;
                    break;

                case "title":
                    title = (String) value;
                    break;
                case "genre":
                    categoria = (String) value;
                    break;
                case "duration":
                    duracao = (int) ((Duration) value ).toMillis();
                    break;

            }
    }



    public void setUserT(int idT) {
        idType = idT;
    }

    public int getUserT() {
        return idType;
    }
}


