package com.example.UrlShortner.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Data

@AllArgsConstructor

public class Url {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String shortUrl;
    private String longUrl;
    private LocalDateTime createdAt;
    public Url(){}
    public Url(String shortUrl, String longUrl) {
        this.shortUrl = shortUrl;
        this.longUrl = longUrl;
        this.createdAt = LocalDateTime.now();
    }

    public String getShortUrl() {
        return shortUrl;
    }
    public String getLongUrl() {
        return longUrl;
    }

}
