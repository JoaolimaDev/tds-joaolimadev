package com.tds.shortener.service.impl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tds.shortener.controller.dto.createDto;
import com.tds.shortener.model.url;
import com.tds.shortener.repository.UrlRepository;
import com.tds.shortener.service.urlService;

@Service
public class urlServiceImpl implements urlService {
    @Autowired
    private UrlRepository urlRepository;

    @Override
    public url createUrl(createDto originalUrl) {
        url url = new url();
        url.setOriginalUrl(originalUrl.url());
        url.setShortUrl(shortUrl());
        url.setCreationDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        return urlRepository.save(url);
    }

    @Override
    public String shortUrl() {
        return "https://www.urlshortener.com/" + RandomStringUtils.randomAlphanumeric(10);
    }

    @Override
    public void redirect() {
       
    }
    
    
}
