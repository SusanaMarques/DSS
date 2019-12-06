package Business;


import Database.MusicaDAO;
import Database.ProprietariosMusicaDAO;
import Database.ProprietariosVideoDAO;
import Database.VideoDAO;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GestaoConteudo {
    /**Instancia da Biblioteca geral de Musicas **/
    private Map<Integer,Musica> musicas = new MusicaDAO();
    /**Instancia da Biblioteca geral de Videos **/
    private Map<Integer,Video> videos = new VideoDAO();
    /**Instancia de proprietarios das Musicas **/
    private Map<Integer, List<UtilizadorRegistado>> proprietariosMusica = new ProprietariosMusicaDAO();
    /**Instancia de proprietarios dos Videos **/
    private Map<Integer, List<UtilizadorRegistado>> proprietariosVideo = new ProprietariosVideoDAO();

    /** Metodo verificador de Duplicacoes de conteudo
     * @param c Conteudo a comparar
     * @param type Tipo do conteudo
     * @return true caso existam duplicacoes, false caso contrario**/
    public boolean verificaDuplicacoes(Conteudo c, char type){
        boolean ret=true;
        if(type=='m') {ret=musicas.containsValue(c);}
        if(type=='v') {ret=videos.containsValue(c);}
        return ret;

    }

    /**Metodo que adiciona o conteudo á biblioteca geral e atualiza o seu proprietario
     * @param c Conteudo a adicionar
     * @param type Tipo do conteudo a adicionar
     * @param u Utilizador que está a carregar o conteudo**/
    public void uploadConteudo(Conteudo c, char type, UtilizadorRegistado u) {
        Map<Integer, List<UtilizadorRegistado>> props=null ;
        if(type=='m') {musicas.put(c.getId(),(Musica) c);props=proprietariosMusica; }
        if(type=='v') {videos.put(c.getId(),(Video)c);props=proprietariosVideo;}
        List<UtilizadorRegistado> propList = new ArrayList<>();
        propList.add(u);
        props.put(c.getId(),propList);
    }
}
