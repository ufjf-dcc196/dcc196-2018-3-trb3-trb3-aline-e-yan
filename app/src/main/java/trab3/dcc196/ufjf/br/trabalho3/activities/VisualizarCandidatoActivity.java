package trab3.dcc196.ufjf.br.trabalho3.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import trab3.dcc196.ufjf.br.trabalho3.Persistence.CandidatoDAO;
import trab3.dcc196.ufjf.br.trabalho3.R;
import trab3.dcc196.ufjf.br.trabalho3.models.Candidato;
import trab3.dcc196.ufjf.br.trabalho3.models.Escola;
import trab3.dcc196.ufjf.br.trabalho3.services.EscolaService;

public class VisualizarCandidatoActivity extends AppCompatActivity {

    private TextView txtNomeCompleto;
    private TextView txtCPF;
    private TextView txtProva;
    private TextView txtNomeEscola;
    private TextView txtCod;
    private TextView txtEndereco;
    private TextView txtCidade;
    private TextView txtEstado;
    private int idCandidato;
    private Button btnEditarCandidato;
    private Button btnVisualizarLocalizacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizar_candidato);

        final Intent intent = getIntent();
        Bundle bundleResult = intent.getExtras();
        idCandidato = bundleResult.getInt(MainActivity.ID_CANDIDATO);
        txtNomeCompleto = (TextView) findViewById(R.id.txt_nome);
        txtCPF = (TextView) findViewById(R.id.txt_cpf);
        txtProva = (TextView) findViewById(R.id.txt_prova);
        txtNomeEscola = (TextView) findViewById(R.id.txt_nome_escola);
        txtCod = (TextView) findViewById(R.id.txt_cod);
        txtEndereco = (TextView) findViewById(R.id.txt_endereco);
        txtCidade = (TextView) findViewById(R.id.txt_cidade);
        txtEstado = (TextView) findViewById(R.id.txt_estado);

        Candidato candidatoAux = CandidatoDAO.getInstance(getApplicationContext())
                .getCandidatoById(idCandidato);
        txtNomeCompleto.setText(candidatoAux.getNome());
        txtCPF.setText(candidatoAux.getCpf());
        txtProva.setText(candidatoAux.getProva());

        btnEditarCandidato = (Button) findViewById(R.id.btn_editar_candidato);
        btnEditarCandidato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(VisualizarCandidatoActivity.this, CadastroCandidatoActivity.class);
                startActivity(intent);
            }
        });

        btnVisualizarLocalizacao = (Button) findViewById(R.id.btn_visualizar_localizacao);
        btnVisualizarLocalizacao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(VisualizarCandidatoActivity.this, MapsActivity.class);
                intent.putExtra(MainActivity.ID_CANDIDATO, idCandidato);
                startActivity(intent);
            }
        });

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://educacao.dadosabertosbr.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        EscolaService escolaService = retrofit.create(EscolaService.class);
        Call<Escola> escola = escolaService.getEscolaByCod(String.valueOf(candidatoAux.getCodEscola()));
        escola.enqueue(new Callback<Escola>() {
            @Override
            public void onResponse(Call<Escola> call, Response<Escola> response) {
                Escola escolaResponse = response.body();
                txtNomeEscola.setText(escolaResponse.getNome());
                txtCod.setText(escolaResponse.getCod());
                txtEndereco.setText(escolaResponse.getEndereco());
                txtCidade.setText(escolaResponse.getNomeDistrito());
                txtEstado.setText(escolaResponse.getSiglaUf());
            }

            @Override
            public void onFailure(Call<Escola> call, Throwable t) {
                Log.i("SERVIÇO", "Falha: "+t.getMessage());
            }
        });
    }
}
