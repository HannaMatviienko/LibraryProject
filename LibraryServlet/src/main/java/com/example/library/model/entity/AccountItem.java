package com.example.library.model.entity;

public class AccountItem {
    private int id;
    private Book book;
    private int status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public int getStatus() { return status; }

    public void setStatus(int status) { this.status = status; }
}
