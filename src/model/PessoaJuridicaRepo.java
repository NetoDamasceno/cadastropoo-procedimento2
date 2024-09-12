package model;

import java.io.*;
import java.util.ArrayList;
import java.util.Optional;

public class PessoaJuridicaRepo {

    private ArrayList<PessoaJuridica> listaPessoasJuridicas;

    // Construtor inicializando a lista
    public PessoaJuridicaRepo() {
        this.listaPessoasJuridicas = new ArrayList<>();
    }

    // Método para inserir uma nova PessoaJuridica
    public void inserir(PessoaJuridica pessoa) {
        listaPessoasJuridicas.add(pessoa);
    }

    // Método para alterar uma PessoaJuridica existente
    public void alterar(PessoaJuridica pessoa) {
        Optional<PessoaJuridica> existente = obter(pessoa.getId());
        existente.ifPresent(p -> {
            p.setNome(pessoa.getNome());
            p.setCnpj(pessoa.getCnpj());
        });
    }

    // Método para excluir uma PessoaJuridica pelo id
    public void excluir(int id) {
        listaPessoasJuridicas.removeIf(p -> p.getId() == id);
    }

    // Método para obter uma PessoaJuridica pelo id
    public Optional<PessoaJuridica> obter(int id) {
        return listaPessoasJuridicas.stream().filter(p -> p.getId() == id).findFirst();
    }

    // Método para obter todas as PessoaJuridica
    public ArrayList<PessoaJuridica> obterTodos() {
        return listaPessoasJuridicas;
    }

    // Método para persistir os dados em um arquivo binário
    public void persistir(String nomeArquivo) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nomeArquivo))) {
            oos.writeObject(listaPessoasJuridicas);
        }
    }

    // Método para recuperar os dados de um arquivo binário
    public void recuperar(String nomeArquivo) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nomeArquivo))) {
            listaPessoasJuridicas = (ArrayList<PessoaJuridica>) ois.readObject();
        }
    }
}