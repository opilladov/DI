package com.example.proyecto1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class DashboardActivity extends AppCompatActivity {

    private Button btnLogout;
    private FirebaseAuth mAuth;
    private TextView tvTitle, tvDescription;
    private ImageView ivImage;
    private FirebaseDatabase database;
    private DatabaseReference dataRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        mAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        dataRef = database.getReference("futbolista/1");

        tvTitle = findViewById(R.id.tvTitle);
        tvDescription = findViewById(R.id.tvDescription);
        ivImage = findViewById(R.id.ivImage);
        btnLogout = findViewById(R.id.btnLogout);

        loadDataFromFirebase();

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signOut();
                Intent intent = new Intent(DashboardActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();
            }
        });
    }

    private void loadDataFromFirebase() {
        dataRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String title = dataSnapshot.child("titulo").getValue(String.class);
                String description = dataSnapshot.child("descripcion").getValue(String.class);
                String imageUrl = dataSnapshot.child("url").getValue(String.class);

                if (title != null) {
                    tvTitle.setText(title);
                }
                if (description != null) {
                    tvDescription.setText(description);
                }
                if (imageUrl != null) {
                    Picasso.with(DashboardActivity.this)
                            .load(imageUrl)
                            .into(ivImage);
                } else {
                    ivImage.setVisibility(View.GONE);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(DashboardActivity.this, "Error al cargar los datos", Toast.LENGTH_SHORT).show();
            }
        });
    }
}