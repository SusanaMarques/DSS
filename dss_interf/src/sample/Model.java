package sample;

public class Model {
    int idType;

    public Model(){}

    public void setUserT(int id){
        idType=id;
    }
    public int getUserT(){
        return idType;
    }
    public boolean checkLogin(){
        return true;
    }
}
