package Database;

import Business.Musica;
import Business.Video;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

public class PlaylistVideoDAO implements Map<Integer, List<Video>>
{
    private Connection c;

    @Override
    public int size() {
        int s = -1;
        try {
            c = Connect.connect();
            PreparedStatement stm = c.prepareStatement("SELECT count(*) FROM PlaylistVideo");
            ResultSet rs = stm.executeQuery();
            if(rs.next()) { s = rs.getInt(1); }
        }
        catch (Exception e) { throw new NullPointerException(e.getMessage()); } finally { Connect.close(c); }
        return s;
    }

    @Override
    public boolean isEmpty() { return (this.size() == 0); }

    @Override
    public boolean containsKey(Object o) {
        boolean res = false;
        try {
            c = Connect.connect();
            String sql = "SELECT idPlaylist FROM PlaylistVideo WHERE idPlaylist = ?";
            PreparedStatement stm = c.prepareStatement(sql);
            stm.setInt(1, (Integer) o);
            ResultSet rs = stm.executeQuery();
            res = rs.next();
        } catch (Exception e) { throw new NullPointerException(e.getMessage()); } finally { Connect.close(c); }
        return res;
    }

    @Override
    public boolean containsValue(Object o) { throw new UnsupportedOperationException("Not Implemented"); }

    @Override
    public List<Video> get(Object o) {
        Video v = new Video();
        ArrayList<Video> array = new ArrayList<>();
        try {
            c = Connect.connect();

            String sql = "SELECT idPlaylist FROM PlaylistVideo WHERE idPlaylist = ?";
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setInt(1, (Integer) o);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                v = new Video(rs.getInt("idVideo"), rs.getString("nome"), rs.getDouble("duracao"), rs.getString("formato"), rs.getString("categoria"), rs.getString("realizador"));
                array.add(v);
            }
        } catch (Exception e) { e.printStackTrace(); } finally { Connect.close(c); }
        return array;
    }

    @Override //INCOMPLETO
    public List<Video> put(Integer integer, List<Video> videos) {
        return null;
    }

    @Override
    public List<Video> remove(Object o) { throw new UnsupportedOperationException("Not Implemented"); }

    @Override
    public void putAll(Map<? extends Integer, ? extends List<Video>> map) { throw new UnsupportedOperationException("Not Implemented"); }

    @Override
    public void clear() {throw new UnsupportedOperationException("Not Implemented");}

    @Override
    public Set<Integer> keySet() {
        throw new UnsupportedOperationException("Not Implemented");
    }

    @Override
    public Collection<List<Video>> values() {
        throw new UnsupportedOperationException("Not Implemented");
    }

    @Override
    public Set<Entry<Integer, List<Video>>> entrySet() {
        throw new UnsupportedOperationException("Not Implemented");
    }
}
