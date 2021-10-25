package com.example.library.model.entity;

public class Author {
    private int id;
    private String name;

    public Author() {
        id = -1;
    }

    public Author(int id) {
        this.id = id;
    }

    public Author(int id, String name) {
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
