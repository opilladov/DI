package com.example.proyecto1.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.proyecto1.R;
import com.example.proyecto1.models.Futbolista;
import com.example.proyecto1.viewmodels.FavoriteViewHolder;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;
import java.util.List;

// Definición de la clase FavoritesAdapter que extiende RecyclerView.Adapter y usa FavoriteViewHolder
public class FavoritesAdapter extends RecyclerView.Adapter<FavoriteViewHolder> {

    // Lista que contiene los futbolistas favoritos
    private List<Futbolista> favoritosList;

    // Contexto de la aplicación necesario para inflar vistas y cargar imágenes
    private Context context;

    // Constructor de la clase, recibe la lista de futbolistas y el contexto
    public FavoritesAdapter(List<Futbolista> favoritosList, Context context) {
        this.favoritosList = favoritosList; // Asigna la lista recibida al atributo de la clase
        this.context = context; // Asigna el contexto recibido al atributo de la clase
    }

    // Método que crea nuevas vistas cuando el RecyclerView las necesita
    @NonNull
    @Override
    public FavoriteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Infla el diseño XML de cada elemento en la lista
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_favorite, parent, false);
        // Retorna un nuevo ViewHolder con la vista inflada
        return new FavoriteViewHolder(view);
    }

    // Método que asocia los datos con la vista en una posición específica
    @Override
    public void onBindViewHolder(@NonNull FavoriteViewHolder holder, int position) {
        // Obtiene el objeto Futbolista correspondiente a la posición actual
        Futbolista futbolista = favoritosList.get(position);

        // Asigna el título del futbolista al TextView del ViewHolder
        holder.tvTitulo.setText(futbolista.getTitulo());

        // Asigna la descripción del futbolista al TextView del ViewHolder
        holder.tvDescripcion.setText(futbolista.getDescripcion());

        // Usa Picasso para cargar la imagen desde la URL y mostrarla en el ImageView
        Picasso.with(context).load(futbolista.getUrl()).into(holder.ivImagen);

        // Configura el botón de eliminar para que llame al método eliminarFavorito() cuando se haga clic
        holder.ivEliminar.setOnClickListener(v -> eliminarFavorito(futbolista.getTitulo(), position));
    }

    // Método privado para eliminar un favorito de la base de datos y actualizar la lista
    private void eliminarFavorito(String titulo, int position) {
        // Obtiene el ID del usuario autenticado en Firebase
        String currentUserId = FirebaseAuth.getInstance().getCurrentUser().getUid();

        // Referencia a la base de datos de Firebase en la ubicación de los favoritos del usuario actual
        DatabaseReference userFavoritesRef = FirebaseDatabase.getInstance()
                .getReference("users").child(currentUserId).child("favoritos");

        // Elimina el favorito de la base de datos usando su título como clave
        userFavoritesRef.child(titulo).removeValue().addOnCompleteListener(task -> {
            if (task.isSuccessful()) { // Si la eliminación fue exitosa
                favoritosList.remove(position); // Elimina el elemento de la lista local
                notifyItemRemoved(position); // Notifica al adaptador para actualizar la vista
            }
        });
    }

    // Método que devuelve la cantidad de elementos en la lista
    @Override
    public int getItemCount() {
        return favoritosList.size(); // Retorna el tamaño de la lista de favoritos
    }
}