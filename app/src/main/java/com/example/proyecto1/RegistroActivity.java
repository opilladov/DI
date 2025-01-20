package com.example.proyecto1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegistroActivity extends AppCompatActivity {

    private EditText etFullName, etEmail, etPassword, etConfirmPassword, etPhone, etAddress;
    private Button btnRegister;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        etFullName = findViewById(R.id.etRegisterFullName);
        etEmail = findViewById(R.id.etRegisterEmail);
        etPassword = findViewById(R.id.etRegisterPassword);
        etConfirmPassword = findViewById(R.id.etRegisterConfirmPassword);
        etPhone = findViewById(R.id.etRegisterPhone);
        etAddress = findViewById(R.id.etRegisterAddress);
        btnRegister = findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fullName = etFullName.getText().toString().trim();
                String email = etEmail.getText().toString().trim();
                String password = etPassword.getText().toString().trim();
                String confirmPassword = etConfirmPassword.getText().toString().trim();
                String phone = etPhone.getText().toString().trim();
                String address = etAddress.getText().toString().trim();

                if (fullName.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() ||
                        phone.isEmpty() || address.isEmpty()) {
                    Toast.makeText(RegistroActivity.this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!password.equals(confirmPassword)) {
                    Toast.makeText(RegistroActivity.this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
                    return;
                }

                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(RegistroActivity.this, task -> {
                            if (task.isSuccessful()) {
                                FirebaseUser user = mAuth.getCurrentUser();

                                User userInfo = new User(fullName, phone, address);

                                if (user != null) {
                                    mDatabase.child("users").child(user.getUid()).setValue(userInfo)
                                            .addOnCompleteListener(RegistroActivity.this, task1 -> {
                                                if (task1.isSuccessful()) {
                                                    Toast.makeText(RegistroActivity.this, "Registro exitoso", Toast.LENGTH_SHORT).show();
                                                    startActivity(new Intent(RegistroActivity.this, MainActivity.class));
                                                    finish();
                                                } else {
                                                    Toast.makeText(RegistroActivity.this, "Error al guardar los datos en la base de datos", Toast.LENGTH_SHORT).show();
                                                }
                                            });
                                }
                            } else {
                                Toast.makeText(RegistroActivity.this, "Error en el registro: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
    }

    public static class User {
        public String fullName;
        public String phone;
        public String address;

        public User() {

        }

        public User(String fullName, String phone, String address) {
            this.fullName = fullName;
            this.phone = phone;
            this.address = address;
        }
    }
}