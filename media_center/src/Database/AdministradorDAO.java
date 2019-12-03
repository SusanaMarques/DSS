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

    @Override
    public boolean containsValue(Object o) {
        boolean res = false;

        if(o.getClass().getName().equals("Business.Administrador")){
            Administrador a = (Administrador) o;
            int id = a.getId();
            Administrador admin = this.get(id);
            if(admin.equals(a)){ res = true; }
        }
        return res;
    }

    @Override
    public Administrador get(Object o) {
        Administrador a = new Administrador();

        try{
            System.out.println("cheagay");
            c = Connect.connect();
            System.out.println("cheagay2");
            PreparedStatement ps = null;
            if(o instanceof String) {
                ps = c.prepareStatement("SELECT * FROM Administrador WHERE email = ?");
                ps.setString(1, (String) o);
            }
            if(o instanceof Integer) {
                ps = c.prepareStatement("SELECT * FROM Administrador WHERE idAdministrador = ?");
                ps.setInt(1, (Integer) o);
            }

            System.out.println("cheagay3");
            ResultSet rs = ps.executeQuery();

            System.out.println(rs.toString());

            System.out.println("cheagay4");
            if(rs.next()){

                System.out.println("cheagay5");
                a.setId(rs.getInt("idAdministrador"));
                a.setNome(rs.getNString("nome"));
                a.setEmail(rs.getNString("email"));
                a.setPassword(rs.getNString("password"));
                System.out.println("email"+a.getEmail());
                System.out.println("pass"+a.getPassword());
            }
        }
        catch(Exception e){
            System.out.printf(e.getMessage());
        }
        finally{ try{ Connect.close(c); } catch(Exception e){ System.out.printf(e.getMessage()); } }
        return a;
    }

    @Override
    public Administrador put(String k, Administrador v) {
        Administrador admin;

        if(this.containsKey(k)){
            admin = this.get(k);
        }
        else admin = v;
        try{
            c = Connect.connect();

            PreparedStatement ps = c.prepareStatement("INSERT INTO Administrador (idAdministrador,Nome,Email,Password) VALUES (?,?,?,?)");
            ps.setString(1,k);
            ps.setString(2,v.getNome());
            ps.setString(3,v.getEmail());
            ps.setString(3,v.getPassword());
            ps.executeUpdate();
        }
        catch(Exception e){ System.out.printf(e.getMessage()); }
        finally{ try{ Connect.close(c); } catch(Exception e){ System.out.printf(e.getMessage()); } }
        return admin;
    }

    @Override
    public Administrador remove(Object o) { throw new UnsupportedOperationException("Erro!"); }

    @Override
    public void putAll(Map<? extends String, ? extends Administrador> map) {
        for(Administrador admin : map.values()) { put(admin.getEmail(), admin); }
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
