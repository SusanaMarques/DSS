package Business;

import Database.AdministradorDAO;
import Database.UtilizadorRegistadoDAO;

import java.util.Map;

public class GestaoUtilizador {
    private Map<String,Administrador> admins = new AdministradorDAO();
    private Map<String,UtilizadorRegistado> users = new UtilizadorRegistadoDAO();



    public void iniciarSessao(String email, String pass, int idOp) throws CredenciaisInvalidasException {
        Utilizador u;
        boolean ret = false;
        if(idOp == 1) u=admins.get(email);
            else u=users.get(email);
            boolean e = u.getEmail().equals(email);
            boolean p = u.getPassword().equals(pass);
        if( !(e && p) ) throw new CredenciaisInvalidasException();

    }
}
