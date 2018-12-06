package trab3.dcc196.ufjf.br.trabalho3.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import trab3.dcc196.ufjf.br.trabalho3.R;

public class VisualizarEstudanteActivity extends AppCompatActivity {

    private TextView txtNomeCompleto;
    private TextView txtCPF;
    private TextView txtNomeEscola;
    private TextView txtEndereco;
    private TextView txtCidade;
    private TextView txtEstado;

    private Button btnEditarEstudante;
    private Button btnVisualizarLocalizacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizar_estudante);

        txtNomeCompleto = (TextView) findViewById(R.id.txt_nome_completo);
        txtCPF = (TextView) findViewById(R.id.txt_cpf);
        txtNomeEscola = (TextView) findViewById(R.id.txt_nome_escola);
        txtEndereco = (TextView) findViewById(R.id.txt_endereco);
        txtCidade = (TextView) findViewById(R.id.txt_cidade);
        txtEstado = (TextView) findViewById(R.id.txt_estado);

        btnEditarEstudante = (Button) findViewById(R.id.btn_editar_estudante);
        btnEditarEstudante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(VisualizarEstudanteActivity.this, CadastroEstudanteActivity.class);
                startActivity(intent);
            }
        });

        btnVisualizarLocalizacao = (Button) findViewById(R.id.btn_visualizar_localizacao);
        btnVisualizarLocalizacao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(VisualizarEstudanteActivity.this, MapsActivity.class);
                startActivity(intent);
            }
        });
    }
}
