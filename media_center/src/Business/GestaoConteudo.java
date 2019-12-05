package Business;


import Database.MusicaDAO;
import Database.VideoDAO;

import java.util.Map;

public class GestaoConteudo {

    private Map<Integer,Musica> musicas = new MusicaDAO();
    private Map<Integer,Video> videos = new VideoDAO();


    public boolean verificaDuplicacoes(Conteudo c, char type){
        boolean ret=true;
        if(type=='m') {ret=musicas.containsValue(c); System.out.println("Musica"+ret);}
        if(type=='v') {ret=videos.containsValue(c);System.out.println("Video"+ret);}
        System.out.println(""+ret);
        return ret;

    }


    public void uploadConteudo(Conteudo c, char type) {
        if(type=='m') {musicas.put(c.getId(),(Musica) c); }
        if(type=='v') {videos.put(c.getId(),(Video)c);}

    }
}
