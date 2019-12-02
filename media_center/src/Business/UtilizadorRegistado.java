package Business;

/**
 * Representação da classe UtilizadorRegistado, subclasse de Utilizador
 **/
public class UtilizadorRegistado extends Utilizador
{
    /**
     * Construtor da classe UtilizadorRegistado sem parâmetros.
     */
    public UtilizadorRegistado()
    {
        super();
    }

    /**
     * Construtor da classe UtilizadorRegistado com paramêtros.
     * @param id        id do administrador
     * @param nome      nome do administrador
     * @param email     email do administrador
     * @param password  password do administrador
     */
    public UtilizadorRegistado(int id, String nome, String email, String password)
    {
        super(id,nome,email,password);
    }

    /**
     * Construtor por cópia da classe UtilizadorRegistado.
     * @param u     Utilizador
     */
    public UtilizadorRegistado(Utilizador u)
    {
        super(u.getId(), u.getNome(), u.getEmail(), u.getPassword());
    }

    /**
     * Método que altera a password de um utilizador
     * @return    Cópia do utilizador
     */
    public UtilizadorRegistado clone() {
        UtilizadorRegistado u =  new  UtilizadorRegistado(this);
        return u;
    }

}
