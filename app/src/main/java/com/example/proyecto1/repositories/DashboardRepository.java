package com.example.proyecto1.repositories;

import androidx.lifecycle.MutableLiveData;

import com.example.proyecto1.models.Futbolista;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

// Definición de la clase DashboardRepository, encargada de manejar la obtención de datos desde Firebase
public class DashboardRepository {

    // Referencia a la base de datos en Firebase
    private final DatabaseReference dataRef;

    // Constructor de la clase, inicializa la referencia a la base de datos
    public DashboardRepository() {
        FirebaseDatabase database = FirebaseDatabase.getInstance(); // Obtiene la instancia de Firebase Database
        dataRef = database.getReference("futbolista"); // Apunta a la referencia "futbolista" en la base de datos
    }

    // Método para obtener la lista de futbolistas y actualizar el MutableLiveData
    public void fetchFutbolistas(MutableLiveData<List<Futbolista>> futbolistaLiveData) {
        // Agrega un listener para escuchar cambios en la base de datos
        dataRef.addValueEventListener(new ValueEventListener() {

            // Se ejecuta cuando los datos cambian en la base de datos
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Crea una lista para almacenar los futbolistas obtenidos
                List<Futbolista> futbolistaList = new ArrayList<>();

                // Itera sobre todos los hijos de la referencia "futbolista"
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    // Convierte cada snapshot en un objeto Futbolista
                    Futbolista futbolista = snapshot.getValue(Futbolista.class);

                    // Asigna el ID del futbolista usando la clave del snapshot en la base de datos
                    futbolista.setFutbolistaId(snapshot.getKey());

                    // Agrega el futbolista a la lista
                    futbolistaList.add(futbolista);
                }

                // Publica la lista de futbolistas en el MutableLiveData para actualizar la UI
                futbolistaLiveData.postValue(futbolistaList);
            }

            // Se ejecuta si hay un error al obtener los datos de Firebase
            @Override
            public void onCancelled(DatabaseError databaseError) {
                // En caso de error, se envía un valor nulo al MutableLiveData
                futbolistaLiveData.postValue(null);
            }
        });
    }
}