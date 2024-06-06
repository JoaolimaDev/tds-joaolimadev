package com.tds.shortener.service;

/**
 * DailyService
 */
public interface DailyService {

    public void updateAccessStats(String url);
    public boolean findByShortUrl(String url);
    
}