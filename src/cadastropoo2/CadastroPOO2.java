package cadastropoo2;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Optional;
import java.util.Scanner;
import model.PessoaFisica;
import model.PessoaJuridica;
import model.PessoaFisicaRepo;
import model.PessoaJuridicaRepo;

public class CadastroPOO2 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PessoaFisicaRepo pfRepo = new PessoaFisicaRepo();
        PessoaJuridicaRepo pjRepo = new PessoaJuridicaRepo();
        String tipo;

        int opcao = -1;
        do {
            System.out.println("====================");
            System.out.println("1 - Incluir Pessoa");
            System.out.println("2 - Alterar Pessoa");
            System.out.println("3 - Excluir Pessoa");
            System.out.println("4 - Buscar pelo Id");
            System.out.println("5 - Exibir Todos");
            System.out.println("6 - Persistir Dados");
            System.out.println("7 - Recuperar Dados");
            System.out.println("0 - Finalizar Programa");
            System.out.println("====================");

            while (!sc.hasNextInt()) {
                System.out.println("Entrada inválida. Por favor, insira um número.");
                sc.next(); // Consumir a entrada inválida
            }
            opcao = sc.nextInt();
            sc.nextLine(); // Consumir a nova linha

            switch (opcao) {
                case 1:
                    System.out.println("F - Pessoa Fisica | J - Pessoa Juridica");
                    tipo = sc.nextLine();
                    if (tipo.equalsIgnoreCase("F")) {
                        System.out.println("Digite o id da pessoa:");
                        if (sc.hasNextInt()) {
                            int id = sc.nextInt();
                            sc.nextLine(); // Consumir nova linha
                            System.out.println("Digite o nome da pessoa:");
                            String nome = sc.nextLine();
                            System.out.println("Digite o CPF da pessoa:");
                            String cpf = sc.nextLine();
                            System.out.println("Digite a idade da pessoa:");
                            if (sc.hasNextInt()) {
                                int idade = sc.nextInt();
                                sc.nextLine(); // Consumir nova linha

                                PessoaFisica pf = new PessoaFisica(id, nome, cpf, idade);
                                pfRepo.inserir(pf);
                                System.out.println("Pessoa física incluída com sucesso!");
                            } else {
                                System.out.println("Erro: A idade deve ser um número inteiro.");
                                sc.nextLine(); // Consumir erro
                            }
                        } else {
                            System.out.println("Erro: O id deve ser um número inteiro.");
                            sc.nextLine(); // Consumir erro
                        }
                    } else if (tipo.equalsIgnoreCase("J")) {
                        System.out.println("Digite o id da empresa:");
                        if (sc.hasNextInt()) {
                            int id = sc.nextInt();
                            sc.nextLine(); // Consumir nova linha
                            System.out.println("Digite o nome da empresa:");
                            String nome = sc.nextLine();
                            System.out.println("Digite o CNPJ da empresa:");
                            String cnpj = sc.nextLine();

                            PessoaJuridica pj = new PessoaJuridica(id, nome, cnpj);
                            pjRepo.inserir(pj);
                            System.out.println("Pessoa jurídica incluída com sucesso!");
                        } else {
                            System.out.println("Erro: O id deve ser um número inteiro.");
                            sc.nextLine(); // Consumir erro
                        }
                    }
                    break;

                case 2: // Alterar Pessoa
                    System.out.println("F - Pessoa Fisica | J - Pessoa Juridica");
                    tipo = sc.nextLine();
                    if (tipo.equalsIgnoreCase("F")) {
                        System.out.println("Digite o id da pessoa que deseja alterar:");
                        if (sc.hasNextInt()) {
                            int id = sc.nextInt();
                            sc.nextLine(); // Consumir nova linha
                            Optional<PessoaFisica> optionalPf = pfRepo.obter(id);
                            if (optionalPf.isPresent()) {
                                PessoaFisica pf = optionalPf.get();
                                System.out.println("Dados atuais: ");
                                pf.exibir();
                                System.out.println("Digite o novo nome da pessoa:");
                                String nome = sc.nextLine();
                                System.out.println("Digite o novo CPF da pessoa:");
                                String cpf = sc.nextLine();
                                System.out.println("Digite a nova idade da pessoa:");
                                if (sc.hasNextInt()) {
                                    int idade = sc.nextInt();
                                    sc.nextLine(); // Consumir nova linha

                                    pf.setNome(nome);
                                    pf.setCpf(cpf);
                                    pf.setIdade(idade);
                                    pfRepo.alterar(pf);
                                    System.out.println("Pessoa física alterada com sucesso!");
                                } else {
                                    System.out.println("Erro: A idade deve ser um número inteiro.");
                                    sc.nextLine(); // Consumir erro
                                }
                            } else {
                                System.out.println("Pessoa não encontrada.");
                            }
                        } else {
                            System.out.println("Erro: O id deve ser um número inteiro.");
                            sc.nextLine(); // Consumir erro
                        }
                    } else if (tipo.equalsIgnoreCase("J")) {
                        System.out.println("Digite o id da empresa que deseja alterar:");
                        if (sc.hasNextInt()) {
                            int id = sc.nextInt();
                            sc.nextLine(); // Consumir nova linha
                            Optional<PessoaJuridica> optionalPj = pjRepo.obter(id);
                            if (optionalPj.isPresent()) {
                                PessoaJuridica pj = optionalPj.get();
                                System.out.println("Dados atuais: ");
                                pj.exibir();
                                System.out.println("Digite o novo nome da empresa:");
                                String nome = sc.nextLine();
                                System.out.println("Digite o novo CNPJ da empresa:");
                                String cnpj = sc.nextLine();

                                pj.setNome(nome);
                                pj.setCnpj(cnpj);
                                pjRepo.alterar(pj);
                                System.out.println("Pessoa jurídica alterada com sucesso!");
                            } else {
                                System.out.println("Empresa não encontrada.");
                            }
                        } else {
                            System.out.println("Erro: O id deve ser um número inteiro.");
                            sc.nextLine(); // Consumir erro
                        }
                    }
                    break;

                case 3: // Excluir Pessoa
                    System.out.println("F - Pessoa Fisica | J - Pessoa Juridica");
                    tipo = sc.nextLine();
                    if (tipo.equalsIgnoreCase("F")) {
                        System.out.println("Digite o id da pessoa que deseja excluir:");
                        if (sc.hasNextInt()) {
                            int id = sc.nextInt();
                            sc.nextLine(); // Consumir nova linha
                            pfRepo.excluir(id);
                            System.out.println("Pessoa física excluída com sucesso!");
                        } else {
                            System.out.println("Erro: O id deve ser um número inteiro.");
                            sc.nextLine(); // Consumir erro
                        }
                    } else if (tipo.equalsIgnoreCase("J")) {
                        System.out.println("Digite o id da empresa que deseja excluir:");
                        if (sc.hasNextInt()) {
                            int id = sc.nextInt();
                            sc.nextLine(); // Consumir nova linha
                            pjRepo.excluir(id);
                            System.out.println("Pessoa jurídica excluída com sucesso!");
                        } else {
                            System.out.println("Erro: O id deve ser um número inteiro.");
                            sc.nextLine(); // Consumir erro
                        }
                    }
                    break;

                case 4: // Buscar Pessoa pelo Id
                    System.out.println("F - Pessoa Fisica | J - Pessoa Juridica");
                    tipo = sc.nextLine();
                    if (tipo.equalsIgnoreCase("F")) {
                        System.out.println("Digite o id da pessoa:");
                        if (sc.hasNextInt()) {
                            int id = sc.nextInt();
                            sc.nextLine(); // Consumir nova linha
                            Optional<PessoaFisica> optionalPf = pfRepo.obter(id);
                            if (optionalPf.isPresent()) {
                                optionalPf.get().exibir();
                            } else {
                                System.out.println("Pessoa não encontrada.");
                            }
                        } else {
                            System.out.println("Erro: O id deve ser um número inteiro.");
                            sc.nextLine(); // Consumir erro
                        }
                    } else if (tipo.equalsIgnoreCase("J")) {
                        System.out.println("Digite o id da empresa:");
                        if (sc.hasNextInt()) {
                            int id = sc.nextInt();
                            sc.nextLine(); // Consumir nova linha
                            Optional<PessoaJuridica> optionalPj = pjRepo.obter(id);
                            if (optionalPj.isPresent()) {
                                optionalPj.get().exibir();
                            } else {
                                System.out.println("Empresa não encontrada.");
                            }
                        } else {
                            System.out.println("Erro: O id deve ser um número inteiro.");
                            sc.nextLine(); // Consumir erro
                        }
                    }
                    break;

                case 5: // Exibir Todos
                    System.out.println("Pessoas Físicas:");
                    for (PessoaFisica pf : pfRepo.obterTodos()) {
                        pf.exibir();
                    }
                    System.out.println("Pessoas Jurídicas:");
                    for (PessoaJuridica pj : pjRepo.obterTodos()) {
                        pj.exibir();
                    }
                    break;

                case 6: // Persistir Dados
                    try {
                        pfRepo.persistir("pessoas_fisicas.dat");
                        pjRepo.persistir("pessoas_juridicas.dat");
                        System.out.println("Dados persistidos com sucesso!");
                    } catch (Exception e) {
                        System.out.println("Erro ao persistir dados: " + e.getMessage());
                    }
                    break;

                case 7: // Recuperar Dados
                    try {
                        pfRepo.recuperar("pessoas_fisicas.dat");
                        pjRepo.recuperar("pessoas_juridicas.dat");
                        System.out.println("Dados recuperados com sucesso!");
                    } catch (Exception e) {
                        System.out.println("Erro ao recuperar dados: " + e.getMessage());
                    }
                    break;

                case 0:
                    System.out.println("Programa finalizado.");
                    break;

                default:
                    System.out.println("Opção inválida.");
                    break;
            }
        } while (opcao != 0);
        sc.close();
    }
}