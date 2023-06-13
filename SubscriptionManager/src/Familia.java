public class Familia extends Aplicativo {
    private int numeroUsuarios;
    private double precoMensal;

    public Familia(String nome, int numeroUsuarios, String dataCobranca, double precoMensal) {
        super(nome, dataCobranca);
        this.numeroUsuarios = numeroUsuarios;
        this.precoMensal = precoMensal;
    }

    public int getNumeroUsuarios() {
        return numeroUsuarios;
    }


    public double getPrecoMensal() {
        return precoMensal;
    }

}
