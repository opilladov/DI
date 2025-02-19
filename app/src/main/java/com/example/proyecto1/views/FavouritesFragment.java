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

public class FavouritesFragment extends Fragment {

    private RecyclerView recyclerView;
    private FavoritesAdapter adapter;
    private ProgressBar progressBar;
    private TextView tvNoFavorites;
    private FavoriteRepository favoriteRepository;

    public FavouritesFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favourites, container, false);

        recyclerView = view.findViewById(R.id.recyclerViewFavorites);
        progressBar = view.findViewById(R.id.progressBar);
        tvNoFavorites = view.findViewById(R.id.tvNoFavorites);

        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        favoriteRepository = new FavoriteRepository();
        cargarFavoritos();

        return view;
    }

    private void cargarFavoritos() {
        progressBar.setVisibility(View.VISIBLE);

        favoriteRepository.cargarFavoritos(new FavoriteRepository.OnFavoritosCargadosListener() {
            @Override
            public void onFavoritosCargados(List<Futbolista> favoritosList) {
                progressBar.setVisibility(View.GONE);
                tvNoFavorites.setVisibility(favoritosList.isEmpty() ? View.VISIBLE : View.GONE);

                adapter = new FavoritesAdapter(favoritosList, requireContext());
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onErrorCargandoFavoritos(String error) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(requireContext(), "Error al cargar los favoritos: " + error, Toast.LENGTH_LONG).show();
            }
        });
    }
}