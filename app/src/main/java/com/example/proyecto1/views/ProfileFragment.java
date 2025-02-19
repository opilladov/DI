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

    private FirebaseAuth mAuth;
    private SharedPreferences sharedPreferences;
    private Button btnToggleTheme;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        mAuth = FirebaseAuth.getInstance();
        Button btnChangePassword = view.findViewById(R.id.btnChangePassword);
        btnToggleTheme = view.findViewById(R.id.btnToggleTheme);

        sharedPreferences = requireActivity().getSharedPreferences("settings", getContext().MODE_PRIVATE);

        // Configurar el botón de cambio de tema
        updateThemeButtonText();
        btnToggleTheme.setOnClickListener(v -> toggleTheme());

        btnChangePassword.setOnClickListener(v -> showChangePasswordDialog());

        return view;
    }

    private void showChangePasswordDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Cambiar Contraseña");

        final EditText input = new EditText(getContext());
        input.setHint("Ingresa tu nueva contraseña");
        builder.setView(input);

        builder.setPositiveButton("Cambiar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String newPassword = input.getText().toString().trim();
                if (newPassword.length() < 6) {
                    Toast.makeText(getContext(), "La contraseña debe tener al menos 6 caracteres", Toast.LENGTH_SHORT).show();
                    return;
                }
                changeUserPassword(newPassword);
            }
        });

        builder.setNegativeButton("Cancelar", (dialog, which) -> dialog.cancel());

        builder.show();
    }

    private void changeUserPassword(String newPassword) {
        if (mAuth.getCurrentUser() != null) {
            mAuth.getCurrentUser().updatePassword(newPassword)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            Toast.makeText(getContext(), "Contraseña cambiada exitosamente", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getContext(), "Error al cambiar la contraseña", Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }

    private void toggleTheme() {
        boolean isDarkMode = sharedPreferences.getBoolean("dark_mode", false);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        if (isDarkMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
            editor.putBoolean("dark_mode", false);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            editor.putBoolean("dark_mode", true);
        }

        editor.apply();
        updateThemeButtonText();
    }

    private void updateThemeButtonText() {
        boolean isDarkMode = sharedPreferences.getBoolean("dark_mode", false);
        btnToggleTheme.setText(isDarkMode ? "Modo Claro" : "Modo Oscuro");
    }
}