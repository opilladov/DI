package com.example.proyecto1.views;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.example.proyecto1.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends AppCompatActivity {

    private TextView tvDetailTitle, tvDetailDescription;
    private ImageView ivDetailImage;
    private Button btnBack;
    private FloatingActionButton fabFavorite;

    private DatabaseReference userFavoritesRef;
    private String currentUserId;
    private String futbolistaId;
    private boolean isFavorite = false;
    private List<String> favoritosList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        tvDetailTitle = findViewById(R.id.tvDetailTitle);
        tvDetailDescription = findViewById(R.id.tvDetailDescription);
        ivDetailImage = findViewById(R.id.ivDetailImage);
        btnBack = findViewById(R.id.btnBack);
        fabFavorite = findViewById(R.id.fabFavorite);

        String title = getIntent().getStringExtra("title");
        String description = getIntent().getStringExtra("description");
        String imageUrl = getIntent().getStringExtra("imageUrl");
        futbolistaId = getIntent().getStringExtra("futbolistaId");

        if (title != null) tvDetailTitle.setText(title);
        if (description != null) tvDetailDescription.setText(description);
        if (imageUrl != null) Picasso.with(this).load(imageUrl).into(ivDetailImage);

        btnBack.setOnClickListener(v -> finish());

        currentUserId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        userFavoritesRef = FirebaseDatabase.getInstance().getReference("users").child(currentUserId).child("favoritos");

        checkIfFavorite();

        fabFavorite.setOnClickListener(v -> toggleFavorite());
    }

    private void checkIfFavorite() {
        userFavoritesRef.get().addOnCompleteListener(task -> {
            if (task.isSuccessful() && task.getResult().exists()) {
                DataSnapshot snapshot = task.getResult();
                favoritosList.clear();

                for (DataSnapshot favSnapshot : snapshot.getChildren()) {
                    favoritosList.add(favSnapshot.getValue(String.class));
                }

                isFavorite = favoritosList.contains(futbolistaId);
                updateFavoriteButton();
            }
        });
    }

    private void toggleFavorite() {
        if (isFavorite) {
            favoritosList.remove(futbolistaId);
        } else {
            favoritosList.add(futbolistaId);
        }

        userFavoritesRef.setValue(favoritosList).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                isFavorite = !isFavorite;
                updateFavoriteButton();
            } else {
                Toast.makeText(DetailActivity.this, "Error al actualizar favorito", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateFavoriteButton() {
        runOnUiThread(() -> {
            if (isFavorite) {
                fabFavorite.setImageResource(R.drawable.star_24);
            } else {
                fabFavorite.setImageResource(R.drawable.star_border_24);
            }
        });
    }
}