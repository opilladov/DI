package com.example.proyecto1.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.proyecto1.models.User;
import com.example.proyecto1.repositories.UserRepository;

public class RegistroViewModel extends ViewModel {
    private final UserRepository userRepository;

    private final MutableLiveData<String> errorMessage = new MutableLiveData<>();
    private final MutableLiveData<Boolean> isLoading = new MutableLiveData<>();

    public RegistroViewModel() {
        userRepository = new UserRepository();
        isLoading.setValue(false);
    }

    public LiveData<String> getErrorMessage() {
        return errorMessage;
    }

    public LiveData<Boolean> getIsLoading() {
        return isLoading;
    }

    public LiveData<Boolean> registerUser(String fullName, String phone, String address, String email, String password, String confirmPassword) {
        MutableLiveData<Boolean> registrationResult = new MutableLiveData<>();

        if (fullName.isEmpty() || phone.isEmpty() || address.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            errorMessage.setValue("Por favor, complete todos los campos");
            registrationResult.setValue(false);
        } else if (!password.equals(confirmPassword)) {
            errorMessage.setValue("Las contraseñas no coinciden");
            registrationResult.setValue(false);
        } else {
            isLoading.setValue(true);
            User userInfo = new User(fullName, phone, address);
            userRepository.registerUser(email, password, userInfo).observeForever(success -> {
                isLoading.setValue(false);
                registrationResult.setValue(success);

                if (!success) {
                    errorMessage.setValue("Error en el registro, inténtelo de nuevo.");
                }
            });
        }

        return registrationResult;
    }
}