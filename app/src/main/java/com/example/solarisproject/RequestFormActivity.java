package com.example.solarisproject;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class RequestFormActivity extends AppCompatActivity {

    private EditText emailEditText;
    private EditText requestEditText;
    private Button sendButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_form);

        emailEditText = findViewById(R.id.editTextEmail);
        requestEditText = findViewById(R.id.editTextRequest);
        sendButton = findViewById(R.id.buttonSend);

        sendButton.setOnClickListener(v -> {
            String email = emailEditText.getText().toString().trim();
            String request = requestEditText.getText().toString().trim();

            if (email.isEmpty() || request.isEmpty()) {
                Toast.makeText(RequestFormActivity.this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
            } else {
                // Simulación de envío de solicitud
                Toast.makeText(RequestFormActivity.this, "Solicitud enviada", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}
