package trab3.dcc196.ufjf.br.trabalho3.models;

public class Escola {

  private String nome, endereco;
  private int id;

    public Escola() {

    }

    public String getNome() {
        return nome;
    }

    public Escola setNome(String nome) {
        this.nome = nome;
        return this;
    }

    public String getEndereco() {
        return endereco;
    }

    public Escola setEndereco(String endereco) {
        this.endereco = endereco;
        return this;
    }

    public int getId() {
        return id;
    }

    public Escola setId(int id) {
        this.id = id;
        return this;
    }
}
