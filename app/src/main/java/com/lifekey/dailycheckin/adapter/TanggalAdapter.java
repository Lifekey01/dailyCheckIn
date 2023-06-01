package com.lifekey.dailycheckin.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.lifekey.dailycheckin.MainActivity;
import com.lifekey.dailycheckin.R;
import com.lifekey.dailycheckin.KegiatanActivity;
import com.lifekey.dailycheckin.helper.Database;
import com.lifekey.dailycheckin.model.Tanggal;

import java.util.ArrayList;
import java.util.List;

public class TanggalAdapter extends RecyclerView.Adapter<TanggalAdapter.TanggalViewHolder> {
    List<Tanggal> tanggalList = new ArrayList<>();
    Context context;
    Database db;

    public TanggalAdapter(List<Tanggal> tanggalList, Context context, Database db) {
        this.tanggalList = tanggalList;
        this.context = context;
        this.db = db;
    }

    @NonNull
    @Override
    public TanggalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tanggal,parent,false);
        return new TanggalViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TanggalViewHolder holder, int position) {
        Tanggal tanggal = tanggalList.get(holder.getAdapterPosition());
        holder.tvTanggal.setText(tanggal.getTanggal());
        holder.ivHapus.setOnLongClickListener(v -> {
            db.deleteTanggal(tanggal.getId());
            ((MainActivity) context).getTanggal();
            notifyDataSetChanged();
            return false;
        });
        holder.ivHapus.setOnClickListener(v -> Toast.makeText(context,"Tahan Jika Ingin Menghapus",Toast.LENGTH_SHORT).show());
        holder.itemView.setOnClickListener((v)->{
            Intent intent = new Intent(context, KegiatanActivity.class);
            intent.putExtra("idTanggal",tanggal.getId());
            intent.putExtra("tanggal",tanggal.getTanggal());
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return tanggalList.size();
    }

    public class TanggalViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
      TextView tvTanggal;
      ImageView ivHapus;
        public TanggalViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setForeground(ContextCompat.getDrawable(context,R.drawable.ripple));
            tvTanggal = itemView.findViewById(R.id.tanggal);
            ivHapus = itemView.findViewById(R.id.hapus);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

        }
    }
}
