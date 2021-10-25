package com.example.library.model.entity;

import java.util.Hashtable;

public class Page {
    private String column;
    private String direction;
    private int page;
    private int pageCount;
    private int limit;
    private Hashtable<String, String> query;

    public Page()
    {
        limit = 10;
        direction = "asc";
        page = 1;
        query = new Hashtable<String, String>();
    }

    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public void setCount(int cn)
    {
        int cur = pageCount;
        pageCount = cn / limit;
        if (cn % limit > 0) pageCount++;
        if (page > pageCount)
            page = 1;
    }

    public void addQuery(String col, String val)
    {
        query.put(col, val);
    }

    public Hashtable<String, String> getQuery()
    {
        return query;
    }
}
