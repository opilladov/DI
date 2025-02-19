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

    private EditText etFullName, etEmail, etPassword, etConfirmPassword, etPhone, etAddress;
    private Button btnRegister;
    private ProgressBar progressBar;

    private RegistroViewModel registroViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        registroViewModel = new ViewModelProvider(this).get(RegistroViewModel.class);

        etFullName = findViewById(R.id.etRegisterFullName);
        etEmail = findViewById(R.id.etRegisterEmail);
        etPassword = findViewById(R.id.etRegisterPassword);
        etConfirmPassword = findViewById(R.id.etRegisterConfirmPassword);
        etPhone = findViewById(R.id.etRegisterPhone);
        etAddress = findViewById(R.id.etRegisterAddress);
        btnRegister = findViewById(R.id.btnRegister);
        progressBar = findViewById(R.id.progressBar);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fullName = etFullName.getText().toString().trim();
                String email = etEmail.getText().toString().trim();
                String password = etPassword.getText().toString().trim();
                String confirmPassword = etConfirmPassword.getText().toString().trim();
                String phone = etPhone.getText().toString().trim();
                String address = etAddress.getText().toString().trim();

                registroViewModel.registerUser(fullName, phone, address, email, password, confirmPassword)
                        .observe(RegistroActivity.this, success -> {
                            if (success) {
                                Toast.makeText(RegistroActivity.this, "Registro exitoso", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(RegistroActivity.this, LoginActivity.class));
                                finish();
                            }
                        });
            }
        });

        registroViewModel.getErrorMessage().observe(this, error -> {
            if (error != null) {
                Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
            }
        });

        registroViewModel.getIsLoading().observe(this, isLoading -> {
            progressBar.setVisibility(isLoading ? View.VISIBLE : View.GONE);
            btnRegister.setEnabled(!isLoading);
        });
    }
}