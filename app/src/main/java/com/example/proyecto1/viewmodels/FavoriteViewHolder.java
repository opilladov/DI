package com.example.proyecto1.viewmodels;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyecto1.R;

// Definición de la clase FavoriteViewHolder, que extiende ViewHolder para mostrar los elementos de la lista de favoritos
public class FavoriteViewHolder extends RecyclerView.ViewHolder {

    // Referencias a los elementos de la interfaz de usuario que se mostrarán en cada ítem de la lista
    public TextView tvTitulo, tvDescripcion;  // TextViews para mostrar el título y la descripción
    public ImageView ivImagen, ivEliminar;   // ImageViews para mostrar la imagen del futbolista y el icono para eliminar

    // Constructor que recibe una vista de ítem (itemView) y asocia las vistas de la interfaz a las variables
    public FavoriteViewHolder(@NonNull View itemView) {
        super(itemView); // Llama al constructor de la clase ViewHolder de RecyclerView

        // Asocia las vistas de la interfaz con las referencias correspondientes
        tvTitulo = itemView.findViewById(R.id.tvTitulo);  // Asocia el TextView para el título
        tvDescripcion = itemView.findViewById(R.id.tvDescripcion);  // Asocia el TextView para la descripción
        ivImagen = itemView.findViewById(R.id.ivImagen);  // Asocia el ImageView para la imagen del futbolista
        ivEliminar = itemView.findViewById(R.id.ivEliminar);  // Asocia el ImageView para el botón de eliminar
    }
}