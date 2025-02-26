package com.example.proyecto1.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.proyecto1.repositories.UserRepository;

// Definición de la clase LoginViewModel, que extiende ViewModel y maneja la lógica para el inicio de sesión del usuario
public class LoginViewModel extends ViewModel {

    // Instancia del repositorio de usuarios que maneja la autenticación
    private final UserRepository userRepository;

    // Constructor de la clase, que inicializa el repositorio de usuarios
    public LoginViewModel() {
        userRepository = new UserRepository(); // Se crea una nueva instancia del repositorio que maneja la autenticación
    }

    // Método público para realizar el inicio de sesión, que recibe el correo electrónico y la contraseña
    public LiveData<Boolean> login(String email, String password) {
        // Retorna el LiveData del repositorio que indica si el inicio de sesión fue exitoso o no
        return userRepository.loginUser(email, password);
    }
}