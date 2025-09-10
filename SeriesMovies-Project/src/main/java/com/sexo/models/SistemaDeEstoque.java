package com.sexo.models;

import java.util.ArrayList;
import java.util.List;
//construtor para o desenvolvimento de uma lista de filmes alugados
public class SistemaDeEstoque {
    private List<Filmes> estoque;
//cria uma nova lista para estoque
    public SistemaDeEstoque() {
        this.estoque = new ArrayList<>();
    }
//adiciona um filme novo ao estoque,e o mostra com uma menssagem "filme adicionado ao estoque:nomedofilme"
    public void adicionarFilme(Filmes filme) {
        estoque.add(filme);
        System.out.println("Filme adicionado ao estoque: " + filme.getNome());
    }
//retorna uma menssagem caso o filme procurado não esteja no estoque
    public void listarFilmes() {
        if (estoque.isEmpty()) {
            System.out.println("O estoque está vazio.");
            return;
        }
// mostra as filmes em estoque e suas infomações, nome,preço,avaliação
        System.out.println("\nFilmes em Estoque:");
        for (int i = 0; i < estoque.size(); i++) {
            Filmes filme = estoque.get(i);
            System.out.println((i + 1) + ". " + filme.getNome() + " | R$" + filme.getPreco() + " | Avaliação: " + filme.getAvaliacao());
        }
    }
//retorna o estoque com a lista de filmes alugados
    public List<Filmes> getEstoque() {
        return estoque;
    }

  
}
