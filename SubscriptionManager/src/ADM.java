public class ADM extends Usuario {
    private String senha;

    public ADM(String nome, int idade, String email, String senha) {
        super(nome, idade, email);
        this.senha = senha;
    }

    public String getSenha() {
        return senha;
    }

    @Override
    public void imprimirDados() {
        System.out.println("Usuário ADM:");
        System.out.println("Nome: " + getNome());
        System.out.println("Idade: " + getIdade());
        System.out.println("Email: " + getEmail());
        System.out.println("Senha: " + getSenha());
    }
}