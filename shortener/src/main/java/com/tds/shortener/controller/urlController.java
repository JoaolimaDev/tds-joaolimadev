package com.tds.shortener.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tds.shortener.model.Url;
import com.tds.shortener.service.urlService;

@RestController
@RequestMapping("/api")
public class urlController {

    @Autowired
    private urlService urlService;

    @PostMapping("/urls")
    public Url createUrl() {
        return urlService.createUrl();
    }
    
}
