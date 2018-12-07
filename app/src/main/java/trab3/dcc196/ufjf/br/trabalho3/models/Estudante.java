package trab3.dcc196.ufjf.br.trabalho3.models;

public class Estudante {

     private  String nome, cpf;
     private  int id_escola, id;

    public Estudante() {
    }

    public String getNome() {
        return nome;
    }

    public Estudante setNome(String nome) {
        this.nome = nome;
        return this;
    }

    public String getCpf() {
        return cpf;
    }

    public Estudante setCpf(String cpf) {
        this.cpf = cpf;
        return this;
    }

    public int getId_escola() {
        return id_escola;
    }

    public Estudante setId_escola(int id_escola) {
        this.id_escola = id_escola;
        return this;

    }

    public int getId() {
        return id;
    }

    public Estudante setId(int id) {
        this.id = id;
        return this;
    }
}
