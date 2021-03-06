package com.example.library.model.entity;

public class Publication {
    private int id;
    private String name;

    public Publication() {
        id = -1;
    }

    public Publication(int id) {
        this.id = id;
    }

    public Publication(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
