package com.tds.shortener.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.tds.shortener.model.Url;

@Repository
public interface UrlRepository extends MongoRepository<Url, String> {
    Url findByShortUrl(String shortUrl);
}
