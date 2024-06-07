package com.tds.shortener.service.impl;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tds.shortener.controller.dto.createDto;
import com.tds.shortener.model.url;
import com.tds.shortener.repository.UrlRepository;
import com.tds.shortener.service.DailyService;
import com.tds.shortener.service.urlService;

import jakarta.servlet.http.HttpServletResponse;

@Service
public class urlServiceImpl implements urlService {

    @Autowired
    private UrlRepository urlRepository;

    @Autowired
    private DailyService dailyService;

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
    public List<url> listUrl(){
        List<url> listUrls = urlRepository.findAll();
        return listUrls;
    }

    @Override
    public String redirect(String url, HttpServletResponse response)  throws IOException {

        Optional<url> optionalUrl = urlRepository.findByShortUrl(url);

        if (optionalUrl.isPresent()) {
            dailyService.updateAccessStats(url);
            response.sendRedirect(optionalUrl.get().getOriginalUrl());
            return optionalUrl.get().getOriginalUrl();
        }

        response.sendError(HttpServletResponse.SC_NOT_FOUND, "URL inválida!");
        return ""; 
    }

    @Override
    public List<url> listByname(String url) {
        Optional<url> optionalUrl = urlRepository.findByShortUrl(url);
        return List.of(optionalUrl.get());
    }
      
}
