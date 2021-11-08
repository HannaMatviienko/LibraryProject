package com.library.dto;

import lombok.*;

@Getter
@Setter
public class BookPage {

    public BookPage()
    {
        sortCol = "name";
        sortDir = "asc";
        page = 1;
        pageCount = 1;
        pageSize = 10;

        queryName = "";
        queryAuthor = "";
        queryPublication = "";
        queryYear = "";
    }

    private String sortCol;
    private String sortDir;
    private int page;
    private int pageCount;
    private int pageSize;
    private String queryName;
    private String queryAuthor;
    private String queryPublication;
    private String queryYear;

    public String getNormName()
    {
        if (queryName.isEmpty())
            return "%";
        else
            return "%" + queryName + "%";
    }

    public String getNormAuthor()
    {
        if (queryAuthor.isEmpty())
            return "%";
        else
            return "%" + queryAuthor + "%";
    }

    public String getNormPublication()
    {
        if (queryPublication.isEmpty())
            return "%";
        else
            return "%" + queryPublication + "%";
    }

    public String getNormYear()
    {
        if (queryYear.isEmpty())
            return "%";
        else
            return "%" + queryYear + "%";
    }
}
