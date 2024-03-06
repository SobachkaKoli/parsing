package com.example.parsing.repository;

import com.example.parsing.model.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface NewsRepository extends JpaRepository<News,Long> {

    List<News> findByNewsDateBetween(LocalDateTime start, LocalDateTime end);

    boolean existsByNewsTitle(String newsTitle);
}
