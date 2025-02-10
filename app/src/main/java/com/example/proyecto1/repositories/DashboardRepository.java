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

public class DashboardRepository {

    private final DatabaseReference dataRef;

    public DashboardRepository() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        dataRef = database.getReference("futbolista");
    }

    public void fetchFutbolistas(MutableLiveData<List<Futbolista>> futbolistaLiveData) {
        dataRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<Futbolista> futbolistaList = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Futbolista futbolista = snapshot.getValue(Futbolista.class);
                    futbolista.setFutbolistaId(snapshot.getKey());
                    futbolistaList.add(futbolista);
                }
                futbolistaLiveData.postValue(futbolistaList);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                futbolistaLiveData.postValue(null);
            }
        });
    }
}