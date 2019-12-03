package Business;

/**
 * Representação da classe Video, subclasse de Conteudo
 **/
public class Video extends Conteudo
{
    private String realizador;

    /**
     * Construtor da classe Video  sem parâmetros.
     */
    public Video()
    {

        super();
        this.realizador = "";
    }

    /**
     * Construtor da classe Video  com paramêtros.
     * @param id          id do video
     * @param nome        nome do video
     * @param duracao     duracao do video
     * @param formato     formato do video
     * @param categoria   categoria do video
     * @param realizador  realizador do video
     */
    public Video(int id, String nome, double duracao, String formato, String categoria, String realizador)
    {
        super(id,nome,duracao,formato,categoria);
        this.realizador = realizador;
    }

    /**
     * Construtor por cópia da classe Video.
     * @param v    Video
     */
    public Video(Video v)
    {
        super(v.getId(), v.getNome(), v.getDuracao(), v.getFormato(), v.getCategoria());
        this.realizador = v.getRealizador();
    }

    /**
     * Método que devolve o realizador do video.
     * @return realizador do video
     */
    public String getRealizador() { return this.realizador; }

    /**
     * Método que altera o realizador do conteudo
     * @param r  Nova categoria do conteudo
     */
    public void setRealizador(String r){this.realizador = r;}

    /**
     * Construtor por cópia da classe Video.
     * @return    Cópia do objeto Video
     */
    public Video clone() {
        Video v =  new  Video(this);
        return v;
    }
}
