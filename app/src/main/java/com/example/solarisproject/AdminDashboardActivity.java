package com.example.solarisproject;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.solarisproject.data.DatabaseHelper;
import com.example.solarisproject.models.Alert;
import com.example.solarisproject.models.User;

import java.util.ArrayList;
import java.util.List;

public class AdminDashboardActivity extends AppCompatActivity {

    private RecyclerView recyclerViewAlerts, recyclerViewUsers;
    private AlertAdapter alertAdapter;
    private UserAdapter userAdapter;
    private List<Alert> alertList;
    private List<User> userList;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);

        recyclerViewAlerts = findViewById(R.id.recyclerViewAlerts);
        recyclerViewUsers = findViewById(R.id.recyclerViewUsers);

        dbHelper = new DatabaseHelper(this);
        alertList = new ArrayList<>();
        userList = new ArrayList<>();
        alertAdapter = new AlertAdapter(alertList);
        userAdapter = new UserAdapter(userList);

        recyclerViewAlerts.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewUsers.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewAlerts.setAdapter(alertAdapter);
        recyclerViewUsers.setAdapter(userAdapter);

        loadAlerts();
        loadUsers();
    }

    private void loadAlerts() {
        // Simulate loading alerts from database or server
        alertList.add(new Alert("Problema de conexión", "No se detecta señal del panel 3.", "Alta", getResources().getColor(android.R.color.holo_red_light)));
        alertList.add(new Alert("Producción baja", "La producción del panel 2 está por debajo del promedio.", "Media", getResources().getColor(android.R.color.holo_orange_light)));
        alertList.add(new Alert("Mantenimiento requerido", "El panel 1 necesita limpieza.", "Baja", getResources().getColor(android.R.color.holo_green_light)));

        alertAdapter.notifyDataSetChanged();
    }

    private void loadUsers() {
        userList.add(new User(1, "Diego", "Venegas", 25, "diego.venegas22@inacapmail.cl", "123456789", "password1", "Propietario"));
        userList.add(new User(2, "Maria", "Perez", 30, "maria.perez@example.com", "987654321", "password2", "Administrador"));
        userList.add(new User(3, "Carlos", "Lopez", 35, "carlos.lopez@example.com", "456789123", "password3", "Inversionista"));

        userAdapter.notifyDataSetChanged();
    }
}
