package com.example.solarisproject;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class EmergenciesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergencies);

        Button supportButton = findViewById(R.id.button_support);
        Button fireButton = findViewById(R.id.button_fire);
        Button requestButton = findViewById(R.id.button_request);

        supportButton.setOnClickListener(v -> {
            startActivity(new Intent(EmergenciesActivity.this, SupportChatActivity.class));
        });

        fireButton.setOnClickListener(v -> {
            Toast.makeText(EmergenciesActivity.this, "Contactando a Bomberos de Chile", Toast.LENGTH_SHORT).show();
        });

        requestButton.setOnClickListener(v -> {
            startActivity(new Intent(EmergenciesActivity.this, RequestFormActivity.class));
        });
    }
}
