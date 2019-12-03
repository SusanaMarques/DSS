package Business;

public class MC {
   private GestaoConteudo gc = new GestaoConteudo();
   private GestaoUtilizador gu = new GestaoUtilizador();
   private int idUtilizadorAtual;
   private int idType;
    //tipo de utilizador: administrador:1; registado:2


    public MC(){}

    public boolean iniciarSessao(String mail,String pass){
        return (gu.iniciarSessao(mail, pass, idType));
    }

    public void setUserT(int idT){
        idType=idT;
    }

    public int getUserT(){
        return idType;
    }



}
