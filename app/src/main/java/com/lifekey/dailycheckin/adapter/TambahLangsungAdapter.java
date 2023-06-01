package com.lifekey.dailycheckin.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lifekey.dailycheckin.KegiatanActivity;
import com.lifekey.dailycheckin.MainActivity;
import com.lifekey.dailycheckin.R;
import com.lifekey.dailycheckin.SetTambahLangsung;
import com.lifekey.dailycheckin.helper.Database;
import com.lifekey.dailycheckin.model.TambahLangsung;

import java.util.ArrayList;
import java.util.List;

public class TambahLangsungAdapter extends RecyclerView.Adapter<TambahLangsungAdapter.TambahLangsungViewHolder> {

    List<TambahLangsung> tambahLangsungList = new ArrayList<>();
    Context context;
    Database db;

    @NonNull
    @Override
    public TambahLangsungViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tambah_langsung,parent,false);
        return new TambahLangsungViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull TambahLangsungViewHolder holder, int position) {
            TambahLangsung tambahLangsung = tambahLangsungList.get(holder.getAdapterPosition());
            holder.tvNamaKegiatan.setText(tambahLangsung.getKegiatan());
            holder.tvTanggalKegiatan.setText(tambahLangsung.getTanggal());
        holder.ivHapus.setOnLongClickListener(v -> {
            db.deleteTanggal(tambahLangsung.getId());
            ((SetTambahLangsung) context).getTambahLangsung();
            notifyDataSetChanged();
            return false;
        });
        holder.ivHapus.setOnClickListener(v -> Toast.makeText(context,"Tahan Jika Ingin Menghapus",Toast.LENGTH_SHORT).show());
        holder.itemView.setOnClickListener((v)->{
            Intent intent = new Intent(context, KegiatanActivity.class);
            intent.putExtra("idTanggal",tambahLangsung.getId());
            intent.putExtra("tanggal",tambahLangsung.getTanggal());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return tambahLangsungList.size();
    }

    public class TambahLangsungViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView tvNamaKegiatan,tvTanggalKegiatan;
        ImageView ivHapus;
        public TambahLangsungViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNamaKegiatan = itemView.findViewById(R.id.namaKegiatan);
            tvTanggalKegiatan = itemView.findViewById(R.id.tanggalKegiatan);
            ivHapus = itemView.findViewById(R.id.hapus);
        }

        @Override
        public void onClick(View v) {

        }
    }
}
