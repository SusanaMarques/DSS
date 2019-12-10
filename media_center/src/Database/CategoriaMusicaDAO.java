package Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class CategoriaMusicaDAO implements Map<Integer, Map<Integer, String>>
{
    private Connection c;

    @Override
    public int size() {
        int s = -1;
        try {
            c = Connect.connect();
            PreparedStatement stm = c.prepareStatement("SELECT count(*) FROM Administrador");
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
            String sql = "SELECT idMusica FROM CategoriaMusica WHERE idMusica = ?";
            PreparedStatement stm = c.prepareStatement(sql);
            stm.setInt(1, (Integer) o);
            ResultSet rs = stm.executeQuery();
            res = rs.next();
        } catch (Exception e) { throw new NullPointerException(e.getMessage()); } finally { Connect.close(c); }
        return res;
    }

    @Override
    public boolean containsValue(Object o) { throw new UnsupportedOperationException("Not Implemented"); }

    @Override //returns null se a categoria da música não tiver sido alterada
    public Map<Integer, String> get(Object o){ throw new UnsupportedOperationException("Erro!"); }

    @Override
    public Map<Integer, String> put(Integer k, Map<Integer,String> v) { throw new UnsupportedOperationException("Erro!"); }

    @Override
    public Map<Integer,String> remove(Object o) { throw new UnsupportedOperationException("Erro!"); }

    @Override
    public void putAll(Map<? extends Integer, ? extends Map<Integer,String>> map) { throw new UnsupportedOperationException("Erro!"); }

    @Override
    public void clear() {
        try{
            c = Connect.connect();
            PreparedStatement ps = c.prepareStatement("DELETE FROM CategoriaMusica");
            ps.executeUpdate();
        }
        catch(Exception e){ System.out.printf(e.getMessage()); }
        finally{ try{ Connect.close(c); } catch(Exception e){ System.out.printf(e.getMessage()); } }
    }

    @Override
    public Set<Integer> keySet() {
        Set<Integer> k = null;
        try{
            c = Connect.connect();
            k = new HashSet<>();
            PreparedStatement ps = c.prepareStatement("SELECT idMusica FROM CategoriaMusica");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){ k.add(rs.getInt(1)); }
        }
        catch(Exception e){ System.out.printf(e.getMessage()); } finally{ try{ Connect.close(c); } catch(Exception e){ System.out.printf(e.getMessage()); } }
        return k;
    }

    @Override
    public Collection<Map<Integer,String>> values() { throw new UnsupportedOperationException("Erro!"); }

    @Override
    public Set<Entry<Integer,Map<Integer,String>>> entrySet() { throw new UnsupportedOperationException("Erro!"); }


}
