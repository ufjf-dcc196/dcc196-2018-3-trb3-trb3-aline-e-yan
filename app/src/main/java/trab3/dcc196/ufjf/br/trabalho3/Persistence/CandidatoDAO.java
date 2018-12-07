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
import trab3.dcc196.ufjf.br.trabalho3.models.Estudante;

public class CandidatoDAO {

    private static CandidatoDAO instance = new CandidatoDAO();
    private CandidatoDBHelper dbHelper;
    private Cursor cursor;
    private boolean feito = false;

    private CandidatoDAO() {
    }

    public static CandidatoDAO getInstance(){
        return instance;
    }

    private void insercaoBanco(){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put(CandidatoContract.CandidatoBD.COLUMN_NAME_NOME_CANDIDATO,"Aline");
        valores.put(CandidatoContract.CandidatoBD.COLUMN_NAME_CPF, "Escola Estadual Franscisco Bernardino");
        valores.put(CandidatoContract.CandidatoBD.COLUMN_NAME_ID_ESCOLA, "1");
        db.insert(CandidatoContract.CandidatoBD.TABLE_NAME,null, valores);


    }

    public void inicializarDBHelper(Context c){
        dbHelper = new CandidatoDBHelper(c);
        }


    public Estudante getEstudanteById(int idEstudante){
        int indexNomeCandidato = cursor.getColumnIndexOrThrow(CandidatoContract.CandidatoBD.COLUMN_NAME_NOME_CANDIDATO);
        int indexCPFCandidato = cursor.getColumnIndexOrThrow(CandidatoContract.CandidatoBD.COLUMN_NAME_CPF);
        int indexNomeIDEscola = cursor.getColumnIndexOrThrow(CandidatoContract.CandidatoBD.COLUMN_NAME_ID_ESCOLA);
        int indexIdEstudante = cursor.getColumnIndexOrThrow(CandidatoContract.CandidatoBD._ID);
        Estudante CandidatoSolicitado = null;
        if(cursor.moveToFirst()) {
            CandidatoSolicitado = new Estudante();
            CandidatoSolicitado.setNome(cursor.getString(indexNomeCandidato))
                    .setCpf(cursor.getString(indexCPFCandidato))
                    .setId_escola(cursor.getInt(indexNomeIDEscola));
        }
        return CandidatoSolicitado;
    }
    public void atualizarEstudante(Estudante aux) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("Nome", aux.getNome());
        cv.put("CPF", aux.getCpf());
        cv.put("Escola",aux.getId_escola());
        db.update("Estudante",cv,
                "_ID=?",new String[]{String.valueOf(aux.getId())});
    }
    public ArrayList<Estudante> getEstudantes() {
        cursor = getAllEstudantesBanco();
        ArrayList<Estudante> Estudantes = new ArrayList<>();
        int indexNomeCandidato = cursor.getColumnIndexOrThrow(CandidatoContract.CandidatoBD.COLUMN_NAME_NOME_CANDIDATO);
        int indexCPFCandidato = cursor.getColumnIndexOrThrow(CandidatoContract.CandidatoBD.COLUMN_NAME_CPF);
        int indexNomeIDEscola = cursor.getColumnIndexOrThrow(CandidatoContract.CandidatoBD.COLUMN_NAME_ID_ESCOLA);
        int indexIdEstudante = cursor.getColumnIndexOrThrow(CandidatoContract.CandidatoBD._ID);
        if(cursor.moveToFirst()){
            do{
                Estudante temp = new Estudante();
                temp.setNome(cursor.getString(indexNomeCandidato))
                        .setCpf(cursor.getString(indexCPFCandidato))
                        .setId_escola(cursor.getInt(indexNomeIDEscola));
                Estudantes.add(temp);
            }while (cursor.moveToNext());
        }
        return Estudantes;
    }
    public void addEstudante(Estudante e){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put(CandidatoContract.CandidatoBD.COLUMN_NAME_NOME_CANDIDATO, e.getNome());
        valores.put(CandidatoContract.CandidatoBD.COLUMN_NAME_CPF, e.getCpf());
        valores.put(CandidatoContract.CandidatoBD.COLUMN_NAME_ID_ESCOLA, e.getId_escola());
        db.insert(CandidatoContract.CandidatoBD.TABLE_NAME,null, valores);

    }
    public void removeEstudante(Estudante e) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int rows=db.delete(CandidatoContract.CandidatoBD.TABLE_NAME,
                "_ID=?",new String[]{String.valueOf(e.getId())});
    }

    public int getIndiceEstudante(Estudante e){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] visao = {
                CandidatoContract.CandidatoBD.COLUMN_NAME_NOME_CANDIDATO,
                CandidatoContract.CandidatoBD._ID
        };
        String sort = CandidatoContract.CandidatoBD.COLUMN_NAME_CPF+ " DESC";

        Cursor c = db.query(CandidatoContract.CandidatoBD.TABLE_NAME, visao,
                "Nome = ?",new String[]{e.getNome()} ,
                null,null, sort);
        Log.i("SQLTEST", "getCursorCandidato "+c.getCount());
        if(c.moveToFirst()){
            return c.getInt(1);
        }
        return -1;
    }

    private Cursor getAllEstudantesBanco() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] visao = {
                CandidatoContract.CandidatoBD.COLUMN_NAME_NOME_CANDIDATO,
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

