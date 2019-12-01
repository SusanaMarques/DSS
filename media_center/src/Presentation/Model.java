package Presentation;


public class Model {

    //tipo de utilizador: administrador:1; registado:2
    private int idType;

    public Model(){}

    public void setUserT(int idUser){
        idType=idUser;
    }

    public int getUserT(){
        return idType;
    }

    public boolean checkLogin(){
        return true;
    }


}
