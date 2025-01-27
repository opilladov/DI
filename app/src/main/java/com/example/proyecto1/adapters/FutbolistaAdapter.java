package com.example.proyecto1.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyecto1.R;
import com.example.proyecto1.models.Futbolista;
import com.squareup.picasso.Picasso;

import java.util.List;

public class FutbolistaAdapter extends RecyclerView.Adapter<FutbolistaAdapter.ViewHolder> {

    private List<Futbolista> futbolistaList;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(Futbolista futbolista);
    }

    public FutbolistaAdapter(List<Futbolista> futbolistaList, OnItemClickListener listener) {
        this.futbolistaList = futbolistaList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_futbolista, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Futbolista futbolista = futbolistaList.get(position);
        holder.bind(futbolista, listener);
    }

    @Override
    public int getItemCount() {
        return futbolistaList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvTitle;
        private ImageView ivImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            ivImage = itemView.findViewById(R.id.ivImage);
        }

        public void bind(Futbolista futbolista, OnItemClickListener listener) {
            tvTitle.setText(futbolista.getTitulo());
            Picasso.with(itemView.getContext())
                    .load(futbolista.getUrl())
                    .into(ivImage);
            itemView.setOnClickListener(v -> listener.onItemClick(futbolista));
        }
    }

    public void updateData(List<Futbolista> newFutbolistaList) {
        this.futbolistaList = newFutbolistaList;
        notifyDataSetChanged();
    }
}