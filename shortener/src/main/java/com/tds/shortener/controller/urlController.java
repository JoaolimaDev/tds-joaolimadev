package com.tds.shortener.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tds.shortener.controller.dto.createDto;
import com.tds.shortener.controller.dto.responseDto;
import com.tds.shortener.service.DailyService;
import com.tds.shortener.service.urlService;

@RestController
@RequestMapping("/api")
public class urlController {

    @Autowired
    private urlService urlService;

    @Autowired
    private DailyService dailyService;

    @PostMapping("/urls")
    public ResponseEntity<responseDto> createUrl(@RequestBody createDto dto) {
        
        return ResponseEntity.ok(new responseDto(HttpStatus.CREATED.value(), urlService.createUrl(dto)));
    }

    @GetMapping("/urls")
    public void updateAccessStats(@RequestParam String url) {
        
        
    }
    
}
