public abstract class Usuario {
    private String nome;
    private int idade;
    private String email;

    public Usuario(String nome, int idade, String email) {
        this.nome = nome;
        this.idade = idade;
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public int getIdade() {
        return idade;
    }

    public String getEmail() {
        return email;
    }

    // Método abstrato para ser implementado pelas subclasses
    public abstract void imprimirDados();
}