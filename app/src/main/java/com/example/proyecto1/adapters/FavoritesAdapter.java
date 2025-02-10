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

public class FavoritesAdapter extends RecyclerView.Adapter<FavoriteViewHolder> {

    private List<Futbolista> favoritosList;
    private Context context;

    public FavoritesAdapter(List<Futbolista> favoritosList, Context context) {
        this.favoritosList = favoritosList;
        this.context = context;
    }

    @NonNull
    @Override
    public FavoriteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_favorite, parent, false);
        return new FavoriteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteViewHolder holder, int position) {
        Futbolista futbolista = favoritosList.get(position);

        holder.tvTitulo.setText(futbolista.getTitulo());
        holder.tvDescripcion.setText(futbolista.getDescripcion());
        Picasso.with(context).load(futbolista.getUrl()).into(holder.ivImagen);

        holder.ivEliminar.setOnClickListener(v -> eliminarFavorito(futbolista.getTitulo(), position));
    }

    private void eliminarFavorito(String titulo, int position) {
        String currentUserId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        DatabaseReference userFavoritesRef = FirebaseDatabase.getInstance()
                .getReference("users").child(currentUserId).child("favoritos");

        userFavoritesRef.child(titulo).removeValue().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                favoritosList.remove(position);
                notifyItemRemoved(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return favoritosList.size();
    }
}