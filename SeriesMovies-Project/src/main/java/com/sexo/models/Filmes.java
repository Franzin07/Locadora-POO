package com.sexo.models;


public  class  Filmes {
 
    public String nome;
    public String sinopse;
    public float avaliacao;
    public String elenco;
    public double preco;
   
    public Filmes(String nome, String sinopse, float avaliacao, String elenco,double preco){
        this.nome = nome;
        this.sinopse = sinopse;
        if(nome != null){
            this.nome = nome;
        }else{
            System.out.println("ERRO: Nome nao pode ser vazio");
        }
        if (avaliacao >= 0 && avaliacao <=5 ){
            this.avaliacao = avaliacao;
        }
        else{System.out.println("Avaliação inválida. Deve ser entre 0 e 5.");}

        this.elenco = elenco;

        if (preco > 0){
            this.preco = preco;
        }
        else{
            System.out.println("Preço inválido. Deve ser maior que 0");
        }
    }

    public void setAvaliacao(float avaliacao) {
        if (avaliacao >= 0 && avaliacao <=5 ){
            this.avaliacao = avaliacao;
        }
        else{System.out.println("Avaliação inválida. Deve ser entre 0 e 5.");}
    }


    //setters
    public void setElenco(String elenco) {
        if(elenco!= null){
        this.elenco = elenco;}else{
            System.out.println("Erro: elenco nao pode ser vazio!");
            
        }
    }

    public void setNome(String nome) {
        if(nome != null){
            this.nome = nome;
        }else{
            System.out.println("ERRO: Nome nao pode ser vazio");
        }
    }

    public void setSinopse(String sinopse) {
        if(sinopse != null){
            this.sinopse = sinopse;
        }else{
            System.out.println("ERRO: sinopse nao pode ser vazio");
        }
    }

    public void setPreco(double preco) {
        if (preco > 0){
            this.preco = preco;
        }
        else{
            System.out.println("Preço inválido. Deve ser maior que 0");
        }
    }
    //getters

    public float getAvaliacao() {
        return avaliacao;
    }

    public String getElenco() {
        return elenco;
    }

    public String getNome() {
        return nome;
    }

    public String getSinopse() {
        return sinopse;
    }

    public double getPreco() {
        return preco;
    }
}
