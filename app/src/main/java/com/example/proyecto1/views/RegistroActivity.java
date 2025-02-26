package com.example.proyecto1.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.proyecto1.R;
import com.example.proyecto1.viewmodels.RegistroViewModel;

public class RegistroActivity extends AppCompatActivity {

    // Declaración de variables para los elementos de la interfaz
    private EditText etFullName, etEmail, etPassword, etConfirmPassword, etPhone, etAddress;
    private Button btnRegister;
    private ProgressBar progressBar;

    // Variable para el ViewModel encargado de la lógica de registro
    private RegistroViewModel registroViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro); // Establece el layout para la actividad

        // Inicialización del ViewModel para manejar el registro del usuario
        registroViewModel = new ViewModelProvider(this).get(RegistroViewModel.class);

        // Referencias a los campos de texto y otros elementos del layout
        etFullName = findViewById(R.id.etRegisterFullName);
        etEmail = findViewById(R.id.etRegisterEmail);
        etPassword = findViewById(R.id.etRegisterPassword);
        etConfirmPassword = findViewById(R.id.etRegisterConfirmPassword);
        etPhone = findViewById(R.id.etRegisterPhone);
        etAddress = findViewById(R.id.etRegisterAddress);
        btnRegister = findViewById(R.id.btnRegister);
        progressBar = findViewById(R.id.progressBar);

        // Establecemos un OnClickListener para el botón de registro
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtenemos los datos ingresados por el usuario en los campos de texto
                String fullName = etFullName.getText().toString().trim();
                String email = etEmail.getText().toString().trim();
                String password = etPassword.getText().toString().trim();
                String confirmPassword = etConfirmPassword.getText().toString().trim();
                String phone = etPhone.getText().toString().trim();
                String address = etAddress.getText().toString().trim();

                // Llamamos al método de registro en el ViewModel, pasando los datos del usuario
                registroViewModel.registerUser(fullName, phone, address, email, password, confirmPassword)
                        .observe(RegistroActivity.this, success -> {
                            // Si el registro es exitoso, mostramos un mensaje y redirigimos al Login
                            if (success) {
                                Toast.makeText(RegistroActivity.this, "Registro exitoso", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(RegistroActivity.this, LoginActivity.class));
                                finish(); // Finalizamos la actividad actual
                            }
                        });
            }
        });

        // Observamos el mensaje de error del ViewModel y lo mostramos si está presente
        registroViewModel.getErrorMessage().observe(this, error -> {
            if (error != null) {
                // Mostramos un mensaje Toast con el error
                Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
            }
        });

        // Observamos el estado de carga en el ViewModel
        registroViewModel.getIsLoading().observe(this, isLoading -> {
            // Si está cargando, mostramos la barra de progreso y deshabilitamos el botón de registro
            progressBar.setVisibility(isLoading ? View.VISIBLE : View.GONE);
            btnRegister.setEnabled(!isLoading); // Deshabilita el botón mientras se está registrando
        });
    }
}