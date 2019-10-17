package sample;

public class Utilizador {

    private int designação; // 0=convidado; 1= administrador; 2=registado


    public Utilizador(int designação) {
        this.designação = designação;
    }


    public int getDesignação() {
        return designação;
    }

    public void setDesignação(int designação) {
        this.designação = designação;
    }
}
