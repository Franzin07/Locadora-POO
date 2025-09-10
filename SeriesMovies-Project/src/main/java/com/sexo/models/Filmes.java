package com.sexo.models;
//cria um banco com classes para Filmes
public class Filmes {
    
    private static int contador = 1;
    private final int id; //final pra não deixar mudar o id com um setter
    private String nome; 
    private String sinopse;
    private float avaliacao;
    private String elenco;
    private double preco;
    private boolean alugado = false;
   //construtor que adiciona os atributos as classes
      public Filmes(String nome, String sinopse, float avaliacao, String elenco, double preco) {
        this.id = contador++;
//retorna os atributos caso o valor não seja menor q zero ou a resposta não seja nula; e se a resposta for nula ou zero, retorna uma menssagem de erro
        if (nome != null && !nome.trim().isEmpty()) {
            this.nome = nome.trim();
        } else {
            System.out.println("ERRO: Nome inválido.");
        }

        if (isTextoValido(sinopse)) {
            this.sinopse = sinopse.trim();
        } else {
            System.out.println("ERRO: Sinopse inválida. Deve conter pelo menos uma letra.");
        }

        if (avaliacao >= 0 && avaliacao <= 5) {
            this.avaliacao = avaliacao;
        } else {
            System.out.println("ERRO: Avaliação deve estar entre 0 e 5.");
        }

        if (isTextoValido(elenco)) {
            this.elenco = elenco.trim();
        } else {
            System.out.println("ERRO: Elenco inválido. Deve conter pelo menos uma letra.");
        }

        if (preco > 0) {
            this.preco = preco;
        } else {
            System.out.println("ERRO: Preço deve ser maior que zero.");
        }
    }

    // Método auxiliar para validar que o texto tem pelo menos uma letra (inclusive com acento)
    private boolean isTextoValido(String texto) {
        return texto != null && !texto.trim().isEmpty() && texto.matches(".*\\p{L}+.*");
    }

    // Getters e setters (exemplo com sinopse)
    

    

    //setters
  

    public void setNome(String nome) {
        if (nome != null && !nome.trim().isEmpty()) {
            this.nome = nome;
        } else {
            System.out.println("ERRO: Nome nao pode ser vazio");
        }
    }

    public void setSinopse(String sinopse) {
        if (isTextoValido(sinopse)) {
            this.sinopse = sinopse.trim();
        } else {
            System.out.println("ERRO: Sinopse inválida.");
        }
    }

    public void setElenco(String elenco) {
        if (isTextoValido(elenco)) {
            this.elenco = elenco.trim();
        } else {
            System.out.println("ERRO: Elenco inválido.");
        }
    }

    public void setAvaliacao(float avaliacao) {
        if (avaliacao >= 0 && avaliacao <= 5) {
            this.avaliacao = avaliacao;
        } else {
            System.out.println("Avaliação inválida. Deve ser entre 0 e 5.");
        }
    }

    public void setPreco(double preco) {
        if (preco > 0) {
            this.preco = preco;
        } else {
            System.out.println("Preço inválido. Deve ser maior que 0");
        }
    }
    
    //getters
      public int getId() {
        return id;
    }
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

    public boolean isAlugado() {
        return alugado;
    }

    public void setAlugado(boolean alugado) {
        this.alugado = alugado;
    }
}