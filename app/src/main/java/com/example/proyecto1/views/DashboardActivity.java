package com.example.proyecto1.views;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyecto1.R;
import com.example.proyecto1.adapters.FutbolistaAdapter;
import com.example.proyecto1.models.Futbolista;
import com.example.proyecto1.viewmodels.DashboardViewModel;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class DashboardActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private FutbolistaAdapter adapter;
    private DashboardViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new FutbolistaAdapter(new ArrayList<>(), this::openDetailActivity);
        recyclerView.setAdapter(adapter);

        viewModel = new ViewModelProvider(this).get(DashboardViewModel.class);
        observeViewModel();

        findViewById(R.id.btnLogout).setOnClickListener(v -> logout());
    }

    private void observeViewModel() {
        viewModel.getFutbolistas().observe(this, futbolistaList -> {
            if (futbolistaList != null) {
                adapter.updateData(futbolistaList);
            } else {
                Toast.makeText(this, "Error al cargar datos", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void openDetailActivity(Futbolista futbolista) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("title", futbolista.getTitulo());
        intent.putExtra("description", futbolista.getDescripcion());
        intent.putExtra("imageUrl", futbolista.getUrl());
        startActivity(intent);
    }

    private void logout() {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}