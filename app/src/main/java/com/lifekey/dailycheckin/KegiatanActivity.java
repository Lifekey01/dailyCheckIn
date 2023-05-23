package com.lifekey.dailycheckin;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.andremion.counterfab.CounterFab;
import com.lifekey.dailycheckin.R;
import com.lifekey.dailycheckin.adapter.ModelAdapter;
import com.lifekey.dailycheckin.helper.Database;
import com.lifekey.dailycheckin.model.Model;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class KegiatanActivity extends AppCompatActivity {
    CounterFab counterFab;
    FloatingActionButton floatingActionButton;
    String idTanggal,tanggal;
    RecyclerView recyclerView;
    ModelAdapter modelAdapter;
    List<Model> modelList = new ArrayList<>();
    Database db;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kegiatan_tes);
        counterFab = findViewById(R.id.fab_TambahKegiatan);
        recyclerView = findViewById(R.id.recyclerKegiatan);
        modelAdapter = new ModelAdapter(modelList,this);
        db = new Database(this);
        floatingActionButton = findViewById(R.id.fab_kembali);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(modelAdapter);

        Intent intent = getIntent();
        idTanggal = intent.getStringExtra("idTanggal");
        tanggal = intent.getStringExtra("tanggal");

        counterFab.setOnClickListener((v) -> {
            Intent intent1 = new Intent(this, TambahKegiatanActivity.class);
            intent1.putExtra("idTanggal",idTanggal);
            intent1.putExtra("tanggal",tanggal);
            startActivity(intent1);
        });

        floatingActionButton.setOnClickListener((v) -> onBackPressed());
        getKegiatanDB();
    }

    public void getKegiatanDB(){
        Log.e("listKegiatan", String.valueOf(db.getAllRecordKegiatan(idTanggal)));
        modelList.clear();
        modelList.addAll(db.getAllRecordKegiatan(idTanggal));
        modelAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getKegiatanDB();
    }
}
