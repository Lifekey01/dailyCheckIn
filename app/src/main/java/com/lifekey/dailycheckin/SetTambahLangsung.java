package com.lifekey.dailycheckin;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.lifekey.dailycheckin.model.Tanggal;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class SetTambahLangsung extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_tambah_langsung);
    }
    public void getTambahLangsung(){

    }
    private void getInputDialog() {

            MinputDialog = new MaterialAlertDialogBuilder(this);
            LayoutInflater inflater = this.getLayoutInflater();
            View dialogView = inflater.inflate(R.layout.dialog_tambah_event, null);
            EditText editText = dialogView.findViewById(R.id.editTanggal);
            Button btn_batal = dialogView.findViewById(R.id.batal);
            Button btn_simpan = dialogView.findViewById(R.id.simpan);
            editText.setText(currentDate);
            inputDialog.setView(dialogView);
            AlertDialog alertDialog = inputDialog.create();
            alertDialog.show();
            editText.setOnClickListener(view -> {
                DatePickerDialog datePickerDialog = new DatePickerDialog(this, (datePicker, year12, month12, day12) -> {
                    NumberFormat numberFormat =new DecimalFormat("00");
                    //tanggal_bayar=numberFormat.format(year12)+"-"+numberFormat.format((month12 +1))+"-"+numberFormat.format(day12);
                    editText.setText(numberFormat.format(day12)+"/"+numberFormat.format((month12 +1))+"/"+numberFormat.format(year12));
                },year,month,day);
                datePickerDialog.show();
            });
            btn_batal.setOnClickListener((v) -> alertDialog.dismiss());
            btn_simpan.setOnClickListener((v) -> {
                db.addRecordTanggal(new Tanggal(null,editText.getText().toString()));
                getTanggal();
                alertDialog.dismiss();
            });

    }
}
