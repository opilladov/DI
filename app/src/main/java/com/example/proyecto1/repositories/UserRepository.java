package com.example.proyecto1.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.proyecto1.models.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class UserRepository {
    private final FirebaseAuth mAuth;
    private final DatabaseReference mDatabase;

    public UserRepository() {
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
    }

    public LiveData<Boolean> loginUser(String email, String password) {
        MutableLiveData<Boolean> loginResult = new MutableLiveData<>();

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> loginResult.setValue(task.isSuccessful()));

        return loginResult;
    }

    public LiveData<Boolean> registerUser(String email, String password, User userInfo) {
        MutableLiveData<Boolean> registrationResult = new MutableLiveData<>();

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser user = mAuth.getCurrentUser();
                        if (user != null) {
                            mDatabase.child("users").child(user.getUid()).setValue(userInfo)
                                    .addOnCompleteListener(task1 -> registrationResult.setValue(task1.isSuccessful()));
                        } else {
                            registrationResult.setValue(false);
                        }
                    } else {
                        registrationResult.setValue(false);
                    }
                });

        return registrationResult;
    }
}