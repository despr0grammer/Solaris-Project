package com.example.solarisproject;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.solarisproject.data.DatabaseHelper;
import com.example.solarisproject.data.UserContract.UserEntry;

public class RegisterActivity extends AppCompatActivity {

    private EditText editTextName, editTextLastName, editTextAge, editTextEmail, editTextPassword, editTextPhone;
    private Spinner spinnerRole;
    private Button buttonRegister;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        editTextName = findViewById(R.id.editText_name);
        editTextLastName = findViewById(R.id.editText_last_name);
        editTextAge = findViewById(R.id.editText_age);
        editTextEmail = findViewById(R.id.editText_email);
        editTextPhone = findViewById(R.id.editText_phone);
        editTextPassword = findViewById(R.id.editText_password);
        spinnerRole = findViewById(R.id.spinner_role);
        buttonRegister = findViewById(R.id.buttonRegister);

        dbHelper = new DatabaseHelper(this);

        // Configurar el adaptador del Spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.user_type_options, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerRole.setAdapter(adapter);

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });
    }

    private void registerUser() {
        String name = editTextName.getText().toString().trim();
        String lastName = editTextLastName.getText().toString().trim();
        String ageText = editTextAge.getText().toString().trim();
        int age = ageText.isEmpty() ? -1 : Integer.parseInt(ageText);
        String email = editTextEmail.getText().toString().trim();
        String phone = editTextPhone.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        String role = spinnerRole.getSelectedItem().toString();

        if (name.isEmpty() || lastName.isEmpty() || email.isEmpty() || password.isEmpty() || phone.isEmpty() || ageText.isEmpty()) {
            Toast.makeText(this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(UserEntry.COLUMN_NAME, name);
        values.put(UserEntry.COLUMN_LAST_NAME, lastName);
        values.put(UserEntry.COLUMN_AGE, age);
        values.put(UserEntry.COLUMN_EMAIL, email);
        values.put(UserEntry.COLUMN_PHONE, phone);
        values.put(UserEntry.COLUMN_PASSWORD, password);
        values.put(UserEntry.COLUMN_USER_TYPE, role);

        long newRowId = db.insert(UserEntry.TABLE_NAME, null, values);
        if (newRowId != -1) {
            Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(this, "Error en el registro", Toast.LENGTH_SHORT).show();
        }
    }
}
