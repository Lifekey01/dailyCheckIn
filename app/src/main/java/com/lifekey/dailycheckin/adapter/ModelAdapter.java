package com.lifekey.dailycheckin.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.lifekey.dailycheckin.R;
import com.lifekey.dailycheckin.MainActivity;
import com.lifekey.dailycheckin.helper.Database;
import com.lifekey.dailycheckin.model.Model;

import java.util.ArrayList;
import java.util.List;

public class ModelAdapter extends RecyclerView.Adapter<ModelAdapter.modelViewHolder> {
    List<Model> modelList = new ArrayList<>();
    Context context;
    public ModelAdapter(List<Model> modelList, Context context) {
        this.modelList = modelList;
        this.context = context;
    }

    @NonNull
    @Override
    public modelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_kegiatan,parent,false);
        return new modelViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull modelViewHolder holder, int position) {
        Model model = modelList.get(holder.getBindingAdapterPosition());
        Database db = new Database(context);
        if (model.getStatus().equals("true")){
            holder.status.setChecked(true);
        }else {
            holder.status.setChecked(false);
        }
        holder.status.setOnCheckedChangeListener((v,l) -> {
             db.updateKegiatan(model.getId(),l);
        });
        holder.namaKegiatan.setText(model.getNamaKegiatan());
        holder.tanggalKegiatan.setText(model.getTanggalKegiatan());
    }

    @Override
    public int getItemCount() {
        return modelList.size();
    }

    public class modelViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        CheckBox status;
        TextView namaKegiatan,tanggalKegiatan;
        public modelViewHolder(@NonNull View itemView) {
            super(itemView);
            status = itemView.findViewById(R.id.check);
            namaKegiatan = itemView.findViewById(R.id.namaKegiatan);
            tanggalKegiatan = itemView.findViewById(R.id.tanggalKegiatan);
        }

        @Override
        public void onClick(View v) {

        }
    }
}
