package com.tds.shortener.service;

import java.util.Map;


/**
 * DailyService
 */
public interface DailyService {

    public void updateAccessStats(String url);    
    public Map<String, Object> getAccessStats(String url);
}