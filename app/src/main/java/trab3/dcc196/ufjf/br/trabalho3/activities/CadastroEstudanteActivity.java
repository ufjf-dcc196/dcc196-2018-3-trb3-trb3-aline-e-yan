package trab3.dcc196.ufjf.br.trabalho3.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.EditText;

import trab3.dcc196.ufjf.br.trabalho3.Banco.CandidatoDBHelper;
import trab3.dcc196.ufjf.br.trabalho3.R;
import trab3.dcc196.ufjf.br.trabalho3.adapters.AdapterEstudante;
import trab3.dcc196.ufjf.br.trabalho3.models.Candidato;

public class CadastroEstudanteActivity extends AppCompatActivity {

    private RecyclerView rvListaEscolasEncontradas;
    private AdapterEstudante adapterEstudante;
    private CandidatoDBHelper dbHelper;
    private EditText edtNomeCompleto;
    private EditText edtCPF;
    private EditText edtProva;
    private EditText edtPesquisarEscola;

    private Button btnPesquisarEscola;
    private Button btnCadastrarEstudante;

    //private AdapterEscola adapterEscola;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_estudante);
        dbHelper = new CandidatoDBHelper(getApplicationContext());
        edtNomeCompleto = (EditText) findViewById(R.id.edt_nome_completo);
        edtCPF = (EditText) findViewById(R.id.edt_cpf);
        edtPesquisarEscola = (EditText) findViewById(R.id.edt_pesquisar_escola);
        edtProva = (EditText) findViewById(R.id.edt_prova);
        btnPesquisarEscola = (Button) findViewById(R.id.btn_pesquisar_escola);
        btnPesquisarEscola.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Candidato candidatoAux = new Candidato();
                candidatoAux.setCpf(edtCPF.getText().toString())
                        .setProva(edtProva.getText().toString());
                //pegar da api
                //            .setId_escola()
                dbHelper.insercaoCandidatoBanco(candidatoAux);
                finish();
            }
        });

        btnCadastrarEstudante = (Button) findViewById(R.id.btn_cadastrar_estudante);
        btnCadastrarEstudante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Candidato candidatoAux = new Candidato();
                candidatoAux.setCpf(edtCPF.getText().toString())
                        .setProva(edtProva.getText().toString());
                //pegar da api
                //            .setId_escola()
                dbHelper.insercaoCandidatoBanco(candidatoAux);
                finish();
            }
        });

        rvListaEscolasEncontradas = (RecyclerView) findViewById(R.id.rv_lista_escolas_encontradas);
        rvListaEscolasEncontradas.setLayoutManager(new LinearLayoutManager(this));
        //adapterEstudante = new AdapterEstudante(Persistencia.getInstance(getApplicationContext()).selectAllParticipantesCursor());
        rvListaEscolasEncontradas.setAdapter(adapterEstudante);
    }
}
