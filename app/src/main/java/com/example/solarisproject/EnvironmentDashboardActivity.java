package com.example.solarisproject;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.solarisproject.data.DatabaseHelper;
import com.example.solarisproject.models.Alert;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EnvironmentDashboardActivity extends AppCompatActivity {

    private LineChart lineChart;
    private RecyclerView recyclerViewAlerts;
    private AlertAdapter alertAdapter;
    private List<Alert> alertList;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_environment_dashboard);

        lineChart = findViewById(R.id.lineChart);
        recyclerViewAlerts = findViewById(R.id.recyclerViewAlerts);

        dbHelper = new DatabaseHelper(this);
        alertList = new ArrayList<>();
        alertAdapter = new AlertAdapter(alertList);

        recyclerViewAlerts.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewAlerts.setAdapter(alertAdapter);

        setupLineChart();
        setLineChartData();
        generateDummyAlerts();
    }

    private void setupLineChart() {
        lineChart.setBackgroundColor(getResources().getColor(android.R.color.black));
        lineChart.setDrawGridBackground(false);
        lineChart.getDescription().setEnabled(false);

        XAxis xAxis = lineChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTextColor(getResources().getColor(android.R.color.white));
        xAxis.setDrawGridLines(false);

        YAxis leftAxis = lineChart.getAxisLeft();
        leftAxis.setTextColor(getResources().getColor(android.R.color.white));
        leftAxis.setDrawGridLines(true);

        YAxis rightAxis = lineChart.getAxisRight();
        rightAxis.setEnabled(false);
    }

    private void setLineChartData() {
        List<Entry> entries = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            entries.add(new Entry(i, new Random().nextInt(100)));
        }

        LineDataSet lineDataSet = new LineDataSet(entries, "Consumo de Energía");
        lineDataSet.setColor(getResources().getColor(android.R.color.holo_blue_light));
        lineDataSet.setValueTextColor(getResources().getColor(android.R.color.white));
        lineDataSet.setLineWidth(2f);
        lineDataSet.setCircleColor(getResources().getColor(android.R.color.holo_blue_light));
        lineDataSet.setCircleRadius(3f);

        LineData lineData = new LineData(lineDataSet);
        lineChart.setData(lineData);
        lineChart.invalidate(); // Refresh chart
    }

    private void generateDummyAlerts() {
        alertList.add(new Alert("Nivel de CO2 alto", "El nivel de CO2 ha aumentado significativamente.", "Alta", getResources().getColor(android.R.color.holo_red_light)));
        alertList.add(new Alert("Consumo energético alto", "El consumo de energía está por encima del promedio.", "Media", getResources().getColor(android.R.color.holo_orange_light)));
        alertList.add(new Alert("Generación de energía baja", "La generación de energía ha disminuido.", "Baja", getResources().getColor(android.R.color.holo_green_light)));

        alertAdapter.notifyDataSetChanged();
    }
}
