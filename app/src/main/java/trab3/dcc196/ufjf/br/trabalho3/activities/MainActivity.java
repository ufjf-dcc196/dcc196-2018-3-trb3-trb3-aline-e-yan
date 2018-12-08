package trab3.dcc196.ufjf.br.trabalho3.activities;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import trab3.dcc196.ufjf.br.trabalho3.Banco.CandidatoDBHelper;
import trab3.dcc196.ufjf.br.trabalho3.R;
import trab3.dcc196.ufjf.br.trabalho3.adapters.AdapterEstudante;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_CADASTRAR_CANDIDATO = 1;

    private RecyclerView rvListaEstudantesCadastrados;
    private CandidatoDBHelper dbHelper;
    private Button btnCadastrarEstudante;

    private AdapterEstudante adapterEstudante;

    public static final String ESTUDANTE_INDICE = "ESTUDANTE_INDICE";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new CandidatoDBHelper(getApplicationContext());
        adapterEstudante = new AdapterEstudante(dbHelper.getAllEstudantesBanco());
        adapterEstudante.setCursor(dbHelper.getAllEstudantesBanco());
        rvListaEstudantesCadastrados = findViewById(R.id.rv_lista_estudantes_cadastrados);
        rvListaEstudantesCadastrados.setAdapter(adapterEstudante);
        rvListaEstudantesCadastrados.setLayoutManager(new LinearLayoutManager(this));

        btnCadastrarEstudante = (Button) findViewById(R.id.btn_cadastrar_estudante);
        btnCadastrarEstudante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CadastroEstudanteActivity.class);
                startActivity(intent);
            }
        });
    }
}
