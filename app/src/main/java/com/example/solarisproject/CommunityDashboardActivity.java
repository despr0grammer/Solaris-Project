package com.example.solarisproject;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

public class CommunityDashboardActivity extends AppCompatActivity {

    private LineChart lineChart;
    private TextView textViewEnergyConsumed;
    private TextView textViewEnergyContributed;
    private TextView textViewSavings;
    private TextView textViewEnvironmentalImpact;
    private Handler handler;
    private Random random;
    private Spinner spinnerFilter;
    private Spinner spinnerMonth;
    private String currentFilter = "Diario";
    private int currentMonth = Calendar.getInstance().get(Calendar.MONTH);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_community_dashboard);

        // Inicializar TextViews para datos en tiempo real
        textViewEnergyConsumed = findViewById(R.id.textView_energy_consumed);
        textViewEnergyContributed = findViewById(R.id.textView_energy_contributed);
        textViewSavings = findViewById(R.id.textView_savings);
        textViewEnvironmentalImpact = findViewById(R.id.textView_environmental_impact);

        // Inicializar Line Chart
        lineChart = findViewById(R.id.lineChart);
        setupLineChart();

        // Inicializar handler y random para datos simulados
        handler = new Handler();
        random = new Random(); // Inicializar aquí
        simulateRealTimeData();

        // Configurar filtros
        spinnerFilter = findViewById(R.id.spinner_filter);
        spinnerMonth = findViewById(R.id.spinner_month);

        spinnerFilter.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                currentFilter = parent.getItemAtPosition(position).toString();
                updateChartData();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing
            }
        });

        spinnerMonth.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                currentMonth = position;
                updateChartData();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing
            }
        });

        // Configurar datos iniciales del gráfico
        setLineChartData();
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
        for (int i = 0; i < 12; i++) {
            entries.add(new Entry(i, random.nextInt(100))); // Simular datos
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

    private void simulateRealTimeData() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Simular datos en tiempo real
                textViewEnergyConsumed.setText("Energía Consumida: " + random.nextInt(500) + "Wh");
                textViewEnergyContributed.setText("Energía Contribuida: " + random.nextInt(500) + "Wh");
                textViewSavings.setText("Ahorros: $" + random.nextInt(1000) + " CLP");
                textViewEnvironmentalImpact.setText("Impacto Ambiental: " + random.nextInt(100) + " árboles");

                // Volver a llamar a este método después de 5 segundos
                handler.postDelayed(this, 5000);
            }
        }, 5000);
    }

    private void updateChartData() {
        // Actualizar datos del gráfico según el filtro y el mes seleccionados
        List<Entry> entries = new ArrayList<>();
        int maxEntries = currentFilter.equals("Diario") ? 30 : currentFilter.equals("Mensual") ? 12 : 365;
        for (int i = 0; i < maxEntries; i++) {
            entries.add(new Entry(i, random.nextInt(100)));
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
}
