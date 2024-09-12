package model;

public class PessoaFisica extends Pessoa {
    private String cpf;
    private int idade;

    // Construtor padrão
    public PessoaFisica() {}

    // Construtor completo
    public PessoaFisica(int id, String nome, String cpf, int idade) {
        super(id, nome); // Chama o construtor da superclasse
        this.cpf = cpf;
        this.idade = idade;
    }

    // Getters e Setters
    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    // Método exibir polimórfico
    @Override
    public void exibir() {
        super.exibir(); // Chama o método exibir da classe base
        System.out.println("CPF: " + cpf + ", Idade: " + idade);
    }
}