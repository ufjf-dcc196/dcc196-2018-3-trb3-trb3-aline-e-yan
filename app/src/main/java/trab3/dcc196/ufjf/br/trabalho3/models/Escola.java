package trab3.dcc196.ufjf.br.trabalho3.models;

import com.google.gson.annotations.SerializedName;

public class Escola {

    private int id;

    @SerializedName("cod")
    private String cod;
    @SerializedName("nome")
    private String nome;
    @SerializedName("endereco")
    private String endereco;
    @SerializedName("cidade")
    private String cidade;
    @SerializedName("estado")
    private String estado;
    @SerializedName("latitude")
    private Double latitude;
    @SerializedName("longitude")
    private Double longitude;

    public Escola() {

    }

    public int getId() {
        return id;
    }

    public Escola setId(int id) {
        this.id = id;
        return this;
    }

    public String getCod() {
        return cod;
    }

    public Escola setCod(String cod) {
        this.cod = cod;
        return this;
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

    public String getCidade() {
        return cidade;
    }

    public Escola setCidade(String cidade) {
        this.cidade = cidade;
        return this;
    }

    public String getEstado() {
        return estado;
    }

    public Escola setEstado(String estado) {
        this.estado = estado;
        return this;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Escola setLatitude(Double latitude) {
        this.latitude = latitude;
        return this;
    }

    public Double getLongitude() {
        return longitude;
    }

    public Escola setLongitude(Double longitude) {
        this.longitude = longitude;
        return this;
    }
}
