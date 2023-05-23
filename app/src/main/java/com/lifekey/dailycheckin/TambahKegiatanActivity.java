package com.lifekey.dailycheckin;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.lifekey.dailycheckin.R;
import com.lifekey.dailycheckin.helper.Database;
import com.lifekey.dailycheckin.model.Model;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class TambahKegiatanActivity extends AppCompatActivity {
    Database db;
    CheckBox checkBox;
    EditText kegiatan,tanggal;
    Button button,buttoLangsung;
    String idTanggal,tanggalIsi;
    FloatingActionButton floatingActionButton;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_kegiatan_tes);
        checkBox = findViewById(R.id.checkbox);
        kegiatan = findViewById(R.id.kegiatan);
        tanggal = findViewById(R.id.tanggal);
        button = findViewById(R.id.TambahKegiatan);
        buttoLangsung = findViewById(R.id.TambahLangsung);
        floatingActionButton = findViewById(R.id.fab_kembali);
        db = new Database(this);
        Intent intent = getIntent();
        idTanggal = intent.getStringExtra("idTanggal");
        tanggalIsi = intent.getStringExtra("tanggal");

        tanggal.setText(tanggalIsi);

        button.setOnClickListener((v) -> {
            TambahKegiatanDB();
            finish();
        });

        buttoLangsung.setOnClickListener((v) -> {
            TambahKegiatanDBLangsung();
            finish();
        });

        floatingActionButton.setOnClickListener((V) -> onBackPressed());
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
