package com.tds.shortener.model;

import lombok.Data;
import java.time.LocalDate;

@Data
public class DailyAccess {
    private LocalDate date;
    private int count;

    public DailyAccess(LocalDate date, int count) {
        this.date = date;
        this.count = count;
    }
}
