package Business;

import Database.AdministradorDAO;
import Database.UtilizadorRegistadoDAO;

import java.util.Map;

public class GestaoUtilizador {
    private Map<String,Administrador> admins = new AdministradorDAO ();
    private Map<String,UtilizadorRegistado> users = new UtilizadorRegistadoDAO();



    public boolean iniciarSessao(String email, String pass, int idOp){
        Utilizador u;
        boolean ret = false;
        if(idOp == 1) u=admins.get(email);
            else u=users.get(email);
            boolean e = u.getEmail().equals(email);
            boolean p = u.getPassword().equals(pass);
            System.out.println(u.getPassword() + "/" + pass );
        if( e && p ) ret =true;
        return ret;
    }
}
