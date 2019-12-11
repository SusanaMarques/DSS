package Database;

import Business.Musica;
import Business.UtilizadorRegistado;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;


public class PlaylistMusicaDAO implements Map<Integer, List<Musica>>
{
    private Connection c;

    /**
     * Método que retorna o número de entradas na base de dados
     * @return s                       número de entradas
     * @throws NullPointerException    Não há conexão com a base de dados
     */
    @Override
    public int size() {
        int s = -1;
        try {
            c = Connect.connect();
            PreparedStatement stm = c.prepareStatement("SELECT count(*) FROM PlaylistMusica");
            ResultSet rs = stm.executeQuery();
            if(rs.next()) { s = rs.getInt(1); }
        }
        catch (Exception e) { throw new NullPointerException(e.getMessage()); } finally { Connect.close(c); }
        return s;
    }

    /**
     * Método que verifica se a base de dados está vazia
     * @return  True caso a base de dados esteja vazia, false caso contrário
     */
    @Override
    public boolean isEmpty() { return (this.size() == 0); }

    /**
     * Método que verifica se o id de uma playlist existe na base de dados
     * @param o                      Objeto a verficar
     * @return                       True se a playlist existir
     * @throws NullPointerException  Não existe conexão com a base de dados
     */
    @Override
    public boolean containsKey(Object o) {
        boolean res = false;
        try {
            c = Connect.connect();
            String sql = "SELECT idPlaylist FROM PlaylistMusica WHERE idPlaylist = ?";
            PreparedStatement stm = c.prepareStatement(sql);
            stm.setInt(1, (Integer) o);
            ResultSet rs = stm.executeQuery();
            res = rs.next();
        } catch (Exception e) { throw new NullPointerException(e.getMessage()); } finally { Connect.close(c); }
        return res;
    }

    @Override
    public boolean containsValue(Object o) { throw new UnsupportedOperationException("Not Implemented"); }

    /**
     * Método que verifica se o id de uma determinada playlist existe na base de dados
     * @param o                      Objeto a verficar
     * @return                       True se a playlist existir
     * @throws NullPointerException  Não existe conexão com a base de dados
     */
    @Override
    public List<Musica> get(Object o) {
        Musica m;
        ArrayList<Musica> array = new ArrayList<>();
        try {
            c = Connect.connect();

            String sql = "SELECT idPlaylist FROM PlaylistMusica WHERE idPlaylist = ?";
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setInt(1, (Integer) o);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                try {
                    PreparedStatement pss = c.prepareStatement("SELECT * FROM Musica WHERE idMusica = ?");
                    pss.setInt(1, rs.getInt("idMusica"));
                    ResultSet rss = ps.executeQuery();
                    m = new Musica(rs.getInt("idMusica"), rs.getString("nome"), rs.getDouble("duracao"), rs.getString("formato"), rs.getString("categoria"), rs.getString("artista"));
                    array.add(m);}
                catch (SQLException ex) { ex.printStackTrace(); }
            } } catch (Exception e) { e.printStackTrace(); } finally { Connect.close(c); }
        return array;
    }

    /**
     * Método que insere uma nova playlist na base de dados
     * @param k      id da playlist
     * @param v      lista de musicas
     * @return
     */
    @Override
    public List<Musica> put(Integer k, List<Musica> v){
        ArrayList<Musica> array = new ArrayList<>();

        try{
            c = Connect.connect();

            PreparedStatement ps = c.prepareStatement("INSERT INTO PlaylistMusica (idPlaylist,idMusica) VALUES (?,?)");
            for(Musica musica : v) {
                ps.setInt(1,k);
                ps.setInt(2, musica.getId());}
        }
        catch(Exception e){ System.out.printf(e.getMessage()); }
        finally{ try{ Connect.close(c); } catch(Exception e){ System.out.printf(e.getMessage()); } }
        return array;
    }

    @Override
    public List<Musica> remove(Object o) { throw new UnsupportedOperationException("Not Implemented"); }

    @Override
    public void putAll(Map<? extends Integer, ? extends List<Musica>> map) { throw new UnsupportedOperationException("Not Implemented"); }

    @Override
    public void clear() { throw new UnsupportedOperationException("Not Implemented"); }

    @Override
    public Set<Integer> keySet() {
        throw new UnsupportedOperationException("Not Implemented");
    }

    @Override
    public Collection<List<Musica>> values() {
        throw new UnsupportedOperationException("Not Implemented");
    }

    @Override
    public Set<Entry<Integer, List<Musica>>> entrySet() {
        throw new UnsupportedOperationException("Not Implemented");
    }
}
