package Database;

import Business.Administrador;
import java.util.Map;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class AdministradorDAO implements Map<String,Administrador>
{
    private Connection c;

    @Override
    public int size() {
        int s = -1;
        try {
            c = Connect.connect();
            PreparedStatement stm = c.prepareStatement("SELECT count(*) FROM Administrador");
            ResultSet rs = stm.executeQuery();
            if(rs.next()) {
                s = rs.getInt(1);
            }
        }
        catch (Exception e) {
            throw new NullPointerException(e.getMessage());
        }
        finally {
            Connect.close(c);
        }
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
            String sql = "SELECT idAdmin FROM Administrador WHERE idAdmin = ?";
            PreparedStatement stm = c.prepareStatement(sql);
            stm.setString(1, (String) o);
            ResultSet rs = stm.executeQuery();
            res = rs.next();
        } catch (Exception e) { throw new NullPointerException(e.getMessage()); } finally { Connect.close(c); }
        return res;
    }

    @Override //completar
    public boolean containsValue(Object o) { return true; }

    @Override
    public Administrador get(Object o) {
        Administrador admin = new Administrador();
        return admin;
    }

    @Override
    public Administrador put(String k, Administrador a) {
        Administrador admin;

        if(this.containsKey(k)){
            admin = this.get(k);
        }
        else admin = a;
        try{
            c = Connect.connect();

            //PreparedStatement ps = c.prepareStatement(???)
        }
        catch(Exception e){ System.out.printf(e.getMessage()); }
        finally{ try{ Connect.close(c); } catch(Exception e){ System.out.printf(e.getMessage()); } }
        return admin;
    }

    @Override
    public Administrador remove(Object o) { throw new UnsupportedOperationException("Erro!"); }

    @Override
    public void putAll(Map<? extends String, ? extends Administrador> map) {
        for(Administrador admin : map.values()) { put(admin.getId(), admin); }
    }

    @Override
    public void clear() {
        try{
            c = Connect.connect();
            PreparedStatement ps = c.prepareStatement("DELETE FROM Administrador");
            ps.executeUpdate();
        }
        catch(Exception e){ System.out.printf(e.getMessage()); }
        finally{ try{ Connect.close(c); } catch(Exception e){ System.out.printf(e.getMessage()); } }
    }

    @Override
    public Set<String> keySet() {
        Set<String> keys = null;

        try{
            c = Connect.connect();
            keys = new HashSet<>();
            PreparedStatement ps = c.prepareStatement("SELECT idAdmin FROM Administrador");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){ keys.add(rs.getString(1)); }
        }
        catch(Exception e){ System.out.printf(e.getMessage()); }
        finally{
            try{ Connect.close(c); } catch(Exception e){ System.out.printf(e.getMessage()); }
        }
        return keys;
    }

    @Override
    public Collection<Administrador> values()
    {
        Set<Administrador> admin = new HashSet<>();
        Set<String> keys = new HashSet<>(this.keySet());

        for(String k : keys){admin.add(this.get(k));}
        return admin;
    }

    @Override
    public Set<Entry<String, Administrador>> entrySet()
    {
        Set<String> keys = new HashSet<>(this.keySet());
        Map<String,Administrador> map = new HashMap<>();

        for(String k : keys){ map.put(k,this.get(k));}
        return map.entrySet();
    }
}
