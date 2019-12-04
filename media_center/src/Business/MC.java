package Business;

import javafx.collections.MapChangeListener;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.net.MalformedURLException;
import java.time.Duration;
import java.util.StringTokenizer;


public class MC {
    private GestaoConteudo gc = new GestaoConteudo();
    private GestaoUtilizador gu = new GestaoUtilizador();
    private int idUtilizadorAtual;
    private int idType;
    private String album;
    private String artist;
    private String title;
    private String categoria;
    private double duracao=0.0;
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
        System.out.println(p);
        StringTokenizer tokens = new StringTokenizer( p,".");
        tokens.nextToken();
        String type = tokens.nextToken();
        System.out.println(type);
        char t;
        Conteudo c;

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
        if (type.equals("mp3")){t='m'; c = new Musica(p.hashCode(), title, artist, duracao, "mp3", categoria);}
        else if (type.equals("mp4")){t='v'; c = new Video();}
        else throw new FormatoDesconhecidoException();

        if(gc.verificaDuplicacoes(c,t)) throw new ConteudoDuplicadoException();




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
                duracao = (double) ((Duration) value ).toMillis();
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


