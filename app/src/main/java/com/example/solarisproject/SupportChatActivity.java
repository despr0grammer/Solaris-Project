package com.example.solarisproject;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class SupportChatActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_support_chat);

        TextView chatTextView = findViewById(R.id.chatTextView);
        chatTextView.setText("Soporte: Hola, ¿cómo puedo ayudarte?\nUsuario: Tengo un problema con mi panel solar.\nSoporte: Entiendo, ¿podrías describir el problema?\n...");
    }
}
