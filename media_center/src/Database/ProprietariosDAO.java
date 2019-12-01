package Database;

import Business.Utilizador;

import java.sql.Connection;
import java.util.Map;

public class ProprietariosDAO implements Map<String, Utilizador>
{
    private Connection c;
}
