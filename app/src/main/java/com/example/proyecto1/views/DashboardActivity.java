package com.example.proyecto1.views;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyecto1.adapters.FutbolistaAdapter;
import com.example.proyecto1.R;
import com.example.proyecto1.models.Futbolista;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DashboardActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private FirebaseDatabase database;
    private DatabaseReference dataRef;
    private List<Futbolista> futbolistaList;
    private FutbolistaAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        futbolistaList = new ArrayList<>();
        adapter = new FutbolistaAdapter(futbolistaList, this::openDetailActivity);
        recyclerView.setAdapter(adapter);

        database = FirebaseDatabase.getInstance();
        dataRef = database.getReference("futbolista");

        loadDataFromFirebase();
    }

    private void loadDataFromFirebase() {
        dataRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                futbolistaList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Futbolista futbolista = snapshot.getValue(Futbolista.class);
                    futbolistaList.add(futbolista);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(DashboardActivity.this, "Error al cargar los datos", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void openDetailActivity(Futbolista futbolista) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("title", futbolista.getTitulo());
        intent.putExtra("description", futbolista.getDescripcion());
        intent.putExtra("imageUrl", futbolista.getUrl());
        startActivity(intent);
    }
}