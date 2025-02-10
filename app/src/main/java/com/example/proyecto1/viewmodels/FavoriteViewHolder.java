package com.example.proyecto1.viewmodels;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyecto1.R;

public class FavoriteViewHolder extends RecyclerView.ViewHolder {

    public TextView tvTitulo, tvDescripcion;
    public ImageView ivImagen, ivEliminar;

    public FavoriteViewHolder(@NonNull View itemView) {
        super(itemView);

        tvTitulo = itemView.findViewById(R.id.tvTitulo);
        tvDescripcion = itemView.findViewById(R.id.tvDescripcion);
        ivImagen = itemView.findViewById(R.id.ivImagen);
        ivEliminar = itemView.findViewById(R.id.ivEliminar);
    }
}