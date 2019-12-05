package Database;

import Business.UtilizadorRegistado;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

public class ProprietariosMusicaDAO implements Map<Integer, List<UtilizadorRegistado>>
{
    private Connection c;

    @Override
    public int size() {
        int s = -1;
        try {
            c = Connect.connect();
            PreparedStatement stm = c.prepareStatement("SELECT count(*) FROM UtilizadorRegistado");
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
            String sql = "SELECT idUtilizador FROM Utilizador WHERE idUtilizador = ?";
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

        if(o.getClass().getName().equals("Business.UtilizadorRegistado")){
            UtilizadorRegistado u = (UtilizadorRegistado) o;
            int id = u.getId();
            UtilizadorRegistado ur = this.get(id);
            if(ur.equals(u)){ res = true; }
        }
        return res;
    }

    @Override
    public List<UtilizadorRegistado> get(Object o) {
        UtilizadorRegistado u = new UtilizadorRegistado();

        try{
            PreparedStatement ps = null;

                ps = c.prepareStatement("SELECT * FROM UtilizadorRegistado WHERE idUtilizador = ?");
                ps.setInt(1, (Integer) o);

            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                u.setId(rs.getInt("idUtilizador"));
                u.setNome(rs.getNString("nome"));
                u.setEmail(rs.getNString("email"));
                u.setPassword(rs.getNString("password"));
                u.setIdBiblioteca(rs.getInt("idBiblioteca"));
            }
        }
        catch(Exception e){
            System.out.printf(e.getMessage());
        }
        finally{ try{ Connect.close(c); } catch(Exception e){ System.out.printf(e.getMessage()); } }
        return u;
    }

    @Override
    public UtilizadorRegistado put(Integer k, List<UtilizadorRegistado> v) {
        UtilizadorRegistado u;

        if(this.containsKey(k)){
            u = this.get(k);
        }
        else u = v;
        try{
            c = Connect.connect();

            PreparedStatement ps = c.prepareStatement("INSERT INTO ProprietariosMusica (idUtilizador,idMusica) VALUES (?,?)");
            ps.setInt(1,k);
            ps.setString(2,v.getNome());
            ps.setString(3,v.getEmail());
            ps.setString(3,v.getPassword());
            ps.setInt(4, v.getIdBiblioteca());
            ps.executeUpdate();
        }
        catch(Exception e){ System.out.printf(e.getMessage()); }
        finally{ try{ Connect.close(c); } catch(Exception e){ System.out.printf(e.getMessage()); } }
        return u;
    }

    @Override
    public UtilizadorRegistado remove(Object o) { throw new UnsupportedOperationException("Erro!"); }

    @Override
    public void putAll(Map<? extends Integer, ? extends List<UtilizadorRegistado>> map) {

    }



    @Override
    public void clear() {
        try{
            c = Connect.connect();
            PreparedStatement ps = c.prepareStatement("DELETE FROM UtilizadorRegistado");
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
            PreparedStatement ps = c.prepareStatement("SELECT idUtilizador FROM UtilizadorRegistado");
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
    public Collection<List<UtilizadorRegistado>> values()
    {
        Set<UtilizadorRegistado> u = new HashSet<>();
        Set<String> keys = new HashSet<>(this.keySet());

        for(String k : keys){u.add(this.get(k));}
        return u;
    }

    @Override
    public Set<Entry<Integer, List<UtilizadorRegistado>>> entrySet()
    {
        Set<String> keys = new HashSet<>(this.keySet());
        Map<String,UtilizadorRegistado> map = new HashMap<>();

        for(String k : keys){ map.put(k,this.get(k));}
        return map.entrySet();
    }


}
