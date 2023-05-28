import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class GerenciadorDeContatos implements Serializable {
    private Map<String, Contato> contatos = new HashMap<>();
    private static final String NOME_ARQUIVO = "contatos.ser";

    public void adicionarContato(String nome, String numeroTelefone) {
        Contato contato = new Contato(nome, numeroTelefone);
        contatos.put(nome, contato);
        salvarContatosNoDisco();
    }

    public void removerContato(String nome) {
        contatos.remove(nome);
        salvarContatosNoDisco();
    }

    public void buscarContato(String nome) {
        Contato contato = contatos.get(nome);
        if (contato != null) {
            contato.imprimirInformacoes();
        } else {
            System.out.println("Contato não encontrado: " + nome);
        }
    }

    public void listarContatos() {
        System.out.println("Lista de contatos:");
        System.out.println(" ");
        for (Map.Entry<String, Contato> entry : contatos.entrySet()) {
            String nome = entry.getKey();
            Contato contato = entry.getValue();
            contato.imprimirInformacoes();
        }
    }

    private void salvarContatosNoDisco() {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(NOME_ARQUIVO))) {
            outputStream.writeObject(contatos);
        } catch (IOException e) {
            System.out.println("Erro ao salvar contatos no disco: " + e.getMessage());
        }
    }

    private void carregarContatosDoDisco() {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(NOME_ARQUIVO))) {
            contatos = (Map<String, Contato>) inputStream.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo de contatos não encontrado. Será criado um novo arquivo.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Erro ao carregar contatos do disco: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        GerenciadorDeContatos gerenciador = new GerenciadorDeContatos();
        gerenciador.carregarContatosDoDisco();
        Scanner scanner = new Scanner(System.in);

        int opcao;
        do {
            System.out.println("========================");
            System.out.println("|         MENU        |");
            System.out.println("1. Adicionar um contato");
            System.out.println("2. Remover um contato");
            System.out.println("3. Buscar um contato");
            System.out.println("4. Lista de contatos");
            System.out.println("5. Sair");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    System.out.print("Nome do contato: ");
                    String nome = scanner.next();
                    System.out.print("Número de telefone: ");
                    String numeroTelefone = scanner.next();
                    gerenciador.adicionarContato(nome, numeroTelefone);
                    System.out.println("Contato adicionado!");
                    break;
                case 2:
                    System.out.print("Nome do contato a ser removido: ");
                    String nomeRemover = scanner.next();
                    gerenciador.removerContato(nomeRemover);
                    System.out.println("Contato removido!");
                    break;
                case 3:
                    System.out.print("Nome do contato a ser buscado: ");
                    String nomeBuscar = scanner.next();
                    gerenciador.buscarContato(nomeBuscar);
                    break;
                case 4:
                    gerenciador.listarContatos();
                    break;
                case 5:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opcao != 5);
    }
}


