package trab3.dcc196.ufjf.br.trabalho3.Banco;

import android.provider.BaseColumns;

public class CandidatoContract
{
    public final class CandidatoBD implements BaseColumns {
        public final static String TABLE_NAME = "Candidato";
        public final static String COLUMN_NAME_NOME_CANDIDATO = "Nome";
        public final static String COLUMN_NAME_CPF = "CPF";
        public final static String COLUMN_NAME_ID_ESCOLA = "ID_ESCOLA";

        public final static String CREATE_EVENTO = "CREATE TABLE "+CandidatoBD.TABLE_NAME+" ("
                + CandidatoBD._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + CandidatoBD.COLUMN_NAME_NOME_CANDIDATO+ " TEXT ,"
                + CandidatoBD.COLUMN_NAME_CPF + " TEXT ,"
                + CandidatoBD.COLUMN_NAME_CPF+ " TEXT ,"
                + CandidatoBD.COLUMN_NAME_ID_ESCOLA+ " INTERGER"
                +")";
        public final static String DROP_CANDIDATO= "DROP TABLE IF EXISTS "+CandidatoBD.TABLE_NAME;
        public final static String DELETE_CANDIDATO= "DELETE FROM" + CandidatoBD.TABLE_NAME+ "WHERE id=" + CandidatoBD._ID;



    }

    public final class EscolaBD implements BaseColumns {
        public final static String TABLE_NAME = "Escola";
        public final static String COLUMN_NAME_NOME_ESCOLA = "NOME_ESCOLA";
        public final static String COLUMN_NAME_ENDERECO= "ENDERECO";

        public final static String CREATE_PARTICPANTE  = "CREATE TABLE "+EscolaBD.TABLE_NAME+" ("
                + EscolaBD._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + EscolaBD.COLUMN_NAME_NOME_ESCOLA + " TEXT ,"
                + EscolaBD.COLUMN_NAME_ENDERECO+ " TEXT"
               
                +")";
        public final static String DROP_ESCOLA = "DROP TABLE IF EXISTS "+EscolaBD.TABLE_NAME;

    }

}