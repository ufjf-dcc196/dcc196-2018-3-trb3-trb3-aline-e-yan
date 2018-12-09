package trab3.dcc196.ufjf.br.trabalho3.Persistence;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.database.Cursor;

import java.util.ArrayList;

import trab3.dcc196.ufjf.br.trabalho3.Banco.CandidatoContract;
import trab3.dcc196.ufjf.br.trabalho3.Banco.CandidatoDBHelper;
import trab3.dcc196.ufjf.br.trabalho3.models.Candidato;
import trab3.dcc196.ufjf.br.trabalho3.models.Escola;


public class EscolaDAO {
    private static EscolaDAO instance;
    private CandidatoDBHelper dbHelper;
    private SQLiteDatabase db;
    private Cursor cursor;
    private boolean feito = false;
    
    private EscolaDAO(Context context) {
     dbHelper= new CandidatoDBHelper(context);
    }
    
    public static EscolaDAO getInstance(Context context) {
        if (instance == null){
            instance = new EscolaDAO(context);
        }
        return instance;
    }


    public void insercaoEscolaBanco(Escola escola){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put(CandidatoContract.EscolaBD.COLUMN_NAME_NOME_ESCOLA,"" + escola.getNome());
        valores.put(CandidatoContract.EscolaBD.COLUMN_NAME_ENDERECO,"" + escola.getEndereco());
        valores.put(CandidatoContract.EscolaBD.COLUMN_NAME_MUNICIPIO_ESCOLA, "" + escola.getCidade());
        valores.put(CandidatoContract.EscolaBD.COLUMN_NAME_QUANTIDADE_SALAS_ESCOLA, "" + escola.getQuantidadesalas());

        db.insert(CandidatoContract.EscolaBD.TABLE_NAME,null, valores);
    }

    public void inicializarDBHelper(Context c){
        dbHelper = new CandidatoDBHelper(c);
        }
        
    public Escola getEscolaById(int idEscola){
        int indexNomeEscola = cursor.getColumnIndexOrThrow(CandidatoContract.EscolaBD.COLUMN_NAME_NOME_ESCOLA);
        int indexEnderecoEscola = cursor.getColumnIndexOrThrow(CandidatoContract.EscolaBD.COLUMN_NAME_ENDERECO);
        int indexIdEscola = cursor.getColumnIndexOrThrow(CandidatoContract.EscolaBD._ID);
        Escola EscolaSolicitado = null;
        if(cursor.moveToFirst()) {
            EscolaSolicitado = new Escola();
            EscolaSolicitado.setNome(cursor.getString(indexNomeEscola))
                    .setEndereco(cursor.getString(indexEnderecoEscola))
                    .setId(cursor.getInt(indexIdEscola));
        }
        return EscolaSolicitado;
    }
    public void atualizarEscola(Escola aux) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("Nome ", aux.getNome());
        cv.put("Endereco ", aux.getEndereco());
        db.update("Escola",cv,
                "_ID=?",new String[]{String.valueOf(aux.getId())});
    }

    public ArrayList<Escola> getEscolas() {
        cursor = getAllEscolasBanco();
        ArrayList<Escola> Escolas = new ArrayList<>();
        int indexNomeEscola = cursor.getColumnIndexOrThrow(CandidatoContract.EscolaBD.COLUMN_NAME_NOME_ESCOLA);
        int indexEnderecoEscola = cursor.getColumnIndexOrThrow(CandidatoContract.EscolaBD.COLUMN_NAME_ENDERECO);
        int indexIdEscola = cursor.getColumnIndexOrThrow(CandidatoContract.EscolaBD._ID);
        if(cursor.moveToFirst()){
            do{
                Escola temp = new Escola();
                temp.setNome(cursor.getString(indexNomeEscola))
                        .setEndereco(cursor.getString(indexEnderecoEscola))
                        .setId(Integer.parseInt(cursor.getString(indexIdEscola)));
                Escolas.add(temp);
            }while (cursor.moveToNext());
        }
        return Escolas;
    }
    public void addEscola(Escola e){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put(CandidatoContract.EscolaBD.COLUMN_NAME_NOME_ESCOLA, e.getNome());
        valores.put(CandidatoContract.EscolaBD.COLUMN_NAME_ENDERECO, e.getEndereco());
        valores.put(CandidatoContract.EscolaBD.COLUMN_NAME_MUNICIPIO_ESCOLA, e.getCidade());
        valores.put(CandidatoContract.EscolaBD.COLUMN_NAME_QUANTIDADE_SALAS_ESCOLA, e.getQuantidadesalas());
        db.insert(CandidatoContract.EscolaBD.TABLE_NAME,null, valores);

    }
    public void removeEscola(Escola e) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int rows=db.delete(CandidatoContract.EscolaBD.TABLE_NAME,
                "_ID=?",new String[]{String.valueOf(e.getId())});
    }

    public int getIndiceEscola(Escola e){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] visao = {
                CandidatoContract.EscolaBD.COLUMN_NAME_NOME_ESCOLA,
                CandidatoContract.EscolaBD._ID
        };
        String sort = CandidatoContract.EscolaBD.COLUMN_NAME_ENDERECO+ " DESC";

        Cursor c = db.query(CandidatoContract.EscolaBD.TABLE_NAME, visao,
                "Nome = ?",new String[]{e.getNome()} ,
                null,null, sort);
        Log.i("SQLTEST", "getCursorCandidato: "+c.getCount());
        if(c.moveToFirst()){
            return c.getInt(1);
        }
        return -1;
    }

    private Cursor getAllEscolasBanco() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] visao = {
                CandidatoContract.EscolaBD.COLUMN_NAME_NOME_ESCOLA,
                CandidatoContract.EscolaBD.COLUMN_NAME_ENDERECO,
                CandidatoContract.EscolaBD.COLUMN_NAME_MUNICIPIO_ESCOLA,
                CandidatoContract.EscolaBD.COLUMN_NAME_QUANTIDADE_SALAS_ESCOLA,
                CandidatoContract.EscolaBD._ID
        };
        String sort = CandidatoContract.EscolaBD.COLUMN_NAME_ENDERECO+ " DESC";
        Cursor c = db.query(CandidatoContract.EscolaBD.TABLE_NAME, visao,
                null,null,null,null, sort);
        Log.i("SQLTEST", "getCursorCandidato: "+c.getCount());
        return c;
    }


}

