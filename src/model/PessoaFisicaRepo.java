package model;

import java.io.*;
import java.util.ArrayList;
import java.util.Optional;

public class PessoaFisicaRepo {

    private ArrayList<PessoaFisica> listaPessoasFisicas;

    // Construtor inicializando a lista
    public PessoaFisicaRepo() {
        this.listaPessoasFisicas = new ArrayList<>();
    }

    // Método para inserir uma nova PessoaFisica
    public void inserir(PessoaFisica pessoa) {
        listaPessoasFisicas.add(pessoa);
    }

    // Método para alterar uma PessoaFisica existente
    public void alterar(PessoaFisica pessoa) {
        Optional<PessoaFisica> existente = obter(pessoa.getId());
        existente.ifPresent(p -> {
            p.setNome(pessoa.getNome());
            p.setCpf(pessoa.getCpf());
            p.setIdade(pessoa.getIdade());
        });
    }

    // Método para excluir uma PessoaFisica pelo id
    public void excluir(int id) {
        listaPessoasFisicas.removeIf(p -> p.getId() == id);
    }

    // Método para obter uma PessoaFisica pelo id
    public Optional<PessoaFisica> obter(int id) {
        return listaPessoasFisicas.stream().filter(p -> p.getId() == id).findFirst();
    }

    // Método para obter todas as PessoaFisica
    public ArrayList<PessoaFisica> obterTodos() {
        return listaPessoasFisicas;
    }

    // Método para persistir os dados em um arquivo binário
    public void persistir(String nomeArquivo) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nomeArquivo))) {
            oos.writeObject(listaPessoasFisicas);
        }
    }

    // Método para recuperar os dados de um arquivo binário
    public void recuperar(String nomeArquivo) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nomeArquivo))) {
            listaPessoasFisicas = (ArrayList<PessoaFisica>) ois.readObject();
        }
    }
}