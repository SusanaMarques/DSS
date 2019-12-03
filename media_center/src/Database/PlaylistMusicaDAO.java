package Database;

import Business.Musica;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class PlaylistMusicaDAO implements Map<Integer, Musica>
{
    private Connection c;

    @Override
    public int size() {
        int s = -1;
        try {
            c = Connect.connect();
            PreparedStatement stm = c.prepareStatement("SELECT count(*) FROM PlaylistMusica");
            ResultSet rs = stm.executeQuery();
            if(rs.next()) {
                s = rs.getInt(1);
            }
        }
        catch (Exception e) { throw new NullPointerException(e.getMessage()); }
        finally { Connect.close(c); }
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
            String sql = "SELECT idPlaylist FROM PlaylistMusica WHERE idPlaylist = ?";
            PreparedStatement stm = c.prepareStatement(sql);
            stm.setString(1, (String) o);
            ResultSet rs = stm.executeQuery();
            res = rs.next();
        } catch (Exception e) { throw new NullPointerException(e.getMessage()); } finally { Connect.close(c); }
        return res;
    }

    @Override
    public boolean containsValue(Object o) {
        boolean res = false;

        if(o.getClass().getName().equals("Business.Musica")){
            Musica m = (Musica) o;
            int id = m.getId();
            Musica msc = this.get(id);
            if(msc.equals(m)){ res = true; }
        }
        return res;
    }

    @Override //UNFINISHED
    public Musica get(Object o) {
        Musica m = new Musica();
        return m;
    }

    @Override //UNFINISHED
    public Musica put(Integer k, Musica v) {
        Musica m = new Musica();
        return m;
    }

    @Override
    public Musica remove(Object o) { throw new UnsupportedOperationException("Erro!"); }

    @Override
    public void putAll(Map<? extends Integer, ? extends Musica> map) {
        for(Musica u : map.values()) { put(u.getId(), u); }
    }

    @Override
    public void clear() {
        try {
            c = Connect.connect();
            PreparedStatement ps = c.prepareStatement("DELETE FROM PlaylistMusica");
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.printf(e.getMessage());
        } finally {
            try {
                Connect.close(c);
            } catch (Exception e) {
                System.out.printf(e.getMessage());
            }
        }
    }

    @Override
    public Set<Integer> keySet() {
        Set<Integer> keys = null;

        try{
            c = Connect.connect();
            keys = new HashSet<>();
            PreparedStatement ps = c.prepareStatement("SELECT idPlaylist FROM Playlist");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){ keys.add(rs.getInt(1)); }
        }
        catch(Exception e){ System.out.printf(e.getMessage()); }
        finally{
            try{ Connect.close(c); } catch(Exception e){ System.out.printf(e.getMessage()); }
        }
        return keys;
    }

    @Override
    public Collection<Musica> values()
    {
        Set<Musica> u = new HashSet<>();
        Set<Integer> keys = new HashSet<>(this.keySet());

        for(Integer k : keys){u.add(this.get(k));}
        return u;
    }

    @Override
    public Set<Entry<Integer, Musica>> entrySet()
    {
        Set<Integer> keys = new HashSet<>(this.keySet());
        Map<Integer,Musica> map = new HashMap<>();

        for(Integer k : keys){ map.put(k,this.get(k));}
        return map.entrySet();
    }
}