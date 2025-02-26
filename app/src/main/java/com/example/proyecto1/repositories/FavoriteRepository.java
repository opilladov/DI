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

// Definición de la clase FavoriteRepository, encargada de gestionar los favoritos del usuario en Firebase
public class FavoriteRepository {

    // Referencia a la base de datos donde se almacenan los favoritos del usuario
    private DatabaseReference userFavoritesRef;

    // ID del usuario actual autenticado en Firebase
    private String currentUserId;

    // Referencia a la base de datos donde se almacenan los futbolistas
    private DatabaseReference futbolistaRef;

    // Constructor de la clase, inicializa las referencias a la base de datos
    public FavoriteRepository() {
        // Obtiene el ID del usuario actualmente autenticado en Firebase
        currentUserId = FirebaseAuth.getInstance().getCurrentUser().getUid();

        // Apunta a la referencia en Firebase donde se almacenan los favoritos del usuario
        userFavoritesRef = FirebaseDatabase.getInstance()
                .getReference("users").child(currentUserId).child("favoritos");

        // Apunta a la referencia en Firebase donde están almacenados los futbolistas
        futbolistaRef = FirebaseDatabase.getInstance().getReference("futbolista");
    }

    // Método para cargar la lista de favoritos del usuario desde Firebase
    public void cargarFavoritos(final OnFavoritosCargadosListener listener) {
        // Agrega un listener para escuchar cambios en la lista de favoritos del usuario
        userFavoritesRef.addValueEventListener(new ValueEventListener() {

            // Se ejecuta cuando hay un cambio en los datos de favoritos
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // Lista para almacenar los identificadores de los futbolistas favoritos
                List<String> favoritosList = new ArrayList<>();

                // Itera sobre todos los favoritos almacenados en la base de datos
                for (DataSnapshot favSnapshot : snapshot.getChildren()) {
                    // Obtiene el identificador del futbolista y lo agrega a la lista
                    String futbolista = favSnapshot.getValue(String.class);
                    favoritosList.add(futbolista);
                }

                // Agrega un nuevo listener para obtener los datos completos de los futbolistas favoritos
                futbolistaRef.addValueEventListener(new ValueEventListener() {

                    // Se ejecuta cuando hay un cambio en la base de datos de futbolistas
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshotF) {
                        // Lista para almacenar los objetos Futbolista correspondientes a los favoritos
                        List<Futbolista> futbolistasList = new ArrayList<>();

                        // Itera sobre la lista de identificadores de futbolistas favoritos
                        for (String futb : favoritosList) {
                            // Obtiene el objeto Futbolista correspondiente y lo agrega a la lista
                            futbolistasList.add(snapshotF.child(futb).getValue(Futbolista.class));
                        }

                        // Llama al método onFavoritosCargados del listener con la lista de futbolistas favoritos
                        listener.onFavoritosCargados(futbolistasList);
                    }

                    // Se ejecuta si hay un error al obtener los datos de futbolistas
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        // Llama al método onErrorCargandoFavoritos del listener con el mensaje de error
                        listener.onErrorCargandoFavoritos(error.getMessage());
                    }
                });
            }

            // Se ejecuta si hay un error al obtener los favoritos del usuario
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Llama al método onErrorCargandoFavoritos del listener con el mensaje de error
                listener.onErrorCargandoFavoritos(error.getMessage());
            }
        });
    }

    // Interfaz para manejar los eventos cuando se cargan los favoritos
    public interface OnFavoritosCargadosListener {
        // Método llamado cuando la lista de favoritos se carga correctamente
        void onFavoritosCargados(List<Futbolista> favoritosList);

        // Método llamado cuando ocurre un error al cargar los favoritos
        void onErrorCargandoFavoritos(String error);
    }
}