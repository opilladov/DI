package com.example.proyecto1.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.proyecto1.models.Futbolista;
import com.example.proyecto1.repositories.DashboardRepository;

import java.util.List;

public class DashboardViewModel extends ViewModel {

    private final MutableLiveData<List<Futbolista>> futbolistaLiveData = new MutableLiveData<>();
    private final DashboardRepository repository;

    public DashboardViewModel() {
        repository = new DashboardRepository();
        fetchFutbolistas();
    }

    public LiveData<List<Futbolista>> getFutbolistas() {
        return futbolistaLiveData;
    }

    private void fetchFutbolistas() {
        repository.fetchFutbolistas(futbolistaLiveData);
    }
}