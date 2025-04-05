package com.example.UrlShortner.service;

import com.example.UrlShortner.model.Url;
import com.example.UrlShortner.repo.UrlRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class UrlService {
    @Autowired
    UrlRepo repo;
    public AtomicLong counter=new AtomicLong(100000);
    public final String baseUrl="https://short.ly/";

    public String shortenUrl(String longUrl) {
        Optional<Url> existing=repo.findByLongUrl(longUrl);
        if(existing.isPresent())
        {
            return baseUrl+existing.get().getShortUrl();
        }
        String shortUrl=encode(counter.incrementAndGet());
        Url urlObj=new Url(shortUrl,longUrl);
        repo.save(urlObj);
        return baseUrl+shortUrl;

    }

    private String encode(long id)
    {
        String chars="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder ans=new StringBuilder();
        while(id>0)
        {
            ans.insert(0,chars.charAt((int)(id%62)));
            id=id/62;
        }
        return ans.toString();

    }

    public String getLongUrl(String shortUrl) {
        System.out.println(" Received short URL: " + shortUrl);

        return repo.findByShortUrl(shortUrl)
                .map(url -> {
                    System.out.println(" Found long URL: " + url.getLongUrl());
                    return url.getLongUrl();
                })
                .orElseThrow(() -> {
                    System.out.println(" Short URL not found in DB.");
                    return new RuntimeException("Short URL not found");
                });
    }

}
