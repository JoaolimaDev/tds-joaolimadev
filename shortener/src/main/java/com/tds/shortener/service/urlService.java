package com.tds.shortener.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tds.shortener.model.Url;
import com.tds.shortener.repository.UrlRepository;

@Service
public class urlService {

    @Autowired
    private UrlRepository urlRepository;

    public Url createUrl() {
        Url url = new Url();
        url.setOriginalUrl("sds");
        url.setShortUrl("teste");
        url.setCreationDate(LocalDateTime.now());
        return urlRepository.save(url);
    }
    
}
