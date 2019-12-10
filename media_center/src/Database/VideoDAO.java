package Database;

import Business.Video;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class VideoDAO implements Map<Integer, Video>
{
    Connection c;

    @Override
    public int size() {
        int s = -1;
        try {
            c = Connect.connect();
            PreparedStatement stm = c.prepareStatement("SELECT count(*) FROM Video");
            ResultSet rs = stm.executeQuery();
            if(rs.next()) { s = rs.getInt(1); }
        }
        catch (Exception e) { throw new NullPointerException(e.getMessage()); } finally { Connect.close(c); }
        return s;
    }

    @Override
    public boolean isEmpty() {
        return (this.size() == 0);
    }

    @Override
    public boolean containsKey(Object o) {
        boolean res = false;
        try {
            c = Connect.connect();
            String sql = "SELECT idVideo FROM Video WHERE idVideo = ?";
            PreparedStatement stm = c.prepareStatement(sql);
            stm.setInt(1, (Integer) o);
            ResultSet rs = stm.executeQuery();
            res = rs.next();
        } catch (Exception e) { throw new NullPointerException(e.getMessage()); } finally { Connect.close(c); }
        return res;
    }

    @Override
    public boolean containsValue(Object o) {
        boolean res = false;
        Video v = (Video) o;
        try {
            c = Connect.connect();
            String sql = "SELECT count(*) FROM Video WHERE nome = ? AND duracao = ? AND formato = ? AND categoria = ? AND realizador = ?";
            PreparedStatement stm = c.prepareStatement(sql);
            stm.setString(1, v.getNome());
            stm.setDouble(2, v.getDuracao());
            stm.setString(3, v.getFormato());
            stm.setString(4, v.getCategoria());
            stm.setString(5, v.getRealizador());
            ResultSet rs = stm.executeQuery();
            rs.next();
            if((rs.getInt(1)) > 0) res = true;
        } catch (Exception e) { throw new NullPointerException(e.getMessage()); } finally { Connect.close(c); }
        return res;
    }

    @Override
    public Video get(Object o) {
        Video v = new Video();

        try {
            c = Connect.connect();
            PreparedStatement ps = c.prepareStatement("SELECT * FROM Video WHERE idVideo = ?");
            ps.setInt(1, (Integer) o);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                v.setId(rs.getInt("idVideo"));
                v.setNome(rs.getNString("nome"));
                v.setDuracao(rs.getDouble("duracao"));
                v.setFormato(rs.getNString("formato"));
                v.setCategoria(rs.getNString("categoria"));
                v.setRealizador(rs.getNString("realizador"));
            }
        } catch (Exception e) { System.out.printf(e.getMessage()); } finally { try { Connect.close(c); } catch (Exception e) { System.out.printf(e.getMessage()); } }
        return v;
    }

    @Override
    public Video put(Integer k, Video v) {
        Video video;

        if(this.containsKey(k)){
            video = this.get(k);
        }
        else video = v;
        try{
            c = Connect.connect();

            PreparedStatement ps = c.prepareStatement("INSERT INTO Video(idVideo,Nome,Duracao,Formato, Categoria,Realizador) VALUES (?,?,?,?,?,?)");
            ps.setInt(1,k);
            ps.setString(2,v.getNome());
            ps.setDouble(3,v.getDuracao());
            ps.setString(4,v.getFormato());
            ps.setString(5, v.getCategoria());
            ps.setString(6, v.getRealizador());
            ps.executeUpdate();
        }
        catch(Exception e){ System.out.printf(e.getMessage()); }
        finally{ try{ Connect.close(c); } catch(Exception e){ System.out.printf(e.getMessage()); } }
        return video;
    }

    @Override
    public Video remove(Object o) { throw new UnsupportedOperationException("Erro!"); }

    @Override
    public void putAll(Map<? extends Integer, ? extends Video> map) {
        for(Video v : map.values()) { put(v.getId(), v); }
    }

    @Override
    public void clear() {
        try{
            c = Connect.connect();
            PreparedStatement ps = c.prepareStatement("DELETE FROM Video");
            ps.executeUpdate();
        }
        catch(Exception e){ System.out.printf(e.getMessage()); }
        finally{ try{ Connect.close(c); } catch(Exception e){ System.out.printf(e.getMessage()); } }
    }

    @Override
    public Set<Integer> keySet() {
        Set<Integer> keys = null;

        try{
            c = Connect.connect();
            keys = new HashSet<>();
            PreparedStatement ps = c.prepareStatement("SELECT idVideo FROM Video");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){ keys.add(rs.getInt(1)); }
        }
        catch(Exception e){ System.out.printf(e.getMessage()); }
        finally{ try{ Connect.close(c); } catch(Exception e){ System.out.printf(e.getMessage()); } }
        return keys;
    }

    @Override
    public Collection<Video> values()
    {
        Set<Video> u = new HashSet<>();
        Set<Integer> keys = new HashSet<>(this.keySet());

        for(Integer k : keys){u.add(this.get(k));}
        return u;
    }

    @Override
    public Set<Entry<Integer, Video>> entrySet()
    {
        Set<Integer> keys = new HashSet<>(this.keySet());
        Map<Integer,Video> map = new HashMap<>();

        for(Integer k : keys){ map.put(k,this.get(k));}
        return map.entrySet();
    }
}