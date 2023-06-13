public class Aplicativo {
    private String nome;
    public String dataCobranca;

    public Aplicativo(String nome, String dataCobranca) {
        this.nome = nome;
        this.dataCobranca = dataCobranca;
    }

    public String getNome() {
        return nome;
    }

    public void setDataCobranca(String dataCobranca) {
        this.dataCobranca = dataCobranca;
    }

    public String getDataCobranca() {
        return dataCobranca;
    }
}