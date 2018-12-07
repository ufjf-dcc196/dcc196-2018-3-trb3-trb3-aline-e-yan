package trab3.dcc196.ufjf.br.trabalho3.Banco;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CandidatoDBHelper extends SQLiteOpenHelper {


    public final static int DATABASE_VERSION = 1;
    public final static String DATABASE_NAME = "Candidato.db";

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
}
