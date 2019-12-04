package Business;

import Database.AdministradorDAO;
import Database.MusicaDAO;
import Database.UtilizadorRegistadoDAO;
import Database.VideoDAO;

import java.util.Map;

public class GestaoConteudo {

    private Map<Integer,Musica> musicas = new MusicaDAO();
    private Map<Integer,Video> videos = new VideoDAO();


    public boolean verificaDuplicacoes(Conteudo c, char type){
        boolean ret=false;
        if(type=='m') ret=musicas.containsValue(c);
        if(type=='v') ret=videos.containsValue(c);
        return ret;
    }


}
