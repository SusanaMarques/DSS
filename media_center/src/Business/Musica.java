package Business;

/**
 * Representação da classe Musica, subclasse de Conteudo
 **/
public class Musica extends Conteudo
{
    /**
     * Construtor da classe Musica  sem parâmetros.
     */
    public Musica ()
    {
        super();
    }

    /**
     * Construtor da classe Musica  com paramêtros.
     * @param id          id do conteudo
     * @param nome        nome do conteudo
     * @param duracao     duracao do conteudo
     * @param formato     formato do conteudo
     * @param categoria   categoria do conteudo
     */
    public Musica(int id, String nome, double duracao, String formato, String categoria)
    {
        super(id,nome,duracao,formato,categoria);
    }

    /**
     * Construtor por cópia da classe Musica.
     * @param m     Musica
     */
    public Musica(Musica m)
    {
        super(m.getId(), m.getNome(), m.getDuracao(), m.getFormato(), m.getCategoria());
    }

    /**
     * Construtor por cópia da classe Musica.
     * @return    Cópia do objeto Musica
     */
    public Musica clone() {
        Musica m =  new  Musica(this);
        return m;
    }
}
