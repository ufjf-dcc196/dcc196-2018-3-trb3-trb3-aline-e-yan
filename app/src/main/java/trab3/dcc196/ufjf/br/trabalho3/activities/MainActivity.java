package trab3.dcc196.ufjf.br.trabalho3.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import trab3.dcc196.ufjf.br.trabalho3.Banco.CandidatoDBHelper;
import trab3.dcc196.ufjf.br.trabalho3.Persistence.CandidatoDAO;
import trab3.dcc196.ufjf.br.trabalho3.R;
import trab3.dcc196.ufjf.br.trabalho3.adapters.AdapterCandidato;
import trab3.dcc196.ufjf.br.trabalho3.models.Candidato;

public class MainActivity extends AppCompatActivity {
    public static final String ID_CANDIDATO = "Posição Participante";
    public static final int CADASTRO = 1;
    public static final String ESTUDANTE_INDICE = "ESTUDANTE_INDICE";
    private RecyclerView rvListaEstudantesCadastrados;
    private CandidatoDBHelper dbHelper;
    private Button btnCadastrarEstudante;
    private AdapterCandidato adapterCandidato;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new CandidatoDBHelper(getApplicationContext());

        rvListaEstudantesCadastrados = (RecyclerView) findViewById(R.id.rv_lista_estudantes_cadastrados);
        adapterCandidato = new AdapterCandidato(
                CandidatoDAO.getInstance(getApplicationContext()).getAllEstudantesBanco());
        adapterCandidato.setCursor(
                CandidatoDAO.getInstance(getApplicationContext()).getAllEstudantesBanco());
        rvListaEstudantesCadastrados.setAdapter(adapterCandidato);
        rvListaEstudantesCadastrados.setLayoutManager(new LinearLayoutManager(this));

        btnCadastrarEstudante = (Button) findViewById(R.id.btn_cadastrar_estudante);
        btnCadastrarEstudante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CadastroCandidatoActivity.class);
                startActivityForResult(intent, CADASTRO);
            }
        });

        adapterCandidato.setOnAdapterEstudanteClickListener(new AdapterCandidato.OnAdapterEstudanteClickListener() {
            @Override
            public void OnAdapterEstudanteClick(View view, int position) {
                Intent intent = new Intent(MainActivity.this, VisualizarCandidatoActivity.class);
                int idCandidato = (int) adapterCandidato.getItemId(position);
                intent.putExtra(MainActivity.ID_CANDIDATO, idCandidato);
                startActivity(intent);
            }

            @Override
            public void OnAdapterEstudanteClickLong(View view, int position) {
                int idCandidato = (int) adapterCandidato.getItemId(position);

                CandidatoDAO.getInstance(getBaseContext()).removeCandidato(
                       idCandidato);
                adapterCandidato.setCursor(
                        CandidatoDAO.getInstance(getApplicationContext()).getAllEstudantesBanco());
                adapterCandidato.notifyItemRemoved(position);

            }


        });




    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == MainActivity.CADASTRO && resultCode == Activity.RESULT_OK && data != null) {
            adapterCandidato.setCursor(
                    CandidatoDAO.getInstance(getApplicationContext()).getAllEstudantesBanco());
            rvListaEstudantesCadastrados.setAdapter(adapterCandidato);
            adapterCandidato.notifyDataSetChanged();
        }
    }
}
