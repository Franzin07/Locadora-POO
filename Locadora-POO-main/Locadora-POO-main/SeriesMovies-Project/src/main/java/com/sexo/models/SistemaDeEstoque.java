package com.sexo.models;

import java.util.ArrayList;
import java.util.List;

public class SistemaDeEstoque {
    private List<Filmes> estoque;

    public SistemaDeEstoque() {
        this.estoque = new ArrayList<>();
    }

    public void adicionarFilme(Filmes filme) {
        estoque.add(filme);
        System.out.println("Filme adicionado ao estoque: " + filme.getNome());
    }

    public void listarFilmes() {
        if (estoque.isEmpty()) {
            System.out.println("O estoque está vazio.");
            return;
        }

        System.out.println("\nFilmes em Estoque:");
        for (int i = 0; i < estoque.size(); i++) {
            Filmes filme = estoque.get(i);
            System.out.println((i + 1) + ". " + filme.getNome() + " | R$" + filme.getPreco() + " | Avaliação: " + filme.getAvaliacao());
        }
    }

    public List<Filmes> getEstoque() {
        return estoque;
    }

  
}
