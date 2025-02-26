package com.example.proyecto1.views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.proyecto1.R;
import com.example.proyecto1.databinding.FragmentAzarBinding;
import com.example.proyecto1.viewmodels.AzarViewModel;
import com.squareup.picasso.Picasso;


public class AzarFragment extends Fragment {
    private FragmentAzarBinding binding;
    private AzarViewModel azarViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_azar, container, false);
        azarViewModel = new ViewModelProvider(this).get(AzarViewModel.class);

        azarViewModel.getFutbolistaLiveData().observe(getViewLifecycleOwner(), futbolista -> {
            if (futbolista != null) {
                binding.tvDetailTitle.setText(futbolista.getTitulo());
                binding.tvDetailDescription.setText(futbolista.getDescripcion());
                Picasso.with(getContext()).load(futbolista.getUrl()).into(binding.ivDetailImage);
            }
        });

        binding.btnAzar.setOnClickListener(v -> azarViewModel.seleccionarFutbolistaAleatorio());
        return binding.getRoot();
    }
}