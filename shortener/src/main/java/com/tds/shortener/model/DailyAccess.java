package com.tds.shortener.model;

import lombok.Data;

@Data
public class DailyAccess {
    private String date;
    private int count;

    public DailyAccess(String date, int count) {
        this.date = date;
        this.count = count;
    }
}
