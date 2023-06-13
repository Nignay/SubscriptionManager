import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import static java.lang.Double.parseDouble;
import static java.lang.Integer.parseInt;

public class Main {
    public static void main(String[] args) {
        ArrayList<Aplicativo> assinaturas = new ArrayList<>();
        ArrayList<Usuario> usuarios = new ArrayList<>();
        File saveFile = new File("Save.txt");
        File assinaturasFile = new File("Assinaturas.txt");
        Scanner scanner = new Scanner(System.in);

        if (saveFile.exists()) {
            try {
                Scanner saveScanner = new Scanner(saveFile);
                while (saveScanner.hasNextLine()) {
                    String linha = saveScanner.nextLine();
                    String[] parts = linha.split(",");

                    String nome = parts[0];
                    int idade = Integer.parseInt(parts[1]);
                    String email = parts[2];
                    String senha = parts[3];

                    if (senha.equals("0")) {
                        Usuario normalUser = new Normal(nome, idade, email);
                        usuarios.add(normalUser);
                    } else {
                        Usuario admUser = new ADM(nome, idade, email, senha);
                        usuarios.add(admUser);
                    }
                }
                saveScanner.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Por favor, crie um usuário");
            System.out.println("Qual é o seu nome?");
            String nome = scanner.nextLine();
            System.out.println("Qual é a sua idade?");
            int idade = Integer.parseInt(scanner.nextLine());
            System.out.println("Qual é o seu e-mail?");
            String email = scanner.nextLine();
            System.out.println("Qual é a sua senha? (digite 0 se for um usuário normal)");
            String senha = scanner.nextLine();

            if (senha.equals("0")) {
                Usuario normalUser = new Normal(nome, idade, email);
                usuarios.add(normalUser);
            } else {
                Usuario admUser = new ADM(nome, idade, email, senha);
                usuarios.add(admUser);
            }

            System.out.println("Deseja adicionar mais usuários normais? (S/N)");
            String opcao = scanner.nextLine();
            while (opcao.equalsIgnoreCase("S")) {
                System.out.println("Qual é o nome do usuário?");
                nome = scanner.nextLine();
                System.out.println("Qual é a idade do usuário?");
                idade = Integer.parseInt(scanner.nextLine());
                System.out.println("Qual é o e-mail do usuário?");
                email = scanner.nextLine();

                Usuario normalUser = new Normal(nome, idade, email);
                usuarios.add(normalUser);

                System.out.println("Deseja adicionar mais usuários normais? (S/N)");
                opcao = scanner.nextLine();
            }

            try {
                FileWriter saveWriter = new FileWriter(saveFile);
                for (Usuario usuario : usuarios) {
                    String linha = usuario.getNome() + "," + usuario.getIdade() + "," + usuario.getEmail();
                    if (usuario instanceof ADM) {
                        linha += "," + ((ADM) usuario).getSenha(); // Adiciona a senha se for um ADM
                    } else {
                        linha += ",0"; // Adiciona "0" se for um usuário normal
                    }
                    saveWriter.write(linha + System.lineSeparator());
                }
                saveWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (assinaturasFile.exists()) {
            try {
                Scanner assinaturasScanner = new Scanner(assinaturasFile);
                while (assinaturasScanner.hasNextLine()) {
                    String linha = assinaturasScanner.nextLine();
                    String[] parts = linha.split(",");

                    String nome = parts[0];
                    String formato = parts[1];
                    double precoMensal = Double.parseDouble(parts[2]);

                    if (formato.equalsIgnoreCase("Individual")) {
                        Individual individual = new Individual(nome, "", precoMensal);
                        assinaturas.add(individual);
                    } else if (formato.equalsIgnoreCase("Família")) {
                        int numeroUsuarios = Integer.parseInt(parts[3]);
                        Familia familia = new Familia(nome, numeroUsuarios, "", precoMensal);
                        assinaturas.add(familia);
                    }
                }

                assinaturasScanner.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("O arquivo de assinaturas não existe.");
        }

        for (Aplicativo assinatura : assinaturas) {
            if (assinatura instanceof Individual) {
                Individual individual = (Individual) assinatura;
                System.out.println("Assinatura Individual: " + individual.getNome());
                System.out.println("Digite o nome dos usuários que assinam essa assinatura (Digite 'fim' para parar):");
                String nomeUsuario = scanner.nextLine();
                while (!nomeUsuario.equalsIgnoreCase("fim")) {
                    for (Usuario usuario : usuarios) {
                        if (usuario.getNome().equalsIgnoreCase(nomeUsuario)) {
                            individual.addAssinante(usuario);
                            break;
                        }
                    }
                    System.out.println("Digite o nome dos usuários que tem essa assinatura (Digite 'fim' para parar):");
                    nomeUsuario = scanner.nextLine();
                }
            }
        }

        Iterator<Aplicativo> iterator = assinaturas.iterator();
        while (iterator.hasNext()) {
            Aplicativo assinatura = iterator.next();
            if (assinatura instanceof Familia) {
                System.out.println("Você assina " + assinatura.getNome() + " no pacote família? (Digite 'S'/'N')");
                String resposta = scanner.nextLine();
                if (resposta.equalsIgnoreCase("N")) {
                    iterator.remove();
                }
            }
        }

        for (Aplicativo assinatura : assinaturas) {
            System.out.println("Digite a data de cobrança para a assinatura " + assinatura.getNome() + ":");
            String dataCobranca = scanner.nextLine();
            assinatura.setDataCobranca(dataCobranca);
        }
        boolean sair = false;
        while (!sair) {
            System.out.println("Selecione uma opção:");
            System.out.println("1 - Relatório de Usuários");
            System.out.println("2 - Relatório Financeiro");
            System.out.println("3 - Sair do programa");

            int opcao = Integer.parseInt(scanner.nextLine());

            switch (opcao) {
                case 1:
                    System.out.println("Relatório de Usuários:");
                    for (Usuario usuario : usuarios) {
                        System.out.println("--------------------");
                        usuario.imprimirDados();
                        System.out.println("--------------------");
                    }
                    break;

                case 2:
                    System.out.println("Relatório Financeiro:");
                    for (Aplicativo assinatura : assinaturas) {
                        System.out.println("Assinatura: " + assinatura.getNome());
                        System.out.println("Data de cobrança dessa assinatura (dia do mês):" + assinatura.getDataCobranca());
                        if (assinatura instanceof Individual) {
                            Individual individual = (Individual) assinatura;
                            ArrayList<String> usuariosIndividuais = new ArrayList<>();
                            for (Usuario usuario : individual.getListaAssinantes()) {
                                usuariosIndividuais.add(usuario.getNome());
                            }
                            System.out.println("Usuários individuais que assinam: " + usuariosIndividuais);
                            System.out.println("Soma total da assinatura: R$" + individual.getPrecoMensal() * individual.getListaAssinantes().size());
                        } else if (assinatura instanceof Familia) {
                            Familia familia = (Familia) assinatura;
                            System.out.println("Número de usuários: " + familia.getNumeroUsuarios());
                            System.out.println("Soma total da assinatura: R$" + familia.getPrecoMensal());
                        }
                        System.out.println();
                    }
                    break;

                    case 3:
                        sair = true;
                        System.out.println("Encerrando o programa...");
                        break;

                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                        break;
                }
            }

            scanner.close();
        }
    }