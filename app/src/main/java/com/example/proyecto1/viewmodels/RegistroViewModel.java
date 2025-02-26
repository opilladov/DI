package com.example.proyecto1.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.proyecto1.models.User;
import com.example.proyecto1.repositories.UserRepository;

// Definición de la clase RegistroViewModel, que extiende ViewModel y maneja la lógica para el registro de un usuario
public class RegistroViewModel extends ViewModel {

    // Instancia del repositorio de usuarios que maneja la autenticación y registro
    private final UserRepository userRepository;

    // MutableLiveData para almacenar mensajes de error que se mostrarán en la UI
    private final MutableLiveData<String> errorMessage = new MutableLiveData<>();

    // MutableLiveData para controlar el estado de carga (loading) durante el registro
    private final MutableLiveData<Boolean> isLoading = new MutableLiveData<>();

    // Constructor de la clase, inicializa el repositorio y establece el estado de carga inicial en false
    public RegistroViewModel() {
        userRepository = new UserRepository(); // Inicializa el repositorio que maneja la autenticación y registro
        isLoading.setValue(false); // Establece que no está cargando al inicio
    }

    // Método público para obtener el mensaje de error como un LiveData que la UI puede observar
    public LiveData<String> getErrorMessage() {
        return errorMessage;
    }

    // Método público para obtener el estado de carga (loading) como un LiveData que la UI puede observar
    public LiveData<Boolean> getIsLoading() {
        return isLoading;
    }

    // Método público para registrar un nuevo usuario, recibe los datos del usuario como parámetros
    public LiveData<Boolean> registerUser(String fullName, String phone, String address, String email, String password, String confirmPassword) {
        // MutableLiveData que devuelve el resultado del registro
        MutableLiveData<Boolean> registrationResult = new MutableLiveData<>();

        // Verifica si algún campo está vacío
        if (fullName.isEmpty() || phone.isEmpty() || address.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            // Si algún campo está vacío, muestra un mensaje de error y devuelve false
            errorMessage.setValue("Por favor, complete todos los campos");
            registrationResult.setValue(false);
        } else if (!password.equals(confirmPassword)) {
            // Si las contraseñas no coinciden, muestra un mensaje de error y devuelve false
            errorMessage.setValue("Las contraseñas no coinciden");
            registrationResult.setValue(false);
        } else {
            // Si todo está correcto, establece que está cargando y crea un objeto User con la información del nuevo usuario
            isLoading.setValue(true);
            User userInfo = new User(fullName, phone, address);

            // Llama al repositorio para registrar al usuario, pasando el correo, la contraseña y los datos del usuario
            userRepository.registerUser(email, password, userInfo).observeForever(success -> {
                // Al finalizar el registro, establece que ya no está cargando y actualiza el resultado del registro
                isLoading.setValue(false);
                registrationResult.setValue(success);

                // Si el registro no fue exitoso, muestra un mensaje de error
                if (!success) {
                    errorMessage.setValue("Error en el registro, inténtelo de nuevo.");
                }
            });
        }

        // Retorna el resultado del registro (LiveData)
        return registrationResult;
    }
}