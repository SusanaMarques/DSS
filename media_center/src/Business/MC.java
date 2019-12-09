package Business;

import javafx.collections.MapChangeListener;
import javafx.scene.input.KeyCode;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.AutoDetectParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.parser.mp3.ID3v1Handler;
import org.apache.tika.parser.mp3.ID3v24Handler;
import org.apache.tika.parser.mp3.ID3v2Frame;
import org.apache.tika.parser.mp3.Mp3Parser;
import org.apache.tika.parser.mp4.MP4Parser;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.SAXException;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.*;


public class MC
{
    /** Instancia da gestao de conteudo**/
    private GestaoConteudo gc = new GestaoConteudo();
    /** Instancia da gestao de utilizador**/
    private GestaoUtilizador gu = new GestaoUtilizador();
    /** Id do utilizador atual**/
    private int idUtilizadorAtual;
    /** Identificador do tipo de utilizador             Administrador : 1                Utilizador Registado : 2   **/
    private int idType;


    /** Construtor da classe MC sem parâmetros **/
    public MC() { }

    /** Método que inicia uma sessão
     * @param mail    Email do utilizador a autenticar
     * @param pass    Password do utilizador a autenticar
     */
    public void iniciarSessao(String mail, String pass) throws CredenciaisInvalidasException {
        if(mail == null||pass == null || mail.isEmpty() || pass.isEmpty()) throw new CredenciaisInvalidasException();
        gu.iniciarSessao(mail, pass, idType);
    }

    /** Método que  termina uma sessão **/
    public void terminarSessao() {
        idUtilizadorAtual = -1;
        idType = -1;
    }

    /** Método que faz o upload de conteudo
     * @param p   Path do conteudo a fazer upload
     */
    public void uploadConteudo(String p) throws FormatoDesconhecidoException, IOException, ConteudoDuplicadoException, URISyntaxException, TikaException, SAXException {
        StringTokenizer tokens = new StringTokenizer( p,".");
        String mp4Artist = tokens.nextToken();
        String type = tokens.nextToken();
        UtilizadorRegistado u =(UtilizadorRegistado) gu.getUser(idUtilizadorAtual,idType);
        char t;
        Conteudo c;



        //Extrair metadados
        String album;
        String artist;
        String title;
        String categoria;
        double duracao ;
        File origin = new File((new URI(p)).getPath());


        //Salvaguardar metadados

        //Verificar formato
        if (type.equals("mp3")){
            ID3v24Handler m = extrairMetaMp3(origin);
            duracao = getDuration(origin);
            title = m.getTitle();
            artist = m.getArtist();
            categoria = m.getGenre();
            album = m.getAlbum();

            if(title == null) title= "N/D";
            if(artist == null) artist = "N/D";
            if(categoria == null) categoria = "N/D";
            if(album == null) album = "N/D";

            t = 'm';
            c = new Musica(p.hashCode(), title, duracao, "mp3", categoria, artist);
        }
        else if (type.equals("mp4")){
            Metadata m = extrairMetaMp4(origin);
            duracao = Double.parseDouble(m.get("xmpDM:duration"));
            t='v';
            c = new Video();
            c.setId(p.hashCode());
            c.setNome(mp4Artist);
            c.setDuracao(duracao);
        }
        else throw new FormatoDesconhecidoException();

        //Verificar duplicaçoes
        if(gc.verificaDuplicacoes(c,t)) throw new ConteudoDuplicadoException();
        //Adicionar a bibliotecas
        gc.uploadConteudo(c,t,u);
        gu.uploadConteudo(c, t,u);
        //Path building
        String path=(new File("").getAbsolutePath())+"/src/Biblioteca/"+c.getId()+ (t=='m'? ".mp3":".mp4" );
        File newFile = new File(path);
        System.out.println(path);
        newFile.createNewFile();
        origin.renameTo(newFile);

    }

    private Metadata extrairMetaMp4(File origin) throws TikaException, SAXException, IOException {
        BodyContentHandler handler = new BodyContentHandler();
        Metadata metadata = new Metadata();
        FileInputStream inputstream = new FileInputStream(origin);
        ParseContext pcontext = new ParseContext();
        MP4Parser mp4Parser = new MP4Parser();
        mp4Parser.parse(inputstream, handler, metadata, pcontext);
        return metadata;
    }

    private double getDuration(File origin) throws TikaException, SAXException, IOException {
        BodyContentHandler handler = new BodyContentHandler();
        Metadata metadata = new Metadata();
        FileInputStream inputstream = new FileInputStream(origin);
        ParseContext pcontext = new ParseContext();

        Mp3Parser  mp3Parser = new  Mp3Parser();
        mp3Parser.parse(inputstream, handler, metadata, pcontext);
        return (Double.parseDouble(metadata.get("xmpDM:duration")));
    }



    /** Método que altera o tipo do utilizador que está a usar o sistema
     * @param idT    Tipo do utilizador
     */
    public void setUserT(int idT) {
        idType = idT;
    }

    /** Método que altera o tipo do utilizador a usar o sistema
     * @return      Tipo do utilizador
     */
    public int getUserT() {
        return idType;
    }

    private ID3v24Handler extrairMetaMp3(File file) throws IOException, TikaException, SAXException {
        FileInputStream inputstream = new FileInputStream(file);
        ID3v24Handler ret = new ID3v24Handler((ID3v2Frame) ID3v2Frame.createFrameIfPresent(inputstream));
        return  ret;
    }

    public Set<Musica> showMusicas(){
        return  gc.getBibliotecaMusica();
    }
    public Set<Video> showVideos(){
        return  gc.getBibliotecaVideo();
    }
    public List<Musica> showMusicasPlaylist(int idPlaylist){
        return gu.getPlaylistMusica(idPlaylist);


    }
    public List<Video> showVideosPlaylist(int idPlaylist){
      return gu.getPlaylistVideo(idPlaylist);
    }


}

