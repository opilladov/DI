package com.example.proyecto1.repositories;

import androidx.annotation.NonNull;

import com.example.proyecto1.models.Futbolista;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FavoriteRepository {

    private DatabaseReference userFavoritesRef;
    private String currentUserId;
    private DatabaseReference futbolistaRef;

    public FavoriteRepository() {
        currentUserId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        userFavoritesRef = FirebaseDatabase.getInstance().getReference("users").child(currentUserId).child("favoritos");
        futbolistaRef = FirebaseDatabase.getInstance().getReference("futbolista");
    }

    public void cargarFavoritos(final OnFavoritosCargadosListener listener) {
        userFavoritesRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<String> favoritosList = new ArrayList<>();

                for (DataSnapshot favSnapshot : snapshot.getChildren()) {
                    String futbolista = favSnapshot.getValue(String.class);
                    favoritosList.add(futbolista);
                }
                futbolistaRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshotF) {
                        List<Futbolista> futbolistasList = new ArrayList<>();

                        for (String futb : favoritosList){
                            futbolistasList.add(snapshotF.child(futb).getValue(Futbolista.class));
                        }
                        listener.onFavoritosCargados(futbolistasList);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        listener.onErrorCargandoFavoritos(error.getMessage());
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                listener.onErrorCargandoFavoritos(error.getMessage());
            }
        });
    }

    public void eliminarFavorito(String titulo, final OnFavoritoEliminadoListener listener) {
        userFavoritesRef.child(titulo).removeValue().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                listener.onFavoritoEliminado(true);
            } else {
                listener.onFavoritoEliminado(false);
            }
        });
    }

    public interface OnFavoritosCargadosListener {
        void onFavoritosCargados(List<Futbolista> favoritosList);
        void onErrorCargandoFavoritos(String error);
    }

    public interface OnFavoritoEliminadoListener {
        void onFavoritoEliminado(boolean success);
    }
}