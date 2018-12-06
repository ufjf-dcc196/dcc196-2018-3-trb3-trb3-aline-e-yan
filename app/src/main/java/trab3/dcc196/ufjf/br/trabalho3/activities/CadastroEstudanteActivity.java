package trab3.dcc196.ufjf.br.trabalho3.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import trab3.dcc196.ufjf.br.trabalho3.R;

public class CadastroEstudanteActivity extends AppCompatActivity {

    private RecyclerView rvListaEscolasEncontradas;

    private EditText edtNomeCompleto;
    private EditText edtCPF;
    private EditText edtPesquisarEscola;

    private Button btnPesquisarEscola;
    private Button btnCadastrarEstudante;

    //private AdapterEscola adapterEscola;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_estudante);

        edtNomeCompleto = (EditText) findViewById(R.id.edt_nome_completo);
        edtCPF = (EditText) findViewById(R.id.edt_cpf);
        edtPesquisarEscola = (EditText) findViewById(R.id.edt_pesquisar_escola);

        btnPesquisarEscola = (Button) findViewById(R.id.btn_pesquisar_escola);
        btnPesquisarEscola.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btnCadastrarEstudante = (Button) findViewById(R.id.btn_cadastrar_estudante);
        btnCadastrarEstudante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
