package com.lifekey.dailycheckin.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lifekey.dailycheckin.R;
import com.lifekey.dailycheckin.KegiatanActivity;
import com.lifekey.dailycheckin.model.Tanggal;

import java.util.ArrayList;
import java.util.List;

public class TanggalAdapter extends RecyclerView.Adapter<TanggalAdapter.TanggalViewHolder> {
    List<Tanggal> tanggalList = new ArrayList<>();
    Context context;

    public TanggalAdapter(List<Tanggal> tanggalList, Context context) {
        this.tanggalList = tanggalList;
        this.context = context;
    }

    @NonNull
    @Override
    public TanggalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_kegiatan,parent,false);
        return new TanggalViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TanggalViewHolder holder, int position) {
        Tanggal tanggal = tanggalList.get(holder.getBindingAdapterPosition());
        holder.tanggal.setText(tanggal.getTanggal());
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
        TextView tanggal;
        CheckBox status;
        TextView namaKegiatan,tanggalKegiatan;
        public TanggalViewHolder(@NonNull View itemView) {
            super(itemView);
            tanggal = itemView.findViewById(R.id.tanggalKegiatan);
            status = itemView.findViewById(R.id.check);
            namaKegiatan = itemView.findViewById(R.id.namaKegiatan);

            status.setVisibility(View.GONE);
            namaKegiatan.setVisibility(View.GONE);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

        }
    }
}
