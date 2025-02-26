package com.example.proyecto1.views;

import android.os.Bundle;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.example.proyecto1.R;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializar DrawerLayout
        drawerLayout = findViewById(R.id.drawerLayout);

        // Inicializar NavigationView, que es la vista del menú lateral
        NavigationView navigationView = findViewById(R.id.navigationView);

        // Configurar el ActionBarDrawerToggle para mostrar y ocultar el menú lateral
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState(); // Sincronizar el estado del DrawerToggle con la acción del menú
        getSupportActionBar(); // Mostrar la ActionBar

        // Verificar si es la primera vez que se crea la actividad o si es una rotación de pantalla
        if (savedInstanceState == null) {
            // Si no hay estado guardado, cargar el fragmento por defecto (DashboardFragment)
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragmentContainer, new DashboardFragment())
                    .commit();
        }

        // Establecer el Listener para los elementos del menú de navegación
        navigationView.setNavigationItemSelectedListener(item -> {
            Fragment selectedFragment = null;

            // Dependiendo del item seleccionado, se muestra el fragment correspondiente
            if (item.getItemId() == R.id.nav_dashboard) {
                selectedFragment = new DashboardFragment();
            } else if (item.getItemId() == R.id.nav_favourites) {
                selectedFragment = new FavouritesFragment();
            } else if (item.getItemId() == R.id.nav_profile) {
                selectedFragment = new ProfileFragment();
            } else if (item.getItemId() == R.id.nav_logout) {
                selectedFragment = new LogoutFragment();
            } else if (item.getItemId() == R.id.nav_random) {
                selectedFragment = new AzarFragment();
            }

            // Si se seleccionó un fragmento válido, reemplazar el fragmento actual
            if (selectedFragment != null) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragmentContainer, selectedFragment)
                        .commit();
            }

            // Cerrar el drawer una vez se seleccione un ítem
            drawerLayout.closeDrawer(GravityCompat.START);
            return true;
        });
    }

    // Método para manejar la navegación hacia atrás en la barra de navegación
    @Override
    public boolean onSupportNavigateUp() {
        // Si el drawer está abierto, cerrarlo, si no, abrirlo
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            drawerLayout.openDrawer(GravityCompat.START);
        }
        return super.onSupportNavigateUp();
    }

    // Manejar el botón de retroceso
    @Override
    public void onBackPressed() {
        // Si el drawer está abierto, cerrarlo, si no, ejecutar el comportamiento normal de back press
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}