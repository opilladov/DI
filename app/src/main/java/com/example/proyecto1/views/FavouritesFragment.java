package com.example.proyecto1.views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyecto1.R;
import com.example.proyecto1.adapters.FavoritesAdapter;
import com.example.proyecto1.models.Futbolista;
import com.example.proyecto1.repositories.FavoriteRepository;

import java.util.List;

// Definición de la clase FavouritesFragment que extiende Fragment
public class FavouritesFragment extends Fragment {

    // Declaración de las vistas que se utilizarán en el fragmento
    private RecyclerView recyclerView; // RecyclerView para mostrar los favoritos
    private FavoritesAdapter adapter; // Adaptador para el RecyclerView
    private ProgressBar progressBar; // Barra de progreso para mostrar mientras se cargan los favoritos
    private TextView tvNoFavorites; // Texto que se muestra cuando no hay favoritos
    private FavoriteRepository favoriteRepository; // Repositorio para manejar los favoritos

    // Constructor vacío para el fragmento
    public FavouritesFragment() {

    }

    // Método que se llama cuando se crea la vista del fragmento
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Infla el layout del fragmento
        View view = inflater.inflate(R.layout.fragment_favourites, container, false);

        // Inicializa las vistas del layout
        recyclerView = view.findViewById(R.id.recyclerViewFavorites);
        progressBar = view.findViewById(R.id.progressBar);
        tvNoFavorites = view.findViewById(R.id.tvNoFavorites);

        // Configura el layout manager para el RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        // Inicializa el repositorio de favoritos
        favoriteRepository = new FavoriteRepository();
        // Carga los favoritos
        cargarFavoritos();

        return view; // Retorna la vista inflada
    }

    // Método para cargar los favoritos desde el repositorio
    private void cargarFavoritos() {
        // Muestra la barra de progreso mientras se cargan los favoritos
        progressBar.setVisibility(View.VISIBLE);

        // Llama al método del repositorio para cargar los favoritos
        favoriteRepository.cargarFavoritos(new FavoriteRepository.OnFavoritosCargadosListener() {
            // Si los favoritos se cargan correctamente
            @Override
            public void onFavoritosCargados(List<Futbolista> favoritosList) {
                // Oculta la barra de progreso una vez que los datos se han cargado
                progressBar.setVisibility(View.GONE);
                // Si la lista de favoritos está vacía, muestra un mensaje indicando que no hay favoritos
                tvNoFavorites.setVisibility(favoritosList.isEmpty() ? View.VISIBLE : View.GONE);

                // Crea un nuevo adaptador con la lista de favoritos y lo asigna al RecyclerView
                adapter = new FavoritesAdapter(favoritosList, requireContext());
                recyclerView.setAdapter(adapter);
            }

            // Si ocurre un error al cargar los favoritos
            @Override
            public void onErrorCargandoFavoritos(String error) {
                // Oculta la barra de progreso y muestra un mensaje de error
                progressBar.setVisibility(View.GONE);
                Toast.makeText(requireContext(), "Error al cargar los favoritos: " + error, Toast.LENGTH_LONG).show();
            }
        });
    }
}