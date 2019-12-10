package Business;

import Database.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class GestaoConteudo {
    /**
     * Biblioteca geral de Musicas
     **/
    private Map<Integer, Musica> musicas = new MusicaDAO();
    /**
     * Biblioteca geral de Videos
     **/
    private Map<Integer, Video> videos = new VideoDAO();
    /**
     * Proprietarios das Musicas
     **/
    private Map<Integer, List<UtilizadorRegistado>> proprietariosMusica = new ProprietariosMusicaDAO();
    /**
     * Proprietarios dos Videos
     **/
    private Map<Integer, List<UtilizadorRegistado>> proprietariosVideo = new ProprietariosVideoDAO();


    /**
     * Método que verifica duplicacoes de conteudo
     *
     * @param c    Conteudo a comparar
     * @param type Tipo do conteudo
     * @return true caso existam duplicacoes, false caso contrario
     */
    public boolean verificaDuplicacoes(Conteudo c, char type) {
        boolean ret = true;
        if (type == 'm') {
            ret = musicas.containsValue(c);
        }
        if (type == 'v') {
            ret = videos.containsValue(c);
        }
        return ret;
    }

    /**
     * Método que adiciona o conteudo à biblioteca geral e atualiza o seu proprietario
     *
     * @param c    Conteudo a adicionar
     * @param tipo Tipo do conteudo a adicionar
     * @param u    Utilizador que está a carregar o conteudo
     **/
    public void uploadConteudo(Conteudo c, char tipo, UtilizadorRegistado u) {
        Map<Integer, List<UtilizadorRegistado>> props = null;
        if (tipo == 'm') {
            musicas.put(c.getId(), (Musica) c);
            props = proprietariosMusica;
        } else {
            if (tipo == 'v') {
                System.out.println("1");
                videos.put(c.getId(), (Video) c);
                props = proprietariosVideo;
                System.out.println("2");
            } else {
                System.out.println("LOL NAO insere nao");
            }
        }
        List<UtilizadorRegistado> propList = new ArrayList<>();
        propList.add(u);
        props.put(c.getId(), propList);
    }

    public Set<Video> getBibliotecaVideo() {
        return ((Set) videos.values());
    }

    public Set<Musica> getBibliotecaMusica() { return ((Set) musicas.values()); }
}