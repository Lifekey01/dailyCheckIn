package com.lifekey.dailycheckin;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.andremion.counterfab.CounterFab;
import com.lifekey.dailycheckin.R;
import com.lifekey.dailycheckin.adapter.TanggalAdapter;
import com.lifekey.dailycheckin.helper.Database;
import com.lifekey.dailycheckin.model.Tanggal;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    AlertDialog inputDialog;
    CounterFab counterFab;
    Database db;
    RecyclerView recyclerView;
    List<Tanggal> tanggalList = new ArrayList<>();
    TanggalAdapter tanggalAdapter;
    Date now;
    Calendar calendar = Calendar.getInstance();
    int year = calendar.get(Calendar.YEAR);
    int month = calendar.get(Calendar.MONTH);
    int day = calendar.get(Calendar.DAY_OF_MONTH);
    String currentDate;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerTanggal);
        counterFab = findViewById(R.id.fab_TambahTanggal);
        db = new Database(this);
        tanggalAdapter = new TanggalAdapter(tanggalList,this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(tanggalAdapter);
        counterFab.setOnClickListener((v) -> {
            getInputDialog();
        });
        getTanggal();
        getTanggalNow();
    }
    private AlertDialog getInputDialog() {
        if (inputDialog == null) {
            LayoutInflater inflater = this.getLayoutInflater();
            View dialogView = inflater.inflate(R.layout.dialog_tambah_event, null);
            EditText editText = dialogView.findViewById(R.id.editText);
            editText.setText(currentDate);
            editText.setOnClickListener(view -> {
               DatePickerDialog datePickerDialog = new DatePickerDialog(this, (datePicker, year12, month12, day12) -> {
                    NumberFormat numberFormat =new DecimalFormat("00");
                    //tanggal_bayar=numberFormat.format(year12)+"-"+numberFormat.format((month12 +1))+"-"+numberFormat.format(day12);
                    editText.setText(numberFormat.format(day12)+"/"+numberFormat.format((month12 +1))+"/"+numberFormat.format(year12));
                },year,month,day);
                datePickerDialog.show();
            });
            inputDialog = new AlertDialog.Builder(this)
                    .setTitle("Tambah Tanggal")
                    .setView(dialogView)
                    .setPositiveButton("Tambah", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            db.addRecordTanggal(new Tanggal(null,editText.getText().toString()));
                            getTanggal();
                            editText.setText("");
                        }
                    })
                    .setNegativeButton("tutup", null)
                    .create();

            inputDialog.setOnShowListener(new DialogInterface.OnShowListener() {
                @Override
                public void onShow(DialogInterface dialog) {
                    editText.requestFocus();
//                    InputMethodManager imm = (InputMethodManager) requireContext().getSystemService(Context.INPUT_METHOD_SERVICE);
//                    imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
                }
            });

            inputDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                @Override
                public void onDismiss(DialogInterface dialog) {
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
                    inputDialog = null;
                }
            });
            inputDialog.show();
        }
        return inputDialog;
    }

    public void getTanggal(){
       tanggalList.clear();
       tanggalList.addAll(db.getAllRecordTanggal());
        tanggalAdapter.notifyDataSetChanged();
    }
    public void getTanggalNow(){
        // Mendapatkan tanggal sekarang
        calendar = Calendar.getInstance();
        now = calendar.getTime();

        // Format tanggal yang diinginkan (contoh: "dd/MM/yyyy")
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        currentDate = sdf.format(now);
    }
}