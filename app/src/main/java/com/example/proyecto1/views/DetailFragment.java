package com.example.proyecto1.views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.proyecto1.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class DetailFragment extends Fragment {

    private TextView tvDetailTitle, tvDetailDescription;
    private ImageView ivDetailImage;
    private Button btnBack;
    private FloatingActionButton fabFavorite;

    private DatabaseReference userFavoritesRef;
    private String currentUserId;
    private String futbolistaId;
    private boolean isFavorite = false;
    private List<String> favoritosList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, container, false);

        tvDetailTitle = view.findViewById(R.id.tvDetailTitle);
        tvDetailDescription = view.findViewById(R.id.tvDetailDescription);
        ivDetailImage = view.findViewById(R.id.ivDetailImage);
        fabFavorite = view.findViewById(R.id.fabFavorite);

        if (getArguments() != null) {
            String title = getArguments().getString("title");
            String description = getArguments().getString("description");
            String imageUrl = getArguments().getString("imageUrl");
            futbolistaId = getArguments().getString("futbolistaId");

            if (title != null) tvDetailTitle.setText(title);
            if (description != null) tvDetailDescription.setText(description);
            if (imageUrl != null) Picasso.with(getContext()).load(imageUrl).into(ivDetailImage);
        }

        currentUserId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        userFavoritesRef = FirebaseDatabase.getInstance().getReference("users").child(currentUserId).child("favoritos");

        checkIfFavorite();

        fabFavorite.setOnClickListener(v -> toggleFavorite());

        return view;
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
                Toast.makeText(getContext(), "Error al actualizar favorito", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateFavoriteButton() {
        if (getActivity() != null) {
            getActivity().runOnUiThread(() -> {
                if (isFavorite) {
                    fabFavorite.setImageResource(R.drawable.star_24);
                } else {
                    fabFavorite.setImageResource(R.drawable.star_border_24);
                }
            });
        }
    }
}