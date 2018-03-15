package com.elis.mvalier1.findme.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by mvalier1 on 24/01/2018.
 */

public class DBHandler {

    //  Columns
    public static final String ID = "_id";
    public static final String NOM = "nom";
    public static final String NUMERO = "numero";
    public static final String CAPACITE = "capacite";
    public static final String EQUIPEMENT = "equipement";
    public static final String TELEPHONE = "telephone";
    public static final String AILE = "aile";
    public static final String NIVEAU = "niveau";
    public static final String COTE = "cote";
    public static final String EMPLACEMENT = "emplacement";
    //  Database
    private static final int DATABASE_VERSION = 35;
    private static final String DATABASE_NAME = "findme";
    private static final String TABLE_NAME = "salle";
    public static final String CREATE_TABLE_SALLE = "CREATE TABLE " + TABLE_NAME +
            " (" +
            " " + ID + " INTEGER primary key autoincrement," +
            " " + NOM + "," +
            /*" "+NUMERO+" TEXT"+
            " "+CAPACITE+" TEXT"+
            " "+EQUIPEMENT+" TEXT"+
            " "+TELEPHONE+" TEXT"+*/
            " " + AILE + "," +
            " " + NIVEAU + "," +
            " " + COTE + "," +
            " " + EMPLACEMENT + "" +
            ");";
    private final Context mCtx;
    DatabaseHelper mDbHelper;
    SQLiteDatabase mDb;

    public DBHandler(Context ctx) {
        this.mCtx = ctx;
    }

    public DBHandler open() throws SQLException {

        mDbHelper = new DatabaseHelper(mCtx);
        mDb = mDbHelper.getWritableDatabase();

        return this;
    }

    public void close() {
        if (mDbHelper != null)
            mDbHelper.close();
    }

    public long createSalle(String nom, String aile, String niveau, String cote, String emplacement) {

        ContentValues values = new ContentValues();

        values.put(NOM, nom);
        values.put(AILE, aile);
        values.put(NIVEAU, niveau);
        values.put(COTE, cote);
        values.put(EMPLACEMENT, emplacement);

        return mDb.insert(TABLE_NAME, null, values);

    }

    public boolean deleteAllSalles() {
        int doneDelete = 0;
        doneDelete = mDb.delete(TABLE_NAME, null, null);
        return doneDelete > 0;
    }

    public Cursor fetchSallesByName(String inputText) throws SQLException {
        Cursor cursor = null;

        if (inputText == null || inputText.length() == 0)
            cursor = mDb.query(TABLE_NAME, new String[]{ID, NOM, AILE, NIVEAU, COTE, EMPLACEMENT}, null, null, null, null, NOM, null);
        else
            cursor = mDb.query(TABLE_NAME, new String[]{ID, NOM, AILE, NIVEAU, COTE, EMPLACEMENT}, NOM + " like '%" + inputText + "%'", null, null, null, NOM, null);
        if (cursor != null)
            cursor.moveToFirst();

        return cursor;
    }

    public Cursor fetchAllSalles() {
        Cursor cursor = mDb.query(TABLE_NAME, new String[]{ID, NOM, AILE, NIVEAU, COTE, EMPLACEMENT}, null, null, null, null, NOM, null);

        if (cursor != null)
            cursor.moveToFirst();

        return cursor;
    }

    public void insertSalles() {

        createSalle("Prado", "a", "rdj", "", "");

        createSalle("Brandebourg", "c", "rdc", "milieu", "milieu");
        createSalle("Grand Place", "b", "rdc", "milieu", "milieu");
        createSalle("Samba", "c", "rdc", "droite", "milieu");
        createSalle("Colisée", "c", "rdc", "gauche", "fin");
        createSalle("Douro", "", "rdc", "", "sur la gauche juste après les barrières");
        createSalle("Hergé", "b", "rdc", "droite", "milieu");

        createSalle("Capitole", "a", "1", "droite", "fin");
        createSalle("Croisette", "c", "1", "gauche", "milieu");
        createSalle("Mont-Blanc", "c", "1", "droite", "milieu");
        createSalle("Duomo", "face_ascenceur", "1", "face", "face");

        createSalle("Gaudi", "face_ascenceur", "2", "face", "face");

        createSalle("Vasco de Gama", "face_ascenceur", "3", "face", "face");
        createSalle("Grand Duche", "b", "3", "gauche", "milieu");

        createSalle("Des Lacs", "face_ascenceur", "4", "face", "face");

        createSalle("Corcovado", "face_ascenceur", "5", "face", "face");
        createSalle("Beffroi", "a", "5", "droite", "milieu");
//        createSalle("Cristal", "b", "5", "droite", "debut");
        createSalle("Chenonceau", "b", "5", "gauche", "milieu");

        createSalle("Eiffel", "face_ascenceur", "6", "face", "face");


    }

    public static class DatabaseHelper extends SQLiteOpenHelper {

        public DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            Log.w("DB ", CREATE_TABLE_SALLE);
            sqLiteDatabase.execSQL(CREATE_TABLE_SALLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
            // Drop older table if existed
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            // Creating tables again
            onCreate(sqLiteDatabase);
        }
    }

}
