package Business;

/**
 * Representação da classe UtilizadorRegistado, subclasse de Utilizador
 **/
public class UtilizadorRegistado extends Utilizador
{
    private int idBiblioteca;
    /**
     * Construtor da classe UtilizadorRegistado sem parâmetros.
     */
    public UtilizadorRegistado()
    {

        super();
        this.idBiblioteca = 0;
    }

    /**
     * Construtor da classe UtilizadorRegistado com paramêtros.
     * @param id        id do administrador
     * @param nome      nome do administrador
     * @param email     email do administrador
     * @param password  password do administrador
     */
    public UtilizadorRegistado(int id, String nome, String email, String password, int idBiblioteca)

    {
        super(id,nome,email,password);
        this.idBiblioteca = idBiblioteca;
    }

    /**
     * Construtor por cópia da classe UtilizadorRegistado.
     * @param u     Utilizador
     */
    public UtilizadorRegistado(UtilizadorRegistado u)
    {

        super(u.getId(), u.getNome(), u.getEmail(), u.getPassword());
        this.idBiblioteca = u.getIdBiblioteca();
    }

    /**
     * Método que devolve o id da biblioteca pessoal do utilizador.
     * @return     Id da biblioteca pessoal do utilizador.
     */
    public int getIdBiblioteca(){return this.idBiblioteca;}

    /**
     * Método que altera o id da biblioteca pessoal do utilizador
     * @param id    Novo id do conteudo
     */
    public void setIdBiblioteca(int id){this.idBiblioteca = id;}


    /**
     * Construtor por cópia da classe UtilizadorRegistado.
     * @return    Cópia do utilizador
     */
    public UtilizadorRegistado clone() {
        UtilizadorRegistado u =  new  UtilizadorRegistado(this);
        return u;
    }

}
