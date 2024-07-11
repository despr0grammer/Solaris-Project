package com.example.solarisproject;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Random;

public class InvestorDashboardActivity extends AppCompatActivity {

    private TextView textViewSuggestedPanels, textViewSuggestedLocations, textViewRevenue, textViewCosts, textViewExpenses, textViewProfit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_investor_dashboard);

        textViewSuggestedPanels = findViewById(R.id.textViewSuggestedPanels);
        textViewSuggestedLocations = findViewById(R.id.textViewSuggestedLocations);
        textViewRevenue = findViewById(R.id.textViewRevenue);
        textViewCosts = findViewById(R.id.textViewCosts);
        textViewExpenses = findViewById(R.id.textViewExpenses);
        textViewProfit = findViewById(R.id.textViewProfit);

        displayInvestorData();
        displaySuggestedPanels();
        displaySuggestedLocations();
    }

    private void displayInvestorData() {
        // Simulated data for investor metrics
        double revenue = 100000; // Example revenue value
        double costs = 50000; // Example costs value
        double expenses = 20000; // Example expenses value
        double profit = revenue - costs - expenses; // Example profit calculation

        textViewRevenue.setText(String.format("Ingresos: $%.2f CLP", revenue));
        textViewCosts.setText(String.format("Costos: $%.2f CLP", costs));
        textViewExpenses.setText(String.format("Gastos: $%.2f CLP", expenses));
        textViewProfit.setText(String.format("Utilidades: $%.2f CLP", profit));
    }

    private void displaySuggestedPanels() {
        // Simulated data for suggested panels
        String[] panels = {
                "Panel Solar A - Alta eficiencia, 20% generación más alta",
                "Panel Solar B - Costo medio, 15% generación más alta",
                "Panel Solar C - Bajo costo, 10% generación más alta"
        };

        Random random = new Random();
        StringBuilder suggestedPanels = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            suggestedPanels.append(panels[random.nextInt(panels.length)]).append("\n");
        }

        textViewSuggestedPanels.setText(suggestedPanels.toString());
    }

    private void displaySuggestedLocations() {
        // Simulated data for suggested locations
        String[] locations = {
                "Zona Norte - Alta irradiación solar",
                "Zona Central - Buen balance de costo y generación",
                "Zona Sur - Oportunidades de subsidios y costos bajos"
        };

        Random random = new Random();
        StringBuilder suggestedLocations = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            suggestedLocations.append(locations[random.nextInt(locations.length)]).append("\n");
        }

        textViewSuggestedLocations.setText(suggestedLocations.toString());
    }
}
