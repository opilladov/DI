package com.example.proyecto1.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.proyecto1.R;
import com.example.proyecto1.viewmodels.LoginViewModel;

// Clase LoginActivity que extiende AppCompatActivity
public class LoginActivity extends AppCompatActivity {

    // Declaración de las vistas que se utilizarán en la actividad
    private EditText etEmail, etPassword; // Campos de texto para el email y la contraseña
    private Button btnLogin, btnGoToRegister; // Botones para iniciar sesión y para ir al registro
    private LoginViewModel loginViewModel; // ViewModel que maneja la lógica de inicio de sesión

    // Método onCreate que se llama cuando se crea la actividad
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Establece el layout de la actividad
        setContentView(R.layout.activity_login);

        // Obtiene una instancia del ViewModel
        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);

        // Inicializa las vistas del layout
        etEmail = findViewById(R.id.etLoginEmail);
        etPassword = findViewById(R.id.etLoginPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnGoToRegister = findViewById(R.id.btnGoToRegister);

        // Configura el botón de login
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtiene el texto ingresado en los campos de email y contraseña
                String email = etEmail.getText().toString().trim();
                String password = etPassword.getText().toString().trim();

                // Verifica que los campos no estén vacíos
                if (email.isEmpty() || password.isEmpty()) {
                    // Muestra un mensaje si falta completar algún campo
                    Toast.makeText(LoginActivity.this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
                    return; // Detiene la ejecución si algún campo está vacío
                }

                // Llama al método login del ViewModel para intentar iniciar sesión
                loginViewModel.login(email, password).observe(LoginActivity.this, isSuccessful -> {
                    // Observa si el login fue exitoso o no
                    if (isSuccessful) {
                        // Si el inicio de sesión es exitoso, muestra un mensaje y abre la actividad principal
                        Toast.makeText(LoginActivity.this, "Inicio de sesión exitoso", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(LoginActivity.this, MainActivity.class)); // Redirige a MainActivity
                        finish(); // Finaliza la actividad de login para que no vuelva atrás
                    } else {
                        // Si hubo un error en el inicio de sesión, muestra un mensaje de error
                        Toast.makeText(LoginActivity.this, "Error en el inicio de sesión", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        // Configura el botón de registro, que redirige a la actividad de registro
        btnGoToRegister.setOnClickListener(v -> {
            // Abre la actividad de registro cuando se hace clic en el botón correspondiente
            startActivity(new Intent(LoginActivity.this, RegistroActivity.class));
        });
    }
}