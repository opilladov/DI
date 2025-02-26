package com.example.proyecto1.views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.proyecto1.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

// Definición de la clase DetailFragment que extiende de Fragment
public class DetailFragment extends Fragment {

    // Declaración de las vistas que se van a usar
    private TextView tvDetailTitle, tvDetailDescription; // Títulos y descripciones
    private ImageView ivDetailImage; // Imagen del futbolista
    private Button btnBack; // Botón para regresar
    private FloatingActionButton fabFavorite; // Botón flotante para agregar a favoritos

    // Variables para gestionar los favoritos
    private DatabaseReference userFavoritesRef; // Referencia a los favoritos del usuario en Firebase
    private String currentUserId; // ID del usuario actual
    private String futbolistaId; // ID del futbolista
    private boolean isFavorite = false; // Indicador si el futbolista es favorito o no
    private List<String> favoritosList = new ArrayList<>(); // Lista de IDs de futbolistas favoritos

    // Método que se llama cuando se crea la vista del fragmento
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Infla el layout del fragmento
        View view = inflater.inflate(R.layout.fragment_detail, container, false);

        // Inicializa las vistas del layout
        tvDetailTitle = view.findViewById(R.id.tvDetailTitle);
        tvDetailDescription = view.findViewById(R.id.tvDetailDescription);
        ivDetailImage = view.findViewById(R.id.ivDetailImage);
        fabFavorite = view.findViewById(R.id.fabFavorite);

        // Obtiene los datos del futbolista pasados a través de los argumentos del fragmento
        if (getArguments() != null) {
            String title = getArguments().getString("title");
            String description = getArguments().getString("description");
            String imageUrl = getArguments().getString("imageUrl");
            futbolistaId = getArguments().getString("futbolistaId");

            // Asigna los valores a las vistas si no son nulos
            if (title != null) tvDetailTitle.setText(title);
            if (description != null) tvDetailDescription.setText(description);
            if (imageUrl != null) Picasso.with(getContext()).load(imageUrl).into(ivDetailImage);
        }

        // Obtiene el ID del usuario actual
        currentUserId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        // Inicializa la referencia a los favoritos del usuario en Firebase
        userFavoritesRef = FirebaseDatabase.getInstance().getReference("users").child(currentUserId).child("favoritos");

        // Verifica si el futbolista actual está en la lista de favoritos
        checkIfFavorite();

        // Configura el evento de click para el botón flotante (favorito)
        fabFavorite.setOnClickListener(v -> toggleFavorite());

        return view; // Retorna la vista inflada
    }

    // Método para comprobar si el futbolista está en la lista de favoritos del usuario
    private void checkIfFavorite() {
        // Obtiene los datos de los favoritos del usuario desde Firebase
        userFavoritesRef.get().addOnCompleteListener(task -> {
            if (task.isSuccessful() && task.getResult().exists()) {
                DataSnapshot snapshot = task.getResult(); // Obtiene el resultado de la consulta
                favoritosList.clear(); // Limpia la lista de favoritos

                // Recorrer todos los elementos en los favoritos y agregar sus valores a la lista
                for (DataSnapshot favSnapshot : snapshot.getChildren()) {
                    favoritosList.add(favSnapshot.getValue(String.class));
                }

                // Verifica si el futbolista actual está en la lista de favoritos
                isFavorite = favoritosList.contains(futbolistaId);
                // Actualiza el icono del botón de favorito según si es favorito o no
                updateFavoriteButton();
            }
        });
    }

    // Método para agregar o quitar el futbolista de la lista de favoritos
    private void toggleFavorite() {
        // Si es favorito, lo elimina de la lista; si no lo es, lo agrega
        if (isFavorite) {
            favoritosList.remove(futbolistaId);
        } else {
            favoritosList.add(futbolistaId);
        }

        // Actualiza la lista de favoritos en Firebase
        userFavoritesRef.setValue(favoritosList).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                // Si la operación fue exitosa, cambia el estado de favorito y actualiza el botón
                isFavorite = !isFavorite;
                updateFavoriteButton();
            } else {
                // Si ocurre un error al actualizar, muestra un mensaje
                Toast.makeText(getContext(), "Error al actualizar favorito", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Método para actualizar el icono del botón de favorito
    private void updateFavoriteButton() {
        if (getActivity() != null) {
            getActivity().runOnUiThread(() -> {
                // Si es favorito, muestra un icono lleno; si no lo es, muestra un icono vacío
                if (isFavorite) {
                    fabFavorite.setImageResource(R.drawable.star_24); // Icono de estrella llena
                } else {
                    fabFavorite.setImageResource(R.drawable.star_border_24); // Icono de estrella vacía
                }
            });
        }
    }
}