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

// Definición de la clase FutbolistaAdapter que extiende RecyclerView.Adapter
public class FutbolistaAdapter extends RecyclerView.Adapter<FutbolistaAdapter.ViewHolder> {

    // Lista que contiene los futbolistas a mostrar en el RecyclerView
    private List<Futbolista> futbolistaList;

    // Interfaz para manejar clics en los elementos de la lista
    private OnItemClickListener listener;

    // Interfaz que define el método que se ejecutará cuando se haga clic en un ítem
    public interface OnItemClickListener {
        void onItemClick(Futbolista futbolista);
    }

    // Constructor de la clase, recibe la lista de futbolistas y el listener de clics
    public FutbolistaAdapter(List<Futbolista> futbolistaList, OnItemClickListener listener) {
        this.futbolistaList = futbolistaList; // Asigna la lista recibida al atributo de la clase
        this.listener = listener; // Asigna el listener recibido al atributo de la clase
    }

    // Método que crea nuevas vistas cuando el RecyclerView las necesita
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Infla el diseño XML de cada elemento en la lista
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_futbolista, parent, false);
        // Retorna un nuevo ViewHolder con la vista inflada
        return new ViewHolder(view);
    }

    // Método que asocia los datos con la vista en una posición específica
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Obtiene el objeto Futbolista correspondiente a la posición actual
        Futbolista futbolista = futbolistaList.get(position);
        // Llama al método bind() del ViewHolder para asignar los datos al ítem
        holder.bind(futbolista, listener);
    }

    // Método que devuelve la cantidad de elementos en la lista
    @Override
    public int getItemCount() {
        return futbolistaList.size(); // Retorna el tamaño de la lista de futbolistas
    }

    // Clase interna ViewHolder que representa la vista de un solo ítem en el RecyclerView
    static class ViewHolder extends RecyclerView.ViewHolder {

        // Componentes visuales de cada ítem
        private TextView tvTitle;
        private ImageView ivImage;

        // Constructor del ViewHolder, enlaza los elementos de la vista
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle); // Enlaza el TextView del título
            ivImage = itemView.findViewById(R.id.ivImage); // Enlaza el ImageView de la imagen
        }

        // Método que asigna los datos de un futbolista a los elementos de la vista
        public void bind(Futbolista futbolista, OnItemClickListener listener) {
            tvTitle.setText(futbolista.getTitulo()); // Establece el título en el TextView
            Picasso.with(itemView.getContext()) // Usa Picasso para cargar la imagen desde la URL
                    .load(futbolista.getUrl())
                    .into(ivImage);
            // Configura el evento de clic para llamar al listener con el futbolista seleccionado
            itemView.setOnClickListener(v -> listener.onItemClick(futbolista));
        }
    }

    // Método para actualizar los datos de la lista y refrescar la vista
    public void updateData(List<Futbolista> newFutbolistaList) {
        this.futbolistaList = newFutbolistaList; // Actualiza la lista con los nuevos datos
        notifyDataSetChanged(); // Notifica al adaptador que los datos han cambiado
    }
}