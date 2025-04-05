package com.example.UrlShortner.repo;

import com.example.UrlShortner.model.Url;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UrlRepo extends JpaRepository<Url,Long> {
    Optional<Url> findByShortUrl(String shortUrl);
    Optional<Url> findByLongUrl(String longUrl);
}
