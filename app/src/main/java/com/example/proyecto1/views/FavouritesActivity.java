package com.example.proyecto1.views;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyecto1.R;
import com.example.proyecto1.adapters.FavoritesAdapter;
import com.example.proyecto1.models.Futbolista;
import com.example.proyecto1.repositories.FavoriteRepository;

import java.util.List;

public class FavouritesActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private FavoritesAdapter adapter;
    private ProgressBar progressBar;
    private TextView tvNoFavorites;
    private Button btnBack;
    private FavoriteRepository favoriteRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourites);

        recyclerView = findViewById(R.id.recyclerViewFavorites);
        progressBar = findViewById(R.id.progressBar);
        tvNoFavorites = findViewById(R.id.tvNoFavorites);
        btnBack = findViewById(R.id.btnBack);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        favoriteRepository = new FavoriteRepository();
        cargarFavoritos();

        btnBack.setOnClickListener(v -> finish());
    }

    private void cargarFavoritos() {
        progressBar.setVisibility(View.VISIBLE);

        favoriteRepository.cargarFavoritos(new FavoriteRepository.OnFavoritosCargadosListener() {
            @Override
            public void onFavoritosCargados(List<Futbolista> favoritosList) {
                progressBar.setVisibility(View.GONE);
                tvNoFavorites.setVisibility(favoritosList.isEmpty() ? View.VISIBLE : View.GONE);

                adapter = new FavoritesAdapter(favoritosList, FavouritesActivity.this);
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onErrorCargandoFavoritos(String error) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(FavouritesActivity.this, "Error al cargar los favoritos: " + error, Toast.LENGTH_LONG).show();
            }
        });
    }
}