package com.example.proyecto1.views;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.proyecto1.R;
import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {

    private TextView tvDetailTitle, tvDetailDescription;
    private ImageView ivDetailImage;
    private Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        tvDetailTitle = findViewById(R.id.tvDetailTitle);
        tvDetailDescription = findViewById(R.id.tvDetailDescription);
        ivDetailImage = findViewById(R.id.ivDetailImage);
        btnBack = findViewById(R.id.btnBack);

        String title = getIntent().getStringExtra("title");
        String description = getIntent().getStringExtra("description");
        String imageUrl = getIntent().getStringExtra("imageUrl");

        if (title != null) {
            tvDetailTitle.setText(title);
        }
        if (description != null) {
            tvDetailDescription.setText(description);
        }
        if (imageUrl != null) {
            Picasso.with(this)
                    .load(imageUrl)
                    .into(ivDetailImage);
        }

        btnBack.setOnClickListener(v -> {
            finish();
        });
    }
}