package com.example.proyecto1.viewmodels;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.proyecto1.models.Futbolista;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AzarViewModel  extends ViewModel {
    private final DatabaseReference databaseReference;
    private final MutableLiveData<Futbolista> futbolistaLiveData = new MutableLiveData<>();
    private final List<Futbolista> futbolistaList = new ArrayList<>();
    private final Random random = new Random();

    public AzarViewModel() {
        databaseReference = FirebaseDatabase.getInstance().getReference("futbolista");
        cargarDatosDesdeFirebase();
    }

    private void cargarDatosDesdeFirebase() {
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                futbolistaList.clear();;
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Futbolista futbolista = snapshot.getValue(Futbolista.class);
                    if (futbolista != null) {
                        futbolistaList.add(futbolista);
                    }
                }
                seleccionarFutbolistaAleatorio();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                //aqui iria el error en caso de que no funcionara correctamente
            }
        });
    }

    public void seleccionarFutbolistaAleatorio() {
        if (!futbolistaList.isEmpty()) {
            int randomIndex = random.nextInt(futbolistaList.size());
            futbolistaLiveData.setValue(futbolistaList.get(randomIndex));
        }
    }

    public LiveData<Futbolista> getFutbolistaLiveData() {
        return futbolistaLiveData;
    }
}