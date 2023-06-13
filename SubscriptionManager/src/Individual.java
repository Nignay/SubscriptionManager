import java.util.ArrayList;

public class Individual extends Aplicativo {
    private double precoMensal;

    private ArrayList <Usuario> pessoasAssinam;

    public Individual(String nome, String dataCobranca, double precoMensal) {
        super(nome, dataCobranca);
        this.precoMensal = precoMensal;
        this.pessoasAssinam = new ArrayList<>();
    }

    public void addAssinante(Usuario assinante){
        this.pessoasAssinam.add(assinante);
    }

    public ArrayList<Usuario> getListaAssinantes(){
        return pessoasAssinam;
    }

    public double getPrecoMensal() {
        return precoMensal;
    }

}