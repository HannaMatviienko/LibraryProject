package com.example.library.model.entity;

import java.util.Date;

public class UserBook {
    private int id;
    private Book book;
    private int status;
    private double fine;
    private int days;
    private Date dateTake;
    private Date dateBack;
    private Date dateActual;

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

    public double getFine() { return fine; }

    public void setFine(double fine) { this.fine = fine; }

    public int getDays() { return days; }

    public void setDays(int days) { this.days = days; }

    public Date getDateTake() { return dateTake; }

    public void setDateTake(Date dateTake) { this.dateTake = dateTake; }

    public Date getDateBack() { return dateBack; }

    public void setDateBack(Date dateBack) { this.dateBack = dateBack; }

    public Date getDateActual() { return dateActual; }

    public void setDateActual(Date dateActual) { this.dateActual = dateActual; }
}
