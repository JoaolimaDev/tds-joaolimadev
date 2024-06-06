package com.tds.shortener.service;

import com.tds.shortener.controller.dto.createDto;
import com.tds.shortener.model.url;

public interface urlService {
    
    public url createUrl(createDto dto);
    public String shortUrl();
    public void redirect();

}
