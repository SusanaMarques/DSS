package Business;

import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.mp3.ID3v24Handler;
import org.apache.tika.parser.mp3.ID3v2Frame;
import org.apache.tika.parser.mp3.Mp3Parser;
import org.apache.tika.parser.mp4.MP4Parser;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;


public class  MC
{
    /** Instancia da gestao de conteudo**/
    private GestaoConteudo gc = new GestaoConteudo();
    /** Instancia da gestao de utilizador**/
    private GestaoUtilizador gu = new GestaoUtilizador();
    /** Id do utilizador atual**/
    private int idUtilizadorAtual;
    /** Identificador do tipo de utilizador             Administrador : 1                Utilizador Registado : 2   **/
    private int idType;


    /** Método que inicia uma sessão
     * @param mail    Email do utilizador a autenticar
     * @param pass    Password do utilizador a autenticar
     */
    public void iniciarSessao(String mail, String pass) throws CredenciaisInvalidasException {
        if(mail == null||pass == null || mail.isEmpty() || pass.isEmpty()) throw new CredenciaisInvalidasException();
        Utilizador u = gu.iniciarSessao(mail, pass, idType);
        this.idUtilizadorAtual = u.getId();
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
        System.out.println(mp4Artist);
        String type = tokens.nextToken();
        UtilizadorRegistado u =(UtilizadorRegistado) gu.getUser(idUtilizadorAtual,idType);
        char t;
        Conteudo c = new Conteudo();



        //Extrair metadados
        String album;
        String artist;
        String title;
        String categoria;
        double duracao ;
        File origin = new File((new URI(p)).getPath());

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
            String realizador = m.get("xmpDM:artist");
            if (realizador== null) realizador="N/D";
            t='v';
            System.out.println(t);
            Video v = new Video();
            v.setId(p.hashCode());
            v.setNome(Integer.toString(c.getId()));
            v.setDuracao(duracao);
            v.setCategoria("N/D");
            v.setRealizador(realizador);
            c=v;
        }
        else throw new FormatoDesconhecidoException();

        //Verificar duplicaçoes
        int dupId = gc.verificaDuplicacoes(c,t);
        if(dupId == -1) {
            //Path building & copiar para a biblioteca
            String path=(new File("").getAbsolutePath())+"/Biblioteca/"+c.getId()+ (t=='m'? ".mp3":".mp4" );
            File newFile = new File(path);
            Files.copy(origin.toPath(),newFile.toPath());
        }
        else c = gc.getConteudo(dupId,t);

        //Adicionar a bibliotecas
        gc.uploadConteudo(c,t,u, dupId);
        gu.uploadConteudo(c, t,u);
    
     
        if (dupId == -1) throw new ConteudoDuplicadoException();
    }

    /** Método que extrai os metadados dos ficheiros mp4
     * @param origin       File com a instancia do mp4 a extrair
     * @return             Instancia com os metadados do mp4
     */
    private Metadata extrairMetaMp4(File origin) throws TikaException, SAXException, IOException {
        BodyContentHandler handler = new BodyContentHandler();
        Metadata metadata = new Metadata();
        FileInputStream inputstream = new FileInputStream(origin);
        ParseContext pcontext = new ParseContext();
        MP4Parser mp4Parser = new MP4Parser();
        mp4Parser.parse(inputstream, handler, metadata, pcontext);
        return metadata;
    }

    /** Método para extrair a duração das musicas
     * @param origin     File com a instancia do mp3 a extrair
     * @return           Duração do mp3
     */
    private double getDuration(File origin) throws TikaException, SAXException, IOException {
        BodyContentHandler handler = new BodyContentHandler();
        Metadata metadata = new Metadata();
        FileInputStream inputstream = new FileInputStream(origin);
        ParseContext pcontext = new ParseContext();

        Mp3Parser  mp3Parser = new  Mp3Parser();
        mp3Parser.parse(inputstream, handler, metadata, pcontext);
        return (Double.parseDouble(metadata.get("xmpDM:duration")));
    }

    /** Método que extrai os metadados dos ficheiros mp3
     * @param file     File com a instancia do mp3 a extrair
     * @return        Instancia do handler dos metadados do mp3
     */
    private ID3v24Handler extrairMetaMp3(File file) throws IOException, TikaException, SAXException {
        FileInputStream inputstream = new FileInputStream(file);
        ID3v24Handler ret = new ID3v24Handler((ID3v2Frame) ID3v2Frame.createFrameIfPresent(inputstream));
        return  ret;
    }

    /** Método que altera o tipo do utilizador que está a usar o sistema
     * @param idT    Tipo do utilizador
     */
    public void setUserT(int idT) {
        idType = idT;
    }

    /** Método que retorna o tipo do utilizador a usar o sistema
     * @return      Tipo do utilizador
     */
    public int getUserT() {
        return idType;
    }


    /** Método que apresenta a Biblioteca geral das Musicas
     * @return        Set com todas as instancias de musica da biblioteca geral
     */
    public Set<Musica> showMusicas(){
        return  gc.getBibliotecaMusica();
    }

    /** Método que apresenta a Biblioteca geral dos Videos
     * @return        Set com todas as instancias de video da biblioteca geral
     */
    public Set<Video> showVideos(){
        return  gc.getBibliotecaVideo();
    }


    /** Método que apresenta uma playlist de musicas
     * @param idPlaylist     Id da playlist
     * @return               List com todas as musicas da playlist
     */
    public List<Musica> showMusicasPlaylist(int idPlaylist){ return gu.getPlaylistMusica(idPlaylist);}

    /** Método que apresenta uma playlist de videos
     * @param idPlaylist    Id da playlist
     * @return              List com todas os videos da playlist
     */
    public List<Video> showVideosPlaylist(int idPlaylist){
      return gu.getPlaylistVideo(idPlaylist);
    }

    /** Método que altera a categoria de um conteudo de um utilizador
     * @param idCont Id do conteudo a alterar
     * @param newCat Nova categoria do conteudo
     * @param type Tipo do conteudo a alterar: m para musicas e v para videos
     */
    public void alterarCategoria(String newCat, int idCont, char type) throws CategoriaIgualException {
        if(type=='m') gu.alterarCategoriaM(newCat,idCont,idUtilizadorAtual);
        if(type=='v') gu.alterarCategoriaV(newCat,idCont,idUtilizadorAtual);
    }


    public String getPath(char type, int idCont) throws MalformedURLException {
        File pathFinder = new File("");
        String s = pathFinder.toURI().toURL().toExternalForm()+"/Biblioteca/"+idCont;
        if(type=='m') s+=".mp3";
        else s+=".mp4";
        return s;
    }


}

