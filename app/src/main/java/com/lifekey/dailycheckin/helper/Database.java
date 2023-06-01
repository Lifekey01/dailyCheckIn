package com.lifekey.dailycheckin.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.lifekey.dailycheckin.model.Model;
import com.lifekey.dailycheckin.model.TambahLangsung;
import com.lifekey.dailycheckin.model.Tanggal;

import java.util.ArrayList;
import java.util.List;

public class Database extends SQLiteOpenHelper {


    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "daily";
    private static final String TABLE_KEGIATAN = "Kegiatan";
    private static final String TABLE_TANGGAL = "Tanggal";
    private static final String TABLE_TAMBAH_LANGSUNG = "Tambah_Langsung";
    // column tables
    private static final String KEY_ID = "id";
    private static final String KEY_ID_TANGGAL = "id_tanggal";
    private static final String KEY_ID_TAMBAH_LANGSUNG = "id_tambah_langsung";
    private static final String KEY_NAME = "nama_kegiatan";
    private static final String KEY_TANGGAL = "tanggal";
    private static final String KEY_STATUS = "status";

    public Database(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_CONTACTS_TABLE_TANGGAL = "CREATE TABLE " + TABLE_TANGGAL + "("
                + KEY_ID_TANGGAL + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," + KEY_TANGGAL + " DEFAULT CURRENT_TIMESTAMP"+ ")";
        db.execSQL(CREATE_CONTACTS_TABLE_TANGGAL);

        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_KEGIATAN + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL ," + KEY_NAME + " TEXT,"
                +KEY_ID_TANGGAL+" INTEGER,"+ KEY_TANGGAL+" DEFAULT CURRENT_TIMESTAMP,"+ KEY_STATUS + " TEXT" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);

        String CREATE_CONTACTS_TABLE_TAMBAH_LANGSUNG = "CREATE TABLE " + TABLE_TAMBAH_LANGSUNG + "("
                + KEY_ID_TAMBAH_LANGSUNG + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," + KEY_NAME + " TEXT,"
        +KEY_ID_TANGGAL+" INTEGER,"+ KEY_TANGGAL+" DEFAULT CURRENT_TIMESTAMP,"+ KEY_ID +" INTEGER "+")";

        db.execSQL(CREATE_CONTACTS_TABLE_TAMBAH_LANGSUNG);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_KEGIATAN);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TANGGAL);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TAMBAH_LANGSUNG);
        onCreate(db);
    }

    public void addRecordTanggal(Tanggal tanggal){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_TANGGAL,tanggal.getTanggal().toString());

        db.insert(TABLE_TANGGAL,null,values);
        db.close();
    }
    public void addRecordKegiatan(Model model){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, model.getNamaKegiatan());
        values.put(KEY_ID_TANGGAL, model.getId_tanggal());
        values.put(KEY_TANGGAL, model.getTanggalKegiatan());
        values.put(KEY_STATUS, model.getStatus());
        db.insert(TABLE_KEGIATAN, null, values);
        db.close();
    }
    public void addRecordTambahLangsung(TambahLangsung tambahLangsung){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, tambahLangsung.getKegiatan());
        values.put(KEY_ID_TANGGAL,tambahLangsung.getId_tanggal());
        values.put(KEY_TANGGAL,tambahLangsung.getTanggal());
        values.put(KEY_ID,tambahLangsung.getId_model());
    }


    public Tanggal getRecordTanggal(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_TANGGAL, new String[] { KEY_ID, KEY_TANGGAL}, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null) cursor.moveToFirst();

        Tanggal contact = new Tanggal(cursor.getString(0), cursor.getString(1));
        // return contact
        return contact;
    }

    public List<Tanggal> getAllRecordTanggal() {
        List<Tanggal> contactList = new ArrayList<Tanggal>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_TANGGAL;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Tanggal userModels = new Tanggal();
                userModels.setId(cursor.getString(0));
                userModels.setTanggal(cursor.getString(1));

                contactList.add(userModels);
            } while (cursor.moveToNext());
        }

        // return contact list
        return contactList;
    }

    public List<Model> getAllRecordKegiatan(String idTanggal){
        List<Model> kegiatanList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        String selectQuery = "SELECT * FROM " + TABLE_KEGIATAN + " WHERE id_tanggal = "+idTanggal;
        Cursor cursor = db.rawQuery(selectQuery,null);
        if (cursor.moveToFirst()){
            do {
                Model kegiatanModels = new Model();
                kegiatanModels.setId(cursor.getString(0));
                kegiatanModels.setNamaKegiatan(cursor.getString(1));
                kegiatanModels.setId_tanggal(cursor.getString(2));
                kegiatanModels.setTanggalKegiatan(cursor.getString(3));
                kegiatanModels.setStatus(cursor.getString(4));
                kegiatanList.add(kegiatanModels);
            } while (cursor.moveToNext());
        }
        return  kegiatanList;
    }

    public  void updateKegiatan(String idModel,Boolean l){
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "UPDATE Kegiatan SET status = '" + l +"' WHERE id =" + idModel;
        db.execSQL(sql);
        db.close();
    }

    public void deleteTanggal(String idTanggal){
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "DELETE FROM Tanggal where id_tanggal = '"+idTanggal+"'";
        db.execSQL(sql);
        db.close();
    }

}
