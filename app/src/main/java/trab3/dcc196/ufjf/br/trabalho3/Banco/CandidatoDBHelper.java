package trab3.dcc196.ufjf.br.trabalho3.Banco;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import trab3.dcc196.ufjf.br.trabalho3.models.Candidato;

public class CandidatoDBHelper extends SQLiteOpenHelper {


    public final static int DATABASE_VERSION = 1;
    public final static String DATABASE_NAME = "Candidato.db";
    public Cursor cursor;
    public CandidatoDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CandidatoContract.CandidatoBD.CREATE_EVENTO);
        db.execSQL(CandidatoContract.EscolaBD.CREATE_PARTICPANTE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL(CandidatoContract.CandidatoBD.DROP_CANDIDATO);
        db.execSQL(CandidatoContract.CandidatoBD.DELETE_CANDIDATO);
        db.execSQL(CandidatoContract.EscolaBD.DROP_ESCOLA);
        onCreate(db);
    }

    public Cursor getAllEstudantesBanco() {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] visao = {
                CandidatoContract.CandidatoBD.COLUMN_NAME_NOME_CANDIDATO,
                CandidatoContract.CandidatoBD.COLUMN_NAME_CPF,
                CandidatoContract.CandidatoBD.COLUMN_NAME_PROVA,
                CandidatoContract.CandidatoBD.COLUMN_NAME_ID_ESCOLA,
                CandidatoContract.CandidatoBD._ID
        };
        String sort = CandidatoContract.CandidatoBD.COLUMN_NAME_CPF+ " DESC";
        Cursor c = db.query(CandidatoContract.CandidatoBD.TABLE_NAME, visao,
                null,null,null,null, sort);
        Log.i("SQLTEST", "getCursorCandidato: "+c.getCount());
        return c;
    }

    public void insercaoCandidatoBanco(Candidato candidato){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put(CandidatoContract.CandidatoBD.COLUMN_NAME_NOME_CANDIDATO,candidato.getNome());
        valores.put(CandidatoContract.CandidatoBD.COLUMN_NAME_CPF, candidato.getCpf());
        valores.put(CandidatoContract.CandidatoBD.COLUMN_NAME_ID_ESCOLA, candidato.getId_escola());
        valores.put(CandidatoContract.CandidatoBD.COLUMN_NAME_PROVA, candidato.getProva());
        db.insert(CandidatoContract.CandidatoBD.TABLE_NAME,null, valores);
    }

}
