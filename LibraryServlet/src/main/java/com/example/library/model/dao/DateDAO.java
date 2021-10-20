package com.example.library.model.dao;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateDAO {
    public String getNow()
    {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Date date = new Date(System.currentTimeMillis());
        return formatter.format(date);
    }
}
