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

// Definición de la clase DashboardFragment que extiende de Fragment
public class DashboardFragment extends Fragment {

    // Declaración de las variables necesarias
    private RecyclerView recyclerView; // RecyclerView para mostrar la lista de futbolistas
    private FutbolistaAdapter adapter; // Adaptador para la lista de futbolistas
    private DashboardViewModel viewModel; // ViewModel que proporciona los datos de los futbolistas
    private SharedPreferences sharedPreferences; // SharedPreferences para almacenar el estado del modo oscuro
    private boolean isDarkMode; // Variable para verificar si el modo oscuro está activado

    // Método que se llama cuando se crea la vista del fragmento
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Infla el layout del fragmento
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);

        // Obtiene las preferencias guardadas para verificar si el modo oscuro está activado
        sharedPreferences = requireActivity().getSharedPreferences("AppPrefs", requireContext().MODE_PRIVATE);
        isDarkMode = sharedPreferences.getBoolean("isDarkMode", false); // Recupera el valor de isDarkMode
        setAppTheme(isDarkMode); // Establece el tema según el valor de isDarkMode

        // Configura el RecyclerView
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext())); // Establece el LayoutManager

        // Crea el adaptador con una lista vacía y define el click listener para abrir el detalle
        adapter = new FutbolistaAdapter(new ArrayList<>(), this::openDetailActivity);
        recyclerView.setAdapter(adapter); // Asocia el adaptador al RecyclerView

        // Inicializa el ViewModel y observa los cambios en los datos
        viewModel = new ViewModelProvider(this).get(DashboardViewModel.class);
        observeViewModel(); // Observa el LiveData de los futbolistas

        return view; // Retorna la vista inflada
    }

    // Método para observar los datos proporcionados por el ViewModel
    private void observeViewModel() {
        // Observa el LiveData de futbolistas y actualiza el adaptador cuando cambia
        viewModel.getFutbolistas().observe(getViewLifecycleOwner(), futbolistaList -> {
            if (futbolistaList != null) {
                adapter.updateData(futbolistaList); // Si los datos no son nulos, actualiza el adaptador
            } else {
                // Si los datos son nulos, muestra un mensaje de error
                Toast.makeText(getContext(), "Error al cargar datos", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Método para abrir una actividad de detalle de futbolista
    private void openDetailActivity(Futbolista futbolista) {
        // Crea una nueva intención para abrir el detalle
        Intent intent = new Intent(requireContext(), DetailFragment.class);
        // Pasa los datos del futbolista a la actividad de detalle
        intent.putExtra("title", futbolista.getTitulo());
        intent.putExtra("description", futbolista.getDescripcion());
        intent.putExtra("imageUrl", futbolista.getUrl());
        intent.putExtra("futbolistaId", futbolista.getFutbolistaId());
        startActivity(intent); // Inicia la actividad
    }

    // Método para abrir la actividad de favoritos
    private void openFavoritesActivity() {
        // Crea una nueva intención para abrir el fragmento de favoritos
        Intent intent = new Intent(requireContext(), FavouritesFragment.class);
        startActivity(intent); // Inicia la actividad
    }

    // Método para cerrar sesión
    private void logout() {
        // Cierra sesión en Firebase
        FirebaseAuth.getInstance().signOut();
        // Inicia la actividad de login
        startActivity(new Intent(requireContext(), LoginActivity.class));
        requireActivity().finish(); // Finaliza la actividad actual
    }

    // Método para cambiar el tema (de modo oscuro a claro y viceversa)
    private void toggleTheme() {
        // Cambia el valor de isDarkMode al contrario
        isDarkMode = !isDarkMode;
        // Guarda el nuevo valor de isDarkMode en las preferencias
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("isDarkMode", isDarkMode);
        editor.apply();
        // Establece el nuevo tema
        setAppTheme(isDarkMode);
        // Recrea la actividad para aplicar el nuevo tema
        requireActivity().recreate();
    }

    // Método para establecer el tema según el valor de darkMode
    private void setAppTheme(boolean darkMode) {
        if (darkMode) {
            // Si darkMode es verdadero, activa el modo oscuro
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            // Si darkMode es falso, activa el modo claro
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }
}