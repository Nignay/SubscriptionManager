public class Normal extends Usuario {
    public Normal(String nome, int idade, String email) {
        super(nome, idade, email);
    }

    @Override
    public void imprimirDados() {
        System.out.println("Usuário Normal:");
        System.out.println("Nome: " + getNome());
        System.out.println("Idade: " + getIdade());
        System.out.println("Email: " + getEmail());
    }
}

