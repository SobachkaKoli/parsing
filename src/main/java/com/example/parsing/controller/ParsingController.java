package com.example.parsing.controller;

import com.example.parsing.model.News;
import com.example.parsing.service.NewsService;
import com.example.parsing.service.impl.NewsParserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ParsingController {
    private final NewsService newsService;
    private final NewsParserServiceImpl newsParserService;

    @Scheduled(fixedRate = 20*60*1000)
    @GetMapping("/parse")
    public ResponseEntity<List<News>> parseNews() {
        List<News> parsedNews = newsParserService.parseNews();
        // Зберігаємо новини в базу даних
        for (News news : parsedNews) {
            newsService.saveNews(news);
        }
        return new ResponseEntity<>(parsedNews, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<News>> getAllNews() {
        List<News> newsList = newsService.getAllNews();
        return new ResponseEntity<>(newsList, HttpStatus.OK);
    }

    @GetMapping("/period")
    public ResponseEntity<List<News>> getNewsByPeriod(@RequestParam LocalDateTime start, @RequestParam LocalDateTime end) {
        List<News> newsList = newsService.getNewsByPeriod(start, end);
        return new ResponseEntity<>(newsList, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<News> addNews(@RequestBody News news) {
        News savedNews = newsService.saveNews(news);
        return new ResponseEntity<>(savedNews, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNews(@PathVariable Long id) {
        newsService.deleteNews(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Парсинг новин

}
