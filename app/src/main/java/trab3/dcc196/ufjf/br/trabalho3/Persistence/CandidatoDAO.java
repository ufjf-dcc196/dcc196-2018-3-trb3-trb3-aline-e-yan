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
        db = dbHelper.getWritableDatabase();
    }

    public static CandidatoDAO getInstance(Context context) {
        if (instance == null){
            instance = new CandidatoDAO(context);
        }
        return instance;
    }

    public void insercaoCandidatoBanco(Candidato candidato){
        ContentValues valores = new ContentValues();
        valores.put(CandidatoContract.CandidatoBD.COLUMN_NAME_NOME,candidato.getNome());
        valores.put(CandidatoContract.CandidatoBD.COLUMN_NAME_CPF, candidato.getCpf());
        valores.put(CandidatoContract.CandidatoBD.COLUMN_NAME_ID_ESCOLA, candidato.getId_escola());
        valores.put(CandidatoContract.CandidatoBD.COLUMN_NAME_PROVA, candidato.getProva());
        db.insert(CandidatoContract.CandidatoBD.TABLE_NAME,null, valores);
    }

    public Candidato getCandidatoById(int id){
        String MY_QUERY =
                "select * from Candidato where ID_PARTICIPANTE = ? ";
        cursor= db.rawQuery(MY_QUERY, new String[]{String.valueOf(id)});

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
    public Cursor getAllEstudantesBanco() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] visao = {
                CandidatoContract.CandidatoBD.COLUMN_NAME_NOME,
                CandidatoContract.CandidatoBD.COLUMN_NAME_CPF,
                CandidatoContract.CandidatoBD.COLUMN_NAME_ID_ESCOLA,
                CandidatoContract.CandidatoBD._ID
        };
        String sort = CandidatoContract.CandidatoBD.COLUMN_NAME_CPF+ " DESC";
        Cursor c = db.query(CandidatoContract.CandidatoBD.TABLE_NAME, visao,
                null,null,null,null, sort);
        Log.i("SQLTEST", "getCursorCandidato: "+c.getCount());
        return c;
    }


}

