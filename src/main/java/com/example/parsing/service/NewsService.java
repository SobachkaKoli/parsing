package com.example.parsing.service;

import com.example.parsing.model.News;

import java.time.LocalDateTime;
import java.util.List;

public interface NewsService {

    List<News> getAllNews();

    List<News> getNewsByPeriod(LocalDateTime start, LocalDateTime end);

    News saveNews(News news);

    void deleteNews(Long id);
}
