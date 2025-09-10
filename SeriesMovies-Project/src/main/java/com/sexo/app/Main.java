package com.sexo.app;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


import com.sexo.models.Filmes;
import com.sexo.models.SistemaDeEstoque;

//um scanner pra identificar as ecolhas do usuario do caso 0 ao 6
public class Main { 
    private static SistemaDeEstoque estoque = new SistemaDeEstoque();
    private static Scanner scanner = new Scanner(System.in);
 
    public static void main(String[] args) {
       
         boolean executando = true;

          while (executando) {
            exibirMenu();
            int opcao = lerOpcao();     // lê oque o usuario escolheu
            switch (opcao) {
                
                case 1:
                   verEstoque();
                    break;
                case 2:
                   atualizarEstoque();
                    break;
                case 3:
                    deletarFilme();
                    break;
                case 4:
                   buscaNomeFilmes();
                    break;
                case 5:
                    alugarFilme() ;
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
           return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1; // Retorna opção inválida
        }
    }
//exibi opções de 0 a 6 para o usuario escolher oq fazer, formando o menu
    public static void exibirMenu(){
        System.out.println("\n ----------------- [ Locadora de Filmes 1990 ] ---------------------------");
        System.out.println("1 - Listar filmes disponiveis ");
        System.out.println("2 - Atualizar estoque / devolução de filmes");
        System.out.println("3 - Remover filme");
        System.out.println("4 - Buscar filme por nome");
        System.out.println("5 - Alugar filme");
        System.out.println("6 - Adicionar filme");
        System.out.println("0 - Sair");
        System.out.print("Escolha uma opção: ");
    }
//verifica o estoque e mostra para o usuario todas as informações necessarias sobre o filme procurado
    private static void verEstoque() {
    System.out.println("\n----------------- [ Filmes Em Estoque ] ---------------------------");

    List<Filmes> filmes = estoque.getEstoque();

    if (filmes.isEmpty()) {
        System.out.println("Nenhum filme disponível no estoque.");
        return;
    }

    int i = 1;
    for (Filmes filme : filmes) {
        String status = filme.isAlugado() ? " (Indisponível para aluguel no momento)" : "";
        
        System.out.printf("   %d. |ID: %d| Nome: %s%s%n", i, filme.getId(), filme.getNome(), status);

        System.out.printf("       Avaliação: %.1f | Preço: R$ %.2f%n", filme.getAvaliacao(), filme.getPreco());
        System.out.println("       Elenco: " + filme.getElenco());
        System.out.println("       Sinopse: " + filme.getSinopse());
        System.out.println(); // linha extra para separar visualmente

        i++;
    }
}
//busca um filme pelo nome 
private static String lerNome(String campo) {
    while (true) {
        System.out.print(campo + ": ");
        String entrada = scanner.nextLine();

        if (entrada != null && !entrada.trim().isEmpty()) {
            return entrada.trim();
        } else {
            System.out.println("Entrada inválida. Por favor, digite um " + campo.toLowerCase() + " válido.");
        }
    }
}
//pede para que o usuario faça uma avaliação ao filme cadastrado, e após cadastrar ele retorna junto com as inf do filme
private static float lerAvaliacao() {
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
    return avaliacao;
}
//pede ao usuario add um preco ao filme cadastrado
private static double lerPreco() { 
    double preco = 0;
    boolean entradaValida = false;

    while (!entradaValida) { 
        System.out.print("Preço: ");
        String entrada = scanner.nextLine();

        if (entrada != null && !entrada.trim().isEmpty()) {
            try {
                entrada = entrada.trim().replace(',', '.'); 
                preco = Double.parseDouble(entrada);
                
                if (preco > 0) {
                    entradaValida = true;
                } else {
                    System.out.println("Preço deve ser maior que 0.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Preço inválido. Por favor, insira um número válido.");
            }
        } else {
            System.out.println("Entrada vazia. Por favor, insira um valor numérico.");
        }
    }

    return preco;
}

private static String lerSegundaria(String campo) {
    while (true) {
        System.out.print(campo + ": ");
        String entrada = scanner.nextLine().trim();

        if (entrada.isEmpty()) {
            System.out.println("Entrada inválida. Por favor, digite um " + campo.toLowerCase() + " válido.");
        } 
        // Verifica se não contém nenhuma letra (só números, símbolos ou espaços)
        else if (!entrada.matches(".*[a-zA-Z].*")) {
            System.out.println("Entrada inválida. O " + campo.toLowerCase() + " deve conter letras.");
        } 
        else {
            return entrada;
        }
    }
}
//verifica na hora de cadastrar um filme se ja não existi outro igual
private static void adicionarFilme() {
    System.out.println("\n----------------- [ Cadastro de filmes ] ---------------------------");

    String nome = lerNome("Nome");
    for (Filmes f : estoque.getEstoque()) {
        if (f.getNome().equalsIgnoreCase(nome)) {
            System.out.println("Erro: Já existe um filme cadastrado com esse nome.");
            return; 
        }
    }
//adiciona sinopse,avaliação,elenco e preco, aos prerequisitos para cadastrar um filme
    String sinopse = lerSegundaria("Sinopse");
    float avaliacao = lerAvaliacao();
    String elenco = lerSegundaria("Elenco");
    double preco = lerPreco();

    Filmes filme = new Filmes(nome, sinopse, avaliacao, elenco, preco);
    estoque.adicionarFilme(filme);

    System.out.println("Filme adicionado com sucesso!");
    System.out.println("Id do Filme: " + filme.getId());
}

// tambem busca o filme pelo nome, e ele completa o nome do filme mais proximo do q o usuario escreveu
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

    // Buscar filmes que contenham o termo ignorando maiúsculas e minúsculas
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
            System.out.printf("%d. |ID: %d| nome: %s | Avaliação: %.1f | Preço: R$ %.2f%n",
                i + 1, f.getId(), f.getNome(), f.getAvaliacao(), f.getPreco());
            System.out.println("Sinopse: " + f.getSinopse() + " | Elenco: " + f.getElenco());
            System.out.println(f.isAlugado() ? " Indisponível para aluguel" : " Disponível para aluguel");
        }
    }
}

