package com.example.proyecto1.views;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyecto1.R;
import com.example.proyecto1.adapters.FutbolistaAdapter;
import com.example.proyecto1.models.Futbolista;
import com.example.proyecto1.viewmodels.DashboardViewModel;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class DashboardFragment extends Fragment {

    private RecyclerView recyclerView;
    private FutbolistaAdapter adapter;
    private DashboardViewModel viewModel;
    private SharedPreferences sharedPreferences;
    private boolean isDarkMode;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);

        sharedPreferences = requireActivity().getSharedPreferences("AppPrefs", requireContext().MODE_PRIVATE);
        isDarkMode = sharedPreferences.getBoolean("isDarkMode", false);
        setAppTheme(isDarkMode);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter = new FutbolistaAdapter(new ArrayList<>(), this::openDetailActivity);
        recyclerView.setAdapter(adapter);

        viewModel = new ViewModelProvider(this).get(DashboardViewModel.class);
        observeViewModel();

        return view;
    }

    private void observeViewModel() {
        viewModel.getFutbolistas().observe(getViewLifecycleOwner(), futbolistaList -> {
            if (futbolistaList != null) {
                adapter.updateData(futbolistaList);
            } else {
                Toast.makeText(getContext(), "Error al cargar datos", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void openDetailActivity(Futbolista futbolista) {
        Intent intent = new Intent(requireContext(), DetailFragment.class);
        intent.putExtra("title", futbolista.getTitulo());
        intent.putExtra("description", futbolista.getDescripcion());
        intent.putExtra("imageUrl", futbolista.getUrl());
        intent.putExtra("futbolistaId", futbolista.getFutbolistaId());
        startActivity(intent);
    }

    private void openFavoritesActivity() {
        Intent intent = new Intent(requireContext(), FavouritesFragment.class);
        startActivity(intent);
    }

    private void logout() {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(requireContext(), LoginActivity.class));
        requireActivity().finish();
    }

    private void toggleTheme() {
        isDarkMode = !isDarkMode;
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("isDarkMode", isDarkMode);
        editor.apply();
        setAppTheme(isDarkMode);
        requireActivity().recreate();
    }

    private void setAppTheme(boolean darkMode) {
        if (darkMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }
}