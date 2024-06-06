package com.tds.shortener.service;

import java.io.IOException;
import java.util.List;

import com.tds.shortener.controller.dto.createDto;
import com.tds.shortener.model.url;

import jakarta.servlet.http.HttpServletResponse;

public interface urlService {
    
    public url createUrl(createDto dto);
    public String shortUrl();
    public String redirect(String url, HttpServletResponse response)  throws IOException;
    public List<url> listUrl();
    public List<url> listByname(String url);
}
