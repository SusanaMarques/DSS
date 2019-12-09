package Business;

import Database.AdministradorDAO;
import Database.PlaylistMusicaDAO;
import Database.PlaylistVideoDAO;
import Database.UtilizadorRegistadoDAO;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class GestaoUtilizador
{
    /** Administradores do media center **/
    private Map<String,Administrador> admins = new AdministradorDAO();
    /** Utilizadores registados no media center **/
    private Map<String,UtilizadorRegistado> users = new UtilizadorRegistadoDAO();
    /** Playlists de musica exitentes no media center **/
    private Map<Integer, List<Musica>> playlistsMusicas = new PlaylistMusicaDAO();
    /** Playlists de video exitentes no media center **/
    private Map<Integer, List<Video>> playlistsVideos = new PlaylistVideoDAO();

    /** Método que incia a sessão
     * @param email    Email do utilizador a autenticar
     * @param pass     Password do utilizador a autenticar
     * @param idOp     Identificador do tipo de utilizador
     */
    public void iniciarSessao(String email, String pass, int idOp) throws CredenciaisInvalidasException {
        Utilizador u;
        if(idOp == 1) u=admins.get(email);
            else u=users.get(email);
            boolean e = u.getEmail().equals(email);
            boolean p = u.getPassword().equals(pass);
        if( !(e && p) ) throw new CredenciaisInvalidasException();
    }

     /** Método que devolve os utilizadores
      * @param idU      Id de utlizador
      * @param idTipo   Identificador do tipo de utilizador
      */
    public Utilizador getUser(int idU,int idTipo){
        Utilizador ret;
        if (idTipo == 2) ret = users.get(idU);
        else ret = admins.get(idU);
        return ret;
    }

    /** Método que atualiza a biblioteca pessoal
     * @param c    Conteudo a acrescentar
     * @param t    Identificador do tipo de Conteudo
     * @param u    Utilizdor a atualizar
     **/
    public void uploadConteudo(Conteudo c, char t, UtilizadorRegistado u) {
        if(t == 'm') {
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

    public List<Musica> getPlaylistMusica(int idPlaylist) {
        return playlistsMusicas.get(idPlaylist);
    }
    public List<Video> getPlaylistVideo( int idPlaylist) {
        return playlistsVideos.get(idPlaylist);
    }
}
