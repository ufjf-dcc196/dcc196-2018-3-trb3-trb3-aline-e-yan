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
    public final static String DATABASE_NAME = "CandidatoBD.db";
    public Cursor cursor;
    public CandidatoDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CandidatoContract.CandidatoBD.CREATE_CANDIDATO);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL(CandidatoContract.CandidatoBD.DROP_CANDIDATO);
        db.execSQL(CandidatoContract.CandidatoBD.DELETE_CANDIDATO);
        onCreate(db);
    }





}
