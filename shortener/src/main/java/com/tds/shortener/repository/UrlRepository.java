package com.tds.shortener.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.tds.shortener.model.url;

@Repository
public interface UrlRepository extends MongoRepository<url, String> {
    
    Optional<url> findByShortUrl(String shortUrl);
}
