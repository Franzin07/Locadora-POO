package com.sexo.models;



public class FilmesAlugados extends Filmes {
   
    public int dataAlugada;
    public int dataDevolucao;
    
   
    public FilmesAlugados(String nome, String sinopse, float avaliacao, String elenco, double preco, int dataAlugada, int dataDevolucao){
        super(nome, sinopse, avaliacao, elenco, preco);
        this.dataAlugada = dataAlugada;
        this.dataDevolucao = dataDevolucao;
    }

    public void setDataAlugada(int dataAlugada){
        this.dataAlugada = dataAlugada;
    }

    public void setDataDevolucao(int dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public int getDataAlugada() {
        return dataAlugada;
    }

    public int getDataDevolucao() {
        return dataDevolucao;
    }
}
