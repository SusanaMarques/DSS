package Business;

import Database.AdministradorDAO;
import Database.PlaylistMusicaDAO;
import Database.PlaylistVideoDAO;
import Database.UtilizadorRegistadoDAO;

import java.util.List;
import java.util.Map;

public class GestaoUtilizador {
    private Map<String,Administrador> admins = new AdministradorDAO();
    private Map<String,UtilizadorRegistado> users = new UtilizadorRegistadoDAO();
    private Map<Integer, List<Musica>> playlistsMusicas = new PlaylistMusicaDAO();
    private Map<Integer, List<Video>> playlistsVideos = new PlaylistVideoDAO();

    /**Metodo de iniciar sessão
     * @param mail Email do utilizador a autenticar
     * @param pass Password do utilizador a autenticar
     * @param idOp Identificador do tipo de utilizador **/
    public void iniciarSessao(String email, String pass, int idOp) throws CredenciaisInvalidasException {
        Utilizador u;
        boolean ret = false;
        if(idOp == 1) u=admins.get(email);
            else u=users.get(email);
            boolean e = u.getEmail().equals(email);
            boolean p = u.getPassword().equals(pass);
        if( !(e && p) ) throw new CredenciaisInvalidasException();

    }

     /** Getter de utilizadores
      * @param idU Id de utlizador
      * @param idType Identificador do tipo de utilizador**/
    public Utilizador getUser(int idU,int idType){
        Utilizador ret;
        if (idType == 1) ret = users.get(idU);
        else ret = admins.get(idU);
        return ret;
    }

    /** Metodo que atualiza a biblioteca pessoal
     * @param c Conteudo a acrescentar
     * @param t Identificador do tipo de Conteudo
     * @param u Utilizdor a atualizar
     * **/
    public void uploadConteudo(Conteudo c, char t, UtilizadorRegistado u) {
        if(t=='m') {
            List<Musica> ls = playlistsMusicas.get(u.getIdBibliotecaMusica());
            ls.add((Musica) c);
            playlistsMusicas.put(u.getIdBibliotecaMusica(),ls);

        }
        if(t=='v') {
            List<Video> ls =  playlistsVideos.get(u.getIdBibliotecaVideo());
            ls.add((Video) c);
            playlistsVideos.put(u.getIdBibliotecaVideo(),ls);

        }




    }
}
