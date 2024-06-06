package com.tds.shortener.controller.dto;

import com.tds.shortener.model.url;

public record responseDto(int httpStatus, url responseBody) {
    
}
