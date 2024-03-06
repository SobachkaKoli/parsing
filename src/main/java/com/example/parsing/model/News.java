package com.example.parsing.model;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Entity
@Table(name = "news")
public class News {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="newsId")
    private Long id;
    @Column(name="news_title", length=200, nullable=false)
    private String newsTitle;
    @Column(name="news_text", length=200, nullable=false)
    private String newsText;
    @Column(name="news_date")
    private Timestamp newsDate;
}
