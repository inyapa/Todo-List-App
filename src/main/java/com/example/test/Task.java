package com.example.test;

import java.time.LocalDateTime;

public class Task {
    private String description;
    private LocalDateTime time;

    public Task(String description, LocalDateTime time) {
        this.description = description;
        this.time = time;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getTime() {
        return time;
    }
}
