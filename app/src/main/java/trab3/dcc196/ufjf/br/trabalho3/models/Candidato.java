package trab3.dcc196.ufjf.br.trabalho3.models;

public class Candidato {

     private  String nome, cpf, prova;
     private  int id_escola, id;

    public Candidato() {
    }

    public String getProva() {
        return prova;
    }

    public Candidato setProva(String prova) {
        this.prova = prova;
        return this;
    }

    public String getNome() {
        return nome;
    }

    public Candidato setNome(String nome) {
        this.nome = nome;
        return this;
    }

    public String getCpf() {
        return cpf;
    }

    public Candidato setCpf(String cpf) {
        this.cpf = cpf;
        return this;
    }

    public int getId_escola() {
        return id_escola;
    }

    public Candidato setId_escola(int id_escola) {
        this.id_escola = id_escola;
        return this;

    }

    public int getId() {
        return id;
    }

    public Candidato setId(int id) {
        this.id = id;
        return this;
    }
}
