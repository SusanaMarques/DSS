package Business;

import javafx.collections.MapChangeListener;
import javafx.scene.input.KeyCode;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.StringTokenizer;


public class MC {
    /** Instancia da gestao de conteudo**/
    private GestaoConteudo gc = new GestaoConteudo();
    /** Instancia da gestao de utilizador**/
    private GestaoUtilizador gu = new GestaoUtilizador();
    /** Id do utilizador atual**/
    private int idUtilizadorAtual;
    /** Identificador do tipo de utilizador
     * Administrador : 1
     * Utilizador Registado : 2
     **/
    private int idType;
    /** Metadados de conteudo a fazer upload **/
    private volatile String album;
    private volatile String artist;
    private volatile String title;
    private volatile String categoria;
    private volatile double duracao=0.0;

    /**Construtor do MC**/
    public MC() {
    }
    /**Metodo de iniciar sessão
     * @param mail Email do utilizador a autenticar
     * @param pass Password do utilizador a autenticar **/

    public void iniciarSessao(String mail, String pass) throws CredenciaisInvalidasException {
        if(mail == null||pass == null || mail.isEmpty() || pass.isEmpty()) throw new CredenciaisInvalidasException();
        gu.iniciarSessao(mail, pass, idType);

    }
    /**Metodo de terminar sessão**/
    public void terminarSessao() {
        idUtilizadorAtual = -1;
        idType = -1;
    }

    /**Metodo de upload de conteudo
     * @param p Path do conteudo a fazer upload**/
    public void uploadConteudo(String p) throws FormatoDesconhecidoException, IOException, ConteudoDuplicadoException {
        System.out.println(p);
        StringTokenizer tokens = new StringTokenizer( p,".");
        tokens.nextToken();
        String type = tokens.nextToken();
        UtilizadorRegistado u =(UtilizadorRegistado) gu.getUser(idUtilizadorAtual,idType);
        char t;
        Conteudo c;
        //Verificar formato
        if (type.equals("mp3")){t='m'; c = new Musica(p.hashCode(), title, artist, duracao, "mp3", categoria);}
        else if (type.equals("mp4")){t='v'; c = new Video(); c.setId(p.hashCode()); }
        else throw new FormatoDesconhecidoException();

        /*--------------------------------------------------------------
        //Extrair metadados
        Media media = new Media(p);
        MediaPlayer player = new MediaPlayer(media);
        media.getMetadata().addListener((MapChangeListener<String, Object>) change -> {
                    if (change.wasAdded()) listnerHandle(change.getKey(),change.getValueAdded());
                }
        );
        *///--------------------------------------------------------------
        //Salvaguardar metadados
        if(title== null) title= "Toxic";
        if(artist == null)artist = "Britney Spears";
        if(categoria== null) categoria = "Pop";
        if(album== null) album = "In The Zone";

        //Verificar duplicaçoes
        if(gc.verificaDuplicacoes(c,t)) throw new ConteudoDuplicadoException();
        //Adicionar a bibliotecas
        gc.uploadConteudo(c,t,u);
        gu.uploadConteudo(c, t,u);
        //Path building
        Path fileDB=Paths.get( (new File("").getAbsolutePath())+"baseDeDados/Biblioteca"+c.getId());
        Path temp = Files.move(Paths.get(p), fileDB);
        if(temp == null) throw new IOException();




    }
    /**Metodo de extração de metadados
     * @param key chave descritora do tipo de metadado observado
     * @param value valor do metadado **/
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
    /**Metodo que altera o tipo do utilizador a usar o sistema
     * @param idT Tipo do utilizador**/
    public void setUserT(int idT) {
        idType = idT;
    }

    /**Metodo que altera o tipo do utilizador a usar o sistema
     * @return   Tipo do utilizador**/
    public int getUserT() {
        return idType;
    }
}


