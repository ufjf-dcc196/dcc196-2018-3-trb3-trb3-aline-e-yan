package trab3.dcc196.ufjf.br.trabalho3.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import trab3.dcc196.ufjf.br.trabalho3.R;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvListaEstudantesCadastrados;

    private Button btnCadastrarEstudante;

    //private AdapterEstudante adapterEstudante;

    public static final String ESTUDANTE_INDICE = "ESTUDANTE_INDICE";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
