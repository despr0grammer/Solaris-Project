package com.example.solarisproject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.solarisproject.models.Alert;
import java.util.List;

public class AlertAdapter extends RecyclerView.Adapter<AlertAdapter.AlertViewHolder> {

    private List<Alert> alerts;

    public AlertAdapter(List<Alert> alerts) {
        this.alerts = alerts;
    }

    @Override
    public AlertViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_alert, parent, false);
        return new AlertViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AlertViewHolder holder, int position) {
        Alert alert = alerts.get(position);
        holder.textViewTitle.setText(alert.getTitle());
        holder.textViewDescription.setText(alert.getDescription());
        holder.textViewSeverity.setText(alert.getSeverity());

        // Set color based on severity
        switch (alert.getSeverity()) {
            case "Alta":
                holder.textViewSeverity.setTextColor(holder.itemView.getContext().getResources().getColor(android.R.color.holo_red_light));
                break;
            case "Media":
                holder.textViewSeverity.setTextColor(holder.itemView.getContext().getResources().getColor(android.R.color.holo_orange_light));
                break;
            case "Baja":
                holder.textViewSeverity.setTextColor(holder.itemView.getContext().getResources().getColor(android.R.color.holo_green_light));
                break;
        }
    }

    @Override
    public int getItemCount() {
        return alerts.size();
    }

    public static class AlertViewHolder extends RecyclerView.ViewHolder {
        TextView textViewTitle, textViewDescription, textViewSeverity;

        public AlertViewHolder(View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewDescription = itemView.findViewById(R.id.textViewDescription);
            textViewSeverity = itemView.findViewById(R.id.textViewSeverity);
        }
    }
}
