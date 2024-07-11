package com.example.solarisproject;

import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SettingsActivity extends AppCompatActivity {

    private Switch switchDarkMode;
    private Switch switchNotifications;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        // Inicializar vistas
        switchDarkMode = findViewById(R.id.switch_dark_mode);
        switchNotifications = findViewById(R.id.switch_notifications);

        // Configurar listeners de los switches
        switchDarkMode.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // Acción cuando se cambia el estado del switch de Modo Oscuro
                if (isChecked) {
                    // Modo oscuro activado
                    Toast.makeText(SettingsActivity.this, "Modo Oscuro activado", Toast.LENGTH_SHORT).show();
                } else {
                    // Modo oscuro desactivado
                    Toast.makeText(SettingsActivity.this, "Modo Oscuro desactivado", Toast.LENGTH_SHORT).show();
                }
            }
        });

        switchNotifications.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // Acción cuando se cambia el estado del switch de Notificaciones
                if (isChecked) {
                    // Notificaciones activadas
                    Toast.makeText(SettingsActivity.this, "Notificaciones activadas", Toast.LENGTH_SHORT).show();
                } else {
                    // Notificaciones desactivadas
                    Toast.makeText(SettingsActivity.this, "Notificaciones desactivadas", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
