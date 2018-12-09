package trab3.dcc196.ufjf.br.trabalho3.Persistence;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

import trab3.dcc196.ufjf.br.trabalho3.Banco.CandidatoContract;
import trab3.dcc196.ufjf.br.trabalho3.Banco.CandidatoDBHelper;
import trab3.dcc196.ufjf.br.trabalho3.models.Candidato;

public class CandidatoDAO {

    private static CandidatoDAO instance;
    private CandidatoDBHelper dbHelper;
    private SQLiteDatabase db;
    private Cursor cursor;
    private boolean feito = false;

    private CandidatoDAO(Context context) {
        dbHelper = new CandidatoDBHelper(context);

    }

    public static CandidatoDAO getInstance(Context context) {
        if (instance == null){
            instance = new CandidatoDAO(context);
        }
        return instance;
    }

    public void insercaoCandidatoBanco(Candidato candidato){
        db = dbHelper.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put(CandidatoContract.CandidatoBD.COLUMN_NAME_NOME,candidato.getNome());
        valores.put(CandidatoContract.CandidatoBD.COLUMN_NAME_CPF, candidato.getCpf());
        valores.put(CandidatoContract.CandidatoBD.COLUMN_NAME_PROVA, candidato.getProva());
        valores.put(CandidatoContract.CandidatoBD.COLUMN_NAME_ID_ESCOLA, String.valueOf(candidato.getId_escola()));
        db.insert(CandidatoContract.CandidatoBD.TABLE_NAME,null, valores);

    }

    public Candidato getCandidatoById(int id){
        SQLiteDatabase db2 = dbHelper.getReadableDatabase();
        String MY_QUERY =
                "select * from "+CandidatoContract.CandidatoBD.TABLE_NAME+" where _ID = ? ";
        cursor= db2.rawQuery(MY_QUERY, new String[]{String.valueOf(id)});
        Log.i("SQLTEST", "getCursorCandidato: "+cursor.getCount());


        int indexNomeCandidato = cursor.getColumnIndexOrThrow(CandidatoContract.CandidatoBD.COLUMN_NAME_NOME);
        int indexCPFCandidato = cursor.getColumnIndexOrThrow(CandidatoContract.CandidatoBD.COLUMN_NAME_CPF);
        int indexTipoProva = cursor.getColumnIndexOrThrow(CandidatoContract.CandidatoBD.COLUMN_NAME_PROVA);
        int indexNomeIDEscola = cursor.getColumnIndexOrThrow(CandidatoContract.CandidatoBD.COLUMN_NAME_ID_ESCOLA);
        int indexIdEstudante = cursor.getColumnIndexOrThrow(CandidatoContract.CandidatoBD._ID);
        Candidato CandidatoSolicitado = null;
        if(cursor.moveToFirst()) {
            CandidatoSolicitado = new Candidato();
            CandidatoSolicitado.setNome(cursor.getString(indexNomeCandidato))
                    .setCpf(cursor.getString(indexCPFCandidato))
                    .setId_escola(cursor.getInt(indexNomeIDEscola))
                    .setProva(cursor.getString(indexTipoProva))
                    .setId(indexIdEstudante);
        }
        return CandidatoSolicitado;
    }
    public Cursor getAllEstudantesBanco() { SQLiteDatabase db2 = dbHelper.getReadableDatabase();
        String MY_QUERY ="select * from "+CandidatoContract.CandidatoBD.TABLE_NAME;
        cursor= db2.rawQuery(MY_QUERY,null);

        Log.i("SQLTEST", "getCursorCandidato: "+cursor.getCount());
        return cursor;
    }


}

