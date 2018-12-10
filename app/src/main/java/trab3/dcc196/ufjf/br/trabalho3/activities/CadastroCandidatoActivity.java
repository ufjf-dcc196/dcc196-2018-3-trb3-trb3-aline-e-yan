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
import android.widget.Toast;

import com.google.gson.internal.LinkedTreeMap;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
    private Button btnCadastrarCandidato;

    private Escola escolaEscolhida;

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

                Call<List<Object>> escolas = escolaService.
                        getEscolas(edtPesquisarEscola.getText().toString());
                escolas.enqueue(new Callback<List<Object>>() {
                    @Override
                    public void onResponse(Call<List<Object>> call, Response<List<Object>> response) {
                        Log.i("SERVIÇO", "Escolas:::> " + response.body().toString());
                        ArrayList<Escola> escolas = new ArrayList<>();
                        for(Object objeto : ((List) response.body().get(1))){
                            if (objeto instanceof Double) {
                                Log.i("SERVIÇO", "PULANDO O NÚMERO DE OBJETOS RETORNADOS: " + String.valueOf((Double) objeto));
                                continue;
                            }

                            Escola escola = new Escola();
                            escola.setCod(String.valueOf(((Double)((LinkedTreeMap) objeto).get("cod")).intValue()));
                            escola.setNome(((LinkedTreeMap) objeto).get("nome").toString());
                            escola.setCidade(((LinkedTreeMap) objeto).get("cidade").toString());
                            escola.setEstado(((LinkedTreeMap) objeto).get("estado").toString());
                            escolas.add(escola);
                        }
                        adapterEscola.setEscolas(escolas);
                        adapterEscola.notifyDataSetChanged();
                    }

                    @Override
                    public void onFailure(Call<List<Object>> call, Throwable t) {
                        Log.i("SERVIÇO", "Falha::: " + t.getMessage());
                    }
                });
            }
        });

        adapterEscola.setOnAdapterCandidatoClickListener(new AdapterEscola.OnAdapterEscolaClickListener() {
            @Override
            public void OnAdapterEscolaClick(View view, int position) {
                escolaEscolhida = (Escola) adapterEscola.getEscolas().get(position);
                Toast t = Toast.makeText(getApplicationContext(), "Selecionada para cadastro a escola " + escolaEscolhida.getNome() + " (COD: " + escolaEscolhida.getCod() + ")", Toast.LENGTH_LONG);
                t.show();
            }

            @Override
            public void OnAdapterEscolaClickLong(View view, int position) {

            }
        });

        btnCadastrarCandidato = (Button) findViewById(R.id.btn_cadastrar_candidato);
        btnCadastrarCandidato.setOnClickListener(new View.OnClickListener() {
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
