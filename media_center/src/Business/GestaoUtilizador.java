package Business;

import Database.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


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
    /** Mapa da categoria das musicas quando alteradas pelos utilizadores **/
    private Map<Integer,Map<Integer,String>> categoriasMusica = new CategoriaMusicaDAO();
    /** Mapa da categoria das videos quando alteradas pelos utilizadores **/
    private Map<Integer,Map<Integer,String>> categoriasVideo = new CategoriaVideoDAO();

    /** Método que incia a sessão
     * @param email    Email do utilizador a autenticar
     * @param pass     Password do utilizador a autenticar
     * @param idOp     Identificador do tipo de utilizador
     */
    public Utilizador iniciarSessao(String email, String pass, int idOp) throws CredenciaisInvalidasException {
        Utilizador u;
        if(idOp == 1) u = admins.get(email);
            else u = users.get(email);
            boolean e = u.getEmail().equals(email);
            boolean p = u.getPassword().equals(pass);
        if( !(e && p) ) throw new CredenciaisInvalidasException();
        return u;
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

    /** Método que apresenta uma playlist de musicas
     * @param idPlaylist Id da playlist
     * @return List com todas as musicas da playlist
     */
    public List<Musica> getPlaylistMusica(int idPlaylist) {
        return playlistsMusicas.get(idPlaylist);
    }

    /** Método que apresenta uma playlist de videos
     * @param idPlaylist Id da playlist
     * @return List com todas os videos da playlist
     */
    public List<Video> getPlaylistVideo( int idPlaylist) {
        return playlistsVideos.get(idPlaylist);
    }



    /** Método que altera a categoria de uma musica de um utilizador
     * @param idCont   Id da musica a alterar
     * @param newCat   Nova categoria da musica
     * @param idU      Id do utilizador
     */
    public void alterarCategoriaM(String newCat, int idCont,int idU) throws CategoriaIgualException {
        Map<Integer,String> cats=null;
        if(categoriasMusica.containsKey(idCont))
            cats= categoriasMusica.get(idCont);
        else cats=new HashMap<>();
        if(cats.containsKey(idU)) if(cats.get(idU).equals(newCat)) throw new CategoriaIgualException();
        else cats.remove(idU);
        cats.put(idU,newCat);
        categoriasMusica.put(idCont,cats);
    }

    /** Método que altera a categoria de um video de um utilizador
     * @param idCont   Id do video a alterar
     * @param newCat   Nova categoria do video
     * @param idU      Id do utilizador
     */
    public void alterarCategoriaV(String newCat, int idCont,int idU) throws CategoriaIgualException {
        Map<Integer,String> cats=null;
        if(categoriasMusica.containsKey(idCont))
            cats= categoriasVideo.get(idCont);
           else cats=new HashMap<>();
        if(cats.containsKey(idU)) if(cats.get(idU).equals(newCat)) throw new CategoriaIgualException();
                                    else cats.remove(idU);
        cats.put(idU,newCat);
        categoriasVideo.put(idCont,cats);
    }
}
