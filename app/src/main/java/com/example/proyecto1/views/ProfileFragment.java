package com.example.proyecto1.views;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;

import com.example.proyecto1.R;
import com.google.firebase.auth.FirebaseAuth;

public class ProfileFragment extends Fragment {

    // Declaración de variables para el manejo de la autenticación y preferencias
    private FirebaseAuth mAuth;
    private SharedPreferences sharedPreferences;
    private Button btnToggleTheme;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Infla el layout de este fragmento y lo devuelve como una vista
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        // Inicializa la instancia de FirebaseAuth para manejar la autenticación
        mAuth = FirebaseAuth.getInstance();

        // Inicializa los botones a través de la vista inflada
        Button btnChangePassword = view.findViewById(R.id.btnChangePassword);
        btnToggleTheme = view.findViewById(R.id.btnToggleTheme);

        // Inicializa SharedPreferences para gestionar la configuración de tema de la aplicación
        sharedPreferences = requireActivity().getSharedPreferences("settings", getContext().MODE_PRIVATE);

        // Configura el texto del botón de cambio de tema según la preferencia almacenada
        updateThemeButtonText();

        // Configura el listener para el botón de cambio de tema
        btnToggleTheme.setOnClickListener(v -> toggleTheme());

        // Configura el listener para el botón de cambio de contraseña
        btnChangePassword.setOnClickListener(v -> showChangePasswordDialog());

        // Retorna la vista inflada con los elementos de interfaz
        return view;
    }

    // Muestra un diálogo para cambiar la contraseña del usuario
    private void showChangePasswordDialog() {
        // Crea un builder para el diálogo
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Cambiar Contraseña");

        // Crea un campo de texto para que el usuario ingrese la nueva contraseña
        final EditText input = new EditText(getContext());
        input.setHint("Ingresa tu nueva contraseña");
        builder.setView(input);  // Establece el campo de texto en el diálogo

        // Configura el botón de cambio de contraseña
        builder.setPositiveButton("Cambiar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Obtiene la contraseña ingresada
                String newPassword = input.getText().toString().trim();

                // Valida que la nueva contraseña tenga al menos 6 caracteres
                if (newPassword.length() < 6) {
                    Toast.makeText(getContext(), "La contraseña debe tener al menos 6 caracteres", Toast.LENGTH_SHORT).show();
                    return;
                }
                // Si la contraseña es válida, llama al método para cambiarla
                changeUserPassword(newPassword);
            }
        });

        // Configura el botón de cancelar
        builder.setNegativeButton("Cancelar", (dialog, which) -> dialog.cancel());

        // Muestra el diálogo
        builder.show();
    }

    // Cambia la contraseña del usuario actual utilizando FirebaseAuth
    private void changeUserPassword(String newPassword) {
        // Verifica que el usuario esté autenticado
        if (mAuth.getCurrentUser() != null) {
            // Intenta actualizar la contraseña del usuario actual
            mAuth.getCurrentUser().updatePassword(newPassword)
                    .addOnCompleteListener(task -> {
                        // Verifica si la actualización fue exitosa
                        if (task.isSuccessful()) {
                            // Muestra un mensaje de éxito
                            Toast.makeText(getContext(), "Contraseña cambiada exitosamente", Toast.LENGTH_SHORT).show();
                        } else {
                            // Muestra un mensaje de error si no se pudo cambiar la contraseña
                            Toast.makeText(getContext(), "Error al cambiar la contraseña", Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }

    // Cambia entre el modo claro y el modo oscuro, según la preferencia del usuario
    private void toggleTheme() {
        // Obtiene el estado actual del tema (oscuro o claro) de las preferencias
        boolean isDarkMode = sharedPreferences.getBoolean("dark_mode", false);

        // Edita las preferencias de SharedPreferences
        SharedPreferences.Editor editor = sharedPreferences.edit();

        // Si el modo actual es oscuro, cambia a modo claro y guarda la preferencia
        if (isDarkMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            editor.putBoolean("dark_mode", false);
        } else {
            // Si el modo actual es claro, cambia a modo oscuro y guarda la preferencia
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            editor.putBoolean("dark_mode", true);
        }

        // Aplica los cambios realizados en SharedPreferences
        editor.apply();

        // Actualiza el texto del botón de cambio de tema
        updateThemeButtonText();
    }

    // Actualiza el texto del botón de cambio de tema según la preferencia del usuario
    private void updateThemeButtonText() {
        // Obtiene el estado actual del tema (oscuro o claro)
        boolean isDarkMode = sharedPreferences.getBoolean("dark_mode", false);

        // Establece el texto del botón de acuerdo con el estado del tema
        btnToggleTheme.setText(isDarkMode ? "Modo Claro" : "Modo Oscuro");
    }
}