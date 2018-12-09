package trab3.dcc196.ufjf.br.trabalho3.services;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.QueryName;
import retrofit2.http.Path;
import trab3.dcc196.ufjf.br.trabalho3.models.Escola;

public interface EscolaService {

    @GET("api/escolas?nome={palavraChave}")
    Call<List<Escola>> getEscolas(@Path("palavraChave") String palavraChave);

    @GET("api/escola/{cod}")
    Call<Escola> getEscolaByCod(@Path("cod") String cod);
}
