import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class GerenciadorDeContatos {
    private Map<String, String> contatos = new HashMap<>();

    public void adicionarContato(String nome, String numeroTelefone) {
        contatos.put(nome, numeroTelefone);
    }

    public void removerContato(String nome) {
        contatos.remove(nome);
    }

    public void buscarContato(String nome) {
        String numeroTelefone = contatos.get(nome);
        if (numeroTelefone != null) {
            System.out.println("Número de telefone de " + nome + ": " + numeroTelefone);
        } else {
            System.out.println("Contato não encontrado: " + nome);
        }
    }

    public void listarContatos() {
        System.out.println("Lista de contatos:");
        for (HashMap.Entry<String, String> entry : contatos.entrySet()) {
            String nome = entry.getKey();
            String numeroTelefone = entry.getValue();
            System.out.println(nome + ": " + numeroTelefone);
        }
    }

    public static void main(String[] args) {
        GerenciadorDeContatos gerenciador = new GerenciadorDeContatos();
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