package com.lifekey.dailycheckin;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.lifekey.dailycheckin.helper.Database;
import com.lifekey.dailycheckin.model.Model;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class TambahKegiatanActivity extends AppCompatActivity {
    Database db;
    CheckBox checkBox;
    EditText kegiatan,tanggal;
    Button button, buttonLangsung;
    String idTanggal,tanggalIsi;
    FloatingActionButton fab_kembali,fab_setTambahLangsung;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_kegiatan);
        checkBox = findViewById(R.id.checkbox);
        kegiatan = findViewById(R.id.kegiatan);
        tanggal = findViewById(R.id.tanggal);
        button = findViewById(R.id.TambahKegiatan);
        buttonLangsung = findViewById(R.id.TambahLangsung);
        fab_kembali = findViewById(R.id.kembali);
        fab_setTambahLangsung = findViewById(R.id.setTambahLangsung);
        db = new Database(this);
        Intent intent = getIntent();
        idTanggal = intent.getStringExtra("idTanggal");
        tanggalIsi = intent.getStringExtra("tanggal");

        tanggal.setText(tanggalIsi);

        button.setOnClickListener((v) -> {
            TambahKegiatanDB();
            finish();
        });

        buttonLangsung.setOnClickListener((v) -> {
            TambahKegiatanDBLangsung();
            finish();
        });

        fab_kembali.setOnClickListener((V) -> onBackPressed());
    }


    public void TambahKegiatanDB(){
        db.addRecordKegiatan(new Model(null,kegiatan.getText().toString(),idTanggal,tanggalIsi,String.valueOf(checkBox.isChecked())));
    }
    public void TambahKegiatanDBLangsung(){
        db.addRecordKegiatan(new Model(null,"Olahraga 15 menit",idTanggal,tanggalIsi,String.valueOf(checkBox.isChecked())));
        db.addRecordKegiatan(new Model(null,"daily genshin impact",idTanggal,tanggalIsi,String.valueOf(checkBox.isChecked())));
        db.addRecordKegiatan(new Model(null,"daily Honkai impact",idTanggal,tanggalIsi,String.valueOf(checkBox.isChecked())));
        db.addRecordKegiatan(new Model(null,"daily Honkai Star Rail",idTanggal,tanggalIsi,String.valueOf(checkBox.isChecked())));
        db.addRecordKegiatan(new Model(null,"Udemy 15 menit",idTanggal,tanggalIsi,String.valueOf(checkBox.isChecked())));
        db.addRecordKegiatan(new Model(null,"Duolingo 15 Meni",idTanggal,tanggalIsi,String.valueOf(checkBox.isChecked())));
        db.addRecordKegiatan(new Model(null,"daily Nikke",idTanggal,tanggalIsi,String.valueOf(checkBox.isChecked())));
    }
}
