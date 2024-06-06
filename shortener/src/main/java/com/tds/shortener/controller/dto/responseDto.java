package com.tds.shortener.controller.dto;

import java.util.List;


@SuppressWarnings("rawtypes")
public record responseDto(int httpStatus, List responseBody) {
    
}
