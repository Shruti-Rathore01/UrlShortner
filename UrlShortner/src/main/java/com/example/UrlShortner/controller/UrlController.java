package com.example.UrlShortner.controller;

import com.example.UrlShortner.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/url")
public class UrlController {
    @Autowired
    public UrlService service;

    @PostMapping(value = "/shorten", consumes = MediaType.TEXT_PLAIN_VALUE)
    public String shortenUrl(@RequestBody String longUrl)
    {
        return service.shortenUrl(longUrl);
    }
    @GetMapping("/{shortUrl}")
    public ResponseEntity<String> getLongUrl(@PathVariable String shortUrl) {
        try {
            String longUrl = service.getLongUrl(shortUrl);
            return ResponseEntity.ok(longUrl);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(" Error: " + e.getMessage());
        }
    }



}