private static void alugarFilme() {
    System.out.println("\n----------------- [ Alugar Filme ] ---------------------------");

    List<Filmes> filmes = estoque.getEstoque();
    //exibi uma menssagem de que não ha filmes disponiveis
    if (filmes.isEmpty()) {
        System.out.println("Nenhum filme disponível para aluguel.");
        return;
    }

    // Exibe os filmes disponíveis
    for (int i = 0; i < filmes.size(); i++) {
        Filmes f = filmes.get(i);
        String status = f.isAlugado() ? " (Indisponível)" : "";
        System.out.printf("%d. %s%s%n", i + 1, f.getNome(), status);
    }

    System.out.print("Digite o número do filme que deseja alugar: ");
    String entrada = scanner.nextLine();

    try {
        int indice = Integer.parseInt(entrada);
        if (indice < 1 || indice > filmes.size()) {
            System.out.println("Número inválido");
            return;
        }

        Filmes filmeSelecionado = filmes.get(indice - 1);

        if (filmeSelecionado.isAlugado()) {
            System.out.println("Este filme já está alugado no momento.");
            return;
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
LocalDate dataAlugada = null;
LocalDate dataDevolucao = null;

// Solicita e valida a data de aluguel
while (true) {
    System.out.print("Digite a data de aluguel (formato: dd/MM/yyyy): ");
    String dataAlugadaStr = scanner.nextLine();
    try {
        dataAlugada = LocalDate.parse(dataAlugadaStr, formatter);
        if (!anoValido(dataAlugada)) {
            System.out.println("A data de aluguel deve estar entre os anos de 1990 e 2100.");
            continue;
        }
        break;
    } catch (DateTimeParseException e) {
        System.out.println("Formato de data inválido. Tente novamente.");
    }
    }

    // Solicita e valida a data de devolução
    while (true) {
        System.out.print("Digite a data de devolução (formato: dd/MM/yyyy): ");
        String dataDevolucaoStr = scanner.nextLine();
        try {
            dataDevolucao = LocalDate.parse(dataDevolucaoStr, formatter);
            if (!anoValido(dataDevolucao)) {
                System.out.println("A data de devolução deve estar entre os anos de 1990 e 2100.");
                continue;
            }
            if (dataDevolucao.isBefore(dataAlugada)) {
                System.out.println("A data de devolução não pode ser antes da data de aluguel.");
                continue;
            }
            break;
        } catch (DateTimeParseException e) {
            System.out.println("Formato de data inválido. Tente novamente.");
        }
    }


        // Marca o original como alugado
        filmeSelecionado.setAlugado(true);

        System.out.println("Filme \"" + filmeSelecionado.getNome() + "\" alugado com sucesso!");
        System.out.printf("Data de Aluguel: %s | Data de Devolução: %s%n",
                dataAlugada.format(formatter), dataDevolucao.format(formatter));


    } catch (NumberFormatException e) {
        System.out.println("Entrada inválida. Por favor, digite um número válido.");
    } catch (DateTimeParseException e) {
        System.out.println("Formato de data inválido. Use o formato dd/MM/yyyy.");
    }
}






private static void atualizarEstoque() {
    System.out.println("\n----------------- [ Atualizar Estoque - Devolução de Filmes ] ---------------------------");

    List<Filmes> filmes = estoque.getEstoque();

    // Filtrar apenas os filmes que estão alugados
    List<Filmes> alugados = new ArrayList<>();
    for (Filmes f : filmes) {
        if (f.isAlugado()) {
            alugados.add(f);
        }
    }

    if (alugados.isEmpty()) {
        System.out.println("Nenhum filme está alugado no momento.");
        return;
    }

    // Exibir filmes alugados
    System.out.println("Filmes atualmente alugados:");
    for (int i = 0; i < alugados.size(); i++) {
        System.out.printf("%d. %s%n", i + 1, alugados.get(i).getNome());
    }

    System.out.print("Digite o número do filme que deseja marcar como devolvido: ");
    String entrada = scanner.nextLine();

    try {
        int indice = Integer.parseInt(entrada);
        if (indice < 1 || indice > alugados.size()) {
            System.out.println("Número inválido.");
            return;
        }

        Filmes devolvido = alugados.get(indice - 1);
        devolvido.setAlugado(false); // Marca como disponível

        System.out.println("Filme " + devolvido.getNome() + "\" foi marcado como devolvido com sucesso!");

    } catch (NumberFormatException e) {
        System.out.println("Entrada inválida. Digite um número válido.");
    }
}

private static boolean anoValido(LocalDate data) {
    int ano = data.getYear();
    return ano >= 1990 && ano <= 2100;
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
                System.out.printf("%d. %s%n", i + 1, filmes.get(i).getNome());
            }
        //remove um filme do estoque pelo nome ou pelo numero
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


