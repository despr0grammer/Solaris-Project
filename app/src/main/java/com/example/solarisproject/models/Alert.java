package com.example.solarisproject.models;

public class Alert {
    private String title;
    private String description;
    private String severity;
    private int color; // Agregar atributo color

    public Alert(String title, String description, String severity, int color) {
        this.title = title;
        this.description = description;
        this.severity = severity;
        this.color = color; // Inicializar color
    }

    // Getters y setters para los atributos, incluyendo color
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
