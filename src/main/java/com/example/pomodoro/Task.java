package com.example.pomodoro;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private boolean complete;

    public Task() {}

    public Task(String name) {
        this.name = name;
        this.complete = false;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isComplete() {
        return complete;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }
}