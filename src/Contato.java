import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

class Contato implements Serializable {
    private String nome;
    private String numeroTelefone;
    private LocalDateTime dataCriacao;

    public Contato(String nome, String numeroTelefone) {
        this.nome = nome;
        this.numeroTelefone = numeroTelefone;
        this.dataCriacao = LocalDateTime.now();
    }

    public void imprimirInformacoes() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String dataFormatada = dataCriacao.format(formatter);
        System.out.println("Nome: " + nome);
        System.out.println("Número de telefone: " + numeroTelefone);
        System.out.println("Data de criação: " + dataFormatada);
        System.out.println("----");
    }
}
