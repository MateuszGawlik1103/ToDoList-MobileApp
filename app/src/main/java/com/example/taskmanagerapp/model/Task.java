package com.example.taskmanagerapp.model; // Upewnij się, że importujesz odpowiedni pakiet

public class Task {
    private String title;
    private String details;
    private String dueDate;
    private String status;

    public Task(String title, String details, String dueDate, String status) {
        this.title = title;
        this.details = details;
        this.dueDate = dueDate;
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public String getDetails() {
        return details;
    }

    public String getDueDate() {
        return dueDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
