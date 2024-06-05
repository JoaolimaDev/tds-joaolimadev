package com.tds.shortener.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;


@Data
@Document(collection = "urls")
public class Url {
    
    @Id
    private String id;
    private String originalUrl;
    private String shortUrl;
    private LocalDateTime creationDate;
    private List<DailyAccess> accessStats = new ArrayList<>();

}
