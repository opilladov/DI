package com.example.proyecto1.views;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.google.firebase.auth.FirebaseAuth;

// Clase LogoutFragment que extiende Fragment
public class LogoutFragment extends Fragment {

    // Método que se ejecuta cuando se crea el fragmento
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Desconecta al usuario de Firebase
        FirebaseAuth.getInstance().signOut();

        // Crea una nueva intención para redirigir al LoginActivity
        Intent intent = new Intent(getActivity(), LoginActivity.class);

        // Establece las flags de la intención para limpiar el stack de actividades
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

        // Inicia LoginActivity
        startActivity(intent);

        // Finaliza la actividad actual para evitar que el usuario regrese a esta
        getActivity().finish();
    }
}