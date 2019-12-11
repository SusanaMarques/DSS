package Database;

import Business.UtilizadorRegistado;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

public class ProprietariosVideoDAO implements Map<Integer, List<UtilizadorRegistado>>
{
    private Connection c;


    @Override
    public int size() { throw new NullPointerException("Not implemented!");}

    @Override
    public boolean isEmpty() {
        throw new NullPointerException("Not implemented!");
    }

    /**
     * Método que verifica se um utilizador é proprietário do conteudo
     * @param o                      Objeto a verficar
     * @return                       True se for proprietário do conteudo
     * @throws NullPointerException  Não existe conexão com a base de dados
     */
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
    public boolean containsValue(Object o) { throw new NullPointerException("Not implemented!"); }

    /**
     * Método que verifica se um determinado utilizador é proprietário de algum conteudo
     * @param o                      Objeto a verficar
     * @return                       True se o utilizador for proprietário de algum conteúdo
     * @throws NullPointerException  Não existe conexão com a base de dados
     */
    @Override
    public List<UtilizadorRegistado> get(Object o) {
        UtilizadorRegistado u;
        ArrayList<UtilizadorRegistado> array = new ArrayList<UtilizadorRegistado>();

        try{
            c = Connect.connect();
            String sql = "SELECT * FROM UtilizadorRegistado WHERE idUtilizador = ?";
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setInt(1, (Integer) o);
            ResultSet rs = ps.executeQuery();
            while(rs.next())
            {
                u = new UtilizadorRegistado(rs.getInt("idUtilizador"), rs.getString("nome"), rs.getString("email"), rs.getString("password"), rs.getInt("idBibliotecaMusica"),rs.getInt("idBibliotecaVideo"));
                array.add(u);
            }
        }
        catch(Exception e){ System.out.printf(e.getMessage()); } finally{ try{ Connect.close(c); } catch(Exception e){ System.out.printf(e.getMessage()); } }
        return array;
    }


    /**
     * Método que insere um novo proprietário de conteudos na base de dados
     * @param k      id do conteudo
     * @param v      lista proprietarios
     * @return
     */
    @Override
    public List<UtilizadorRegistado> put(Integer k, List<UtilizadorRegistado> v) {
        UtilizadorRegistado u;
        ArrayList<UtilizadorRegistado> array = new ArrayList<UtilizadorRegistado>();

        try{
            c = Connect.connect();

            PreparedStatement ps = c.prepareStatement("INSERT INTO ProprietariosVideo (idVideo,idUtilizador) VALUES (?,?)");
            for(UtilizadorRegistado user : v)
            {
                ps.setInt(1,k);
                ps.setInt(2, user.getId());
            }
        }
        catch(Exception e){ System.out.printf(e.getMessage()); } finally{ try{ Connect.close(c); } catch(Exception e){ System.out.printf(e.getMessage()); } }
        return array;
    }

    @Override
    public List<UtilizadorRegistado> remove(Object o) { throw new UnsupportedOperationException("Not Implemented"); }

    @Override
    public void putAll(Map<? extends Integer, ? extends List<UtilizadorRegistado>> map) { throw new UnsupportedOperationException("Not Implemented"); }

    @Override
    public void clear() { throw new UnsupportedOperationException("Not Implemented"); }

    @Override
    public Set<Integer> keySet() { throw new UnsupportedOperationException("Not Implemented"); }

    @Override
    public Collection<List<UtilizadorRegistado>> values() {throw new UnsupportedOperationException("Not Implemented");}

    @Override
    public Set<Map.Entry<Integer, List<UtilizadorRegistado>>> entrySet() {throw new UnsupportedOperationException("Not Implemented"); }
}