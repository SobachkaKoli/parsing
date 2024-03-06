package com.example.parsing.service.impl;

import com.example.parsing.model.News;
import com.example.parsing.repository.NewsRepository;
import com.example.parsing.service.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NewsServiceImpl implements NewsService {

    private final NewsRepository newsRepository;
    @Override
    public List<News> getAllNews() {
        return newsRepository.findAll();
    }
    @Override
    public List<News> getNewsByPeriod(LocalDateTime start, LocalDateTime end) {
        return newsRepository.findByNewsDateBetween(start, end);
    }
    @Override
    public News saveNews(News news) {
        return newsRepository.save(news);
    }
    public void deleteNews(Long id) {
        newsRepository.deleteById(id);
    }

}
