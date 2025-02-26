package com.example.proyecto1.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.proyecto1.models.Futbolista;
import com.example.proyecto1.repositories.DashboardRepository;

import java.util.List;

// Definición de la clase DashboardViewModel, que se encarga de manejar los datos relacionados con el dashboard (pantalla principal)
public class DashboardViewModel extends ViewModel {

    // MutableLiveData para almacenar la lista de futbolistas que se mostrará en la UI
    private final MutableLiveData<List<Futbolista>> futbolistaLiveData = new MutableLiveData<>();

    // Instancia del repositorio que maneja la carga de los futbolistas desde la base de datos
    private final DashboardRepository repository;

    // Constructor de la clase, donde se inicializa el repositorio y se carga la lista de futbolistas
    public DashboardViewModel() {
        repository = new DashboardRepository(); // Inicializa el repositorio que obtiene los datos
        fetchFutbolistas(); // Llama al método para obtener los futbolistas desde el repositorio
    }

    // Método público para obtener los futbolistas, devuelve un LiveData que puede ser observado por la UI
    public LiveData<List<Futbolista>> getFutbolistas() {
        return futbolistaLiveData; // Retorna el LiveData con la lista de futbolistas
    }

    // Método privado que se encarga de cargar la lista de futbolistas desde el repositorio
    private void fetchFutbolistas() {
        repository.fetchFutbolistas(futbolistaLiveData); // Llama al repositorio para obtener los futbolistas y actualizar el LiveData
    }
}