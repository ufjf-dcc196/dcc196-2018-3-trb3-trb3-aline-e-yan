package trab3.dcc196.ufjf.br.trabalho3.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Query;
import trab3.dcc196.ufjf.br.trabalho3.Banco.CandidatoDBHelper;
import trab3.dcc196.ufjf.br.trabalho3.Persistence.CandidatoDAO;
import trab3.dcc196.ufjf.br.trabalho3.R;
import trab3.dcc196.ufjf.br.trabalho3.adapters.AdapterCandidato;
import trab3.dcc196.ufjf.br.trabalho3.adapters.AdapterEscola;
import trab3.dcc196.ufjf.br.trabalho3.models.Candidato;
import trab3.dcc196.ufjf.br.trabalho3.models.Escola;
import trab3.dcc196.ufjf.br.trabalho3.services.EscolaService;

public class CadastroCandidatoActivity extends AppCompatActivity {

    private RecyclerView rvListaEscolasEncontradas;

    private CandidatoDBHelper dbHelper;
    private EditText edtNomeCompleto;
    private EditText edtCPF;
    private EditText edtProva;
    private EditText edtPesquisarEscola;
    private Button btnPesquisarEscola;
    private Button btnCadastrarEstudante;

    private AdapterEscola adapterEscola;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_candidato);

        dbHelper = new CandidatoDBHelper(getApplicationContext());
        edtNomeCompleto = (EditText) findViewById(R.id.edt_nome_completo);
        edtCPF = (EditText) findViewById(R.id.edt_cpf);
        edtPesquisarEscola = (EditText) findViewById(R.id.edt_pesquisar_escola);
        edtProva = (EditText) findViewById(R.id.edt_prova);
        btnPesquisarEscola = (Button) findViewById(R.id.btn_pesquisar_escola);

        adapterEscola = new AdapterEscola(new ArrayList<Escola>());
        rvListaEscolasEncontradas = (RecyclerView) findViewById(R.id.rv_lista_escolas_encontradas);
        rvListaEscolasEncontradas.setLayoutManager(new LinearLayoutManager(this));
        rvListaEscolasEncontradas.setAdapter(adapterEscola);
        btnPesquisarEscola.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://educacao.dadosabertosbr.com/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
                EscolaService escolaService = retrofit.create(EscolaService.class);

                Call<List<Escola>> escola = escolaService.
                        getEscolas(edtPesquisarEscola.getText().toString());

                escola.enqueue(new Callback<List<Escola>>() {
                    @Override
                    public void onResponse(Call<List<Escola>> call, Response<List<Escola>> response) {
                        for(Escola escolaResponse: response.body()){
                            Log.i("SERVIÇO", "Escolas: "+escolaResponse.getNome());
                        }
                        adapterEscola.setEscolas((ArrayList<Escola>) response.body());
                        adapterEscola.notifyDataSetChanged();
                    }
                    @Override
                    public void onFailure(Call<List<Escola>> call, Throwable t) {
                        Log.i("SERVIÇO", "Falha: "+t.getMessage());
                    }
                });

            }
        });

        btnCadastrarEstudante = (Button) findViewById(R.id.btn_cadastrar_estudante);
        btnCadastrarEstudante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Candidato candidatoAux = new Candidato();
                candidatoAux.setNome(edtNomeCompleto.getText().toString())
                        .setCpf(edtCPF.getText().toString())
                        .setProva(edtProva.getText().toString())
                        .setIdEscola(1);
                CandidatoDAO.getInstance(getApplicationContext())
                        .insercaoCandidatoBanco(candidatoAux);
                setResult(Activity.RESULT_OK, new Intent());
                finish();
            }
        });

    }
}
