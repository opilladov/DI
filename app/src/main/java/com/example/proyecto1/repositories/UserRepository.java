package com.example.proyecto1.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.proyecto1.models.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

// Definición de la clase UserRepository, encargada de manejar la autenticación de usuarios y almacenamiento en Firebase
public class UserRepository {

    // Instancia de FirebaseAuth para gestionar la autenticación de usuarios
    private final FirebaseAuth mAuth;

    // Instancia de DatabaseReference para interactuar con la base de datos de Firebase
    private final DatabaseReference mDatabase;

    // Constructor de la clase, inicializa las instancias de FirebaseAuth y FirebaseDatabase
    public UserRepository() {
        mAuth = FirebaseAuth.getInstance(); // Obtiene la instancia de FirebaseAuth para la autenticación
        mDatabase = FirebaseDatabase.getInstance().getReference(); // Obtiene la referencia raíz de FirebaseDatabase
    }

    // Método para iniciar sesión de un usuario con correo electrónico y contraseña
    public LiveData<Boolean> loginUser(String email, String password) {
        // Crea un MutableLiveData para almacenar el resultado de la operación de inicio de sesión
        MutableLiveData<Boolean> loginResult = new MutableLiveData<>();

        // Intenta iniciar sesión con el correo electrónico y la contraseña proporcionados
        mAuth.signInWithEmailAndPassword(email, password)
                // Después de que la tarea de inicio de sesión termine, se actualiza el resultado
                .addOnCompleteListener(task -> loginResult.setValue(task.isSuccessful()));

        // Retorna el LiveData con el resultado de la operación de inicio de sesión
        return loginResult;
    }

    // Método para registrar un nuevo usuario con correo electrónico, contraseña e información adicional del usuario
    public LiveData<Boolean> registerUser(String email, String password, User userInfo) {
        // Crea un MutableLiveData para almacenar el resultado de la operación de registro
        MutableLiveData<Boolean> registrationResult = new MutableLiveData<>();

        // Intenta crear un nuevo usuario con el correo electrónico y la contraseña proporcionados
        mAuth.createUserWithEmailAndPassword(email, password)
                // Después de que la tarea de registro termine, se maneja el resultado
                .addOnCompleteListener(task -> {
                    // Si el registro fue exitoso
                    if (task.isSuccessful()) {
                        FirebaseUser user = mAuth.getCurrentUser(); // Obtiene el usuario recién autenticado

                        // Verifica que el usuario no sea nulo
                        if (user != null) {
                            // Guarda la información adicional del usuario en la base de datos bajo la clave "users" y el UID del usuario
                            mDatabase.child("users").child(user.getUid()).setValue(userInfo)
                                    // Después de que la tarea de guardar datos del usuario termine, se actualiza el resultado
                                    .addOnCompleteListener(task1 -> registrationResult.setValue(task1.isSuccessful()));
                        } else {
                            // Si el usuario es nulo, la operación de registro no fue exitosa
                            registrationResult.setValue(false);
                        }
                    } else {
                        // Si el registro falló, se indica que no fue exitoso
                        registrationResult.setValue(false);
                    }
                });

        // Retorna el LiveData con el resultado de la operación de registro
        return registrationResult;
    }
}