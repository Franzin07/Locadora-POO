package com.sexo.app;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


import com.sexo.models.Filmes;
import com.sexo.models.FilmesAlugados;
import com.sexo.models.SistemaDeEstoque;


public class Main { 
    private static SistemaDeEstoque estoque = new SistemaDeEstoque();
    private static Scanner scanner = new Scanner(System.in);
    private static List<String> filmes;
    public static void main(String[] args) {
       
         boolean executando = true;

          while (executando) {
            exibirMenu();
            int opcao = lerOpcao();
            switch (opcao) {
                
                case 1:
                   verEstoque();
                    break;
                case 2:
                  //  ();
                    break;
                case 3:
                    deletarFilme();
                    break;
                case 4:
                   buscaNomeFilmes();
                    break;
                case 5:
                
                    break;
                case 6:
                adicionarFilme();
                    break;
                case 0:
                    executando = false;
                    System.out.println("Finalizando o programa!");
                    break;
                default:
                    System.out.println("Opção inválida. Por favor, tente novamente.");
            }
        
            // Pausa para o usuário ler a saída
            if (executando) {
                System.out.println("\nPressione ENTER para continuar...");
                scanner.nextLine();
            }
        }
        
        scanner.close();
    }
     
    private static int lerOpcao() {
        try {
            int opcao = Integer.parseInt(scanner.nextLine());
            return opcao;
        } catch (NumberFormatException e) {
            return -1; // Retorna opção inválida
        }
    }
    
    public static void exibirMenu(){
        System.out.println("\n ----------------- [ Locadora de Filmes 1990 ] ---------------------------");
        System.out.println("1 - Listar filmes disponiveis ");
        System.out.println("2 - Atualizar quantidade em estoque");
        System.out.println("3 - Remover filme");
        System.out.println("4 - Buscar filme por nome");
        System.out.println("5 - Alugar filme");
        System.out.println("6 - Adicionar filme");
        System.out.println("0 - Sair");
        System.out.print("Escolha uma opção: ");
    }
    
    private static void verEstoque() {
    System.out.println("\n----------------- [ Filmes Em Estoque ] ---------------------------");

    if (estoque.getEstoque().isEmpty()) {
        System.out.println("Nenhum filme disponível no estoque.");
        return;
    }

    int i = 1;
    for (Filmes filme : estoque.getEstoque()) {
        System.out.printf("   Nome: %d. %s\n", i, filme.getNome());
        System.out.printf("   Avaliação: %.1f | Preço: R$ %.2f\n", filme.getAvaliacao(), filme.getPreco());
        System.out.println("   Elenco: " + filme.getElenco() + " | Sinopse: " + filme.getSinopse());
         i++;
    }
}

    private static void adicionarFilme() {
        System.out.println("\n----------------- [ Cadastro de filmes ] ---------------------------");
    
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
    
        System.out.print("Sinopse: ");
        String sinopse = scanner.nextLine();
        float avaliacao = -1;
        while (avaliacao < 1 || avaliacao > 5) {
            System.out.print("Avaliação (1 a 5): ");
            try {
                avaliacao = Float.parseFloat(scanner.nextLine());
                if (avaliacao < 1 || avaliacao > 5) {
                    System.out.println("Por favor, digite um número entre 1 e 5.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Digite um número decimal entre 1 e 5.");
            }
        }
    
        System.out.print("Elenco: ");
        String elenco = scanner.nextLine();
    
        System.out.print("Preço: ");
        double preco;
        try {
            preco = Double.parseDouble(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Preço inválido, usando 0.");
            preco = 0;
        }
    
        Filmes filme = new Filmes(nome, sinopse, avaliacao, elenco, preco);
        estoque.adicionarFilme(filme);
    
        System.out.println("Filme adicionado com sucesso!");
    }
     public static void buscaNomeFilmes() {
    List<Filmes> filmes = estoque.getEstoque();

    if (filmes.isEmpty()) {
        System.out.println("Nenhum filme disponível para busca.");
        return;
    }

    System.out.println("------------------[ Buscar Filme ]-------------------");
    System.out.print("Digite o nome ou parte do nome do filme a ser buscado: ");
    String entrada = scanner.nextLine().trim();

    if (entrada.isEmpty()) {
        System.out.println("Entrada vazia! Por favor, digite um nome válido.");
        return;
    }

    // Lista para armazenar os filmes que correspondem à busca
    List<Filmes> encontrados = new ArrayList<>();

    // Buscar filmes que contenham o termo (ignorando maiúsculas/minúsculas)
    for (Filmes filme : filmes) {
        if (filme.getNome().toLowerCase().contains(entrada.toLowerCase())) {
            encontrados.add(filme);
        }
    }

    // Mostrar resultados
    if (encontrados.isEmpty()) {
        System.out.println("Nenhum filme encontrado com o nome: \"" + entrada + "\"");
    } else {
        System.out.println("Filmes encontrados:");
        for (int i = 0; i < encontrados.size(); i++) {
            Filmes f = encontrados.get(i);
            System.out.printf("%d. %s | Avaliação: %.1f | Preço: R$ %.2f\n",
                i + 1, f.getNome(), f.getAvaliacao(), f.getPreco());
            System.out.println("Sinopse: " + f.getSinopse() + " | Elenco: " + f.getElenco());
        }
    }
}

private static void deletarFilme(){
        System.out.println("\n----------------- [ Remoção de filmes ] ---------------------------");

        
            List<Filmes> filmes = estoque.getEstoque();
        
            if (filmes.isEmpty()) {
                System.out.println("Nenhum filme disponível para remoção.");
                return;
            }
        
            // Exibe os filmes numerados
            for (int i = 0; i < filmes.size(); i++) {
                System.out.printf("%d. %s\n", i + 1, filmes.get(i).getNome());
            }
        
            System.out.print("Digite o número ou nome do filme que deseja remover: ");
            String entrada = scanner.nextLine();
        
            // Tenta interpretar como número
            try {
                int indice = Integer.parseInt(entrada);
                if (indice < 1 || indice > filmes.size()) {//verifica certo o numero do filme e a gente coloco >filme size pra ele atualiza o valor cada vez q vai add filmes
                    System.out.println("Número inválido");
                    return;
                }
                Filmes removido = filmes.remove(indice - 1);
                System.out.println("Filme \"" + removido.getNome() + "\" removido com sucesso!");
                return;
            } catch (NumberFormatException e) {
                // Não é número, tenta buscar por nome
                Filmes filmeParaRemover = null;
                for (Filmes filme : filmes) {
                    if (filme.getNome().equalsIgnoreCase(entrada.trim())) {
                        filmeParaRemover = filme;
                        break;
                    }
                }
        
                if (filmeParaRemover != null) {
                    filmes.remove(filmeParaRemover);
                    System.out.println("Filme \"" + filmeParaRemover.getNome() + "\" removido com sucesso!");
                } else {
                    System.out.println("Filme não encontrado: \"" + entrada + "\"");
                }
            }
        }
}


