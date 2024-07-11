package com.example.solarisproject;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private String userType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Obtener el tipo de usuario del Intent
        userType = getIntent().getStringExtra("USER_TYPE");

        // Log para verificar el tipo de usuario recibido
        Log.d("MainActivity", "User type: " + userType);

        // Botón para ir al perfil del usuario
        Button profileButton = findViewById(R.id.button_profile);
        profileButton.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, ProfileActivity.class)));

        // Botón para ir a la tienda
        Button storeButton = findViewById(R.id.button_store);
        storeButton.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, StoreActivity.class)));

        // Botón para ir a configuración
        Button settingsButton = findViewById(R.id.button_settings);
        settingsButton.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, SettingsActivity.class)));

        // Botón para ir a emergencias
        Button emergenciesButton = findViewById(R.id.button_emergencies);
        emergenciesButton.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, EmergenciesActivity.class)));

        // Botones de los diferentes dashboards
        Button ownerDashboardButton = findViewById(R.id.button_owner_dashboard);
        Button adminDashboardButton = findViewById(R.id.button_admin_dashboard);
        Button environmentDashboardButton = findViewById(R.id.button_environment_dashboard);
        Button investorDashboardButton = findViewById(R.id.button_investor_dashboard);

        // Mostrar el botón de Dashboard correspondiente y ocultar los demás
        switch (userType) {
            case "Propietario":
                ownerDashboardButton.setVisibility(View.VISIBLE);
                adminDashboardButton.setVisibility(View.GONE);
                environmentDashboardButton.setVisibility(View.GONE);
                investorDashboardButton.setVisibility(View.GONE);
                ownerDashboardButton.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, OwnerDashboardActivity.class)));
                break;
            case "Administrador":
                ownerDashboardButton.setVisibility(View.GONE);
                adminDashboardButton.setVisibility(View.VISIBLE);
                environmentDashboardButton.setVisibility(View.GONE);
                investorDashboardButton.setVisibility(View.GONE);
                adminDashboardButton.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, AdminDashboardActivity.class)));
                break;
            case "Preocupado por el Medio Ambiente":
                ownerDashboardButton.setVisibility(View.GONE);
                adminDashboardButton.setVisibility(View.GONE);
                environmentDashboardButton.setVisibility(View.VISIBLE);
                investorDashboardButton.setVisibility(View.GONE);
                environmentDashboardButton.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, EnvironmentDashboardActivity.class)));
                break;
            case "Inversionista":
                ownerDashboardButton.setVisibility(View.GONE);
                adminDashboardButton.setVisibility(View.GONE);
                environmentDashboardButton.setVisibility(View.GONE);
                investorDashboardButton.setVisibility(View.VISIBLE);
                investorDashboardButton.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, InvestorDashboardActivity.class)));
                break;
            default:
                // Caso de manejo de un tipo de usuario desconocido
                Toast.makeText(this, "Tipo de usuario desconocido: " + userType, Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
