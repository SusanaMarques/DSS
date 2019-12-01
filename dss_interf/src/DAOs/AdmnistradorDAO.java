package DAOs;

import business.*;
import java.util.Map;
import java.util.Set;
import java.util.Collection;
import java.util.HashSet;
import java.sql.*;

public class AdmnistradorDAO implements Map<String,Utilizador>
{
    private AdmnistradorDAO inst = null;

    // load the driver’s class file into memory at runtime. No need of using new or creation of object
    private AdmnistradorDAO ()
    {
        try { Class.forName("sun.jdbc.odbc.JdbcOdbcDriver"); }
        catch (ClassNotFoundException e) {throw new NullPointerException(e.getMessage());}
    }

    public static AdmnistradorDAO getInstance()
    {
        if (inst == null) {
            inst = new AdmnistradorDAO();
        }
        return inst;
    }


}
