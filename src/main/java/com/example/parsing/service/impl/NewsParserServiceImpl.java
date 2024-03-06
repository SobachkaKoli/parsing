package com.example.parsing.service.impl;

import com.example.parsing.model.News;
import com.example.parsing.repository.NewsRepository;
import com.example.parsing.service.NewsParserService;
import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NewsParserServiceImpl implements NewsParserService {

    @Autowired
    private NewsRepository newsRepository;

    @Override
    public List<News> parseNews() {

        List<News> newsList = new ArrayList<>();

        try {
            Document doc = Jsoup.connect("https://www.bbc.com/ukrainian").get();

            Elements promoElements = doc.select("div[data-testid=hierarchical-grid]>ul[data-testid=topic-promos] li, div[data-testid=curation-grid-normal]>ul[data-testid=topic-promos] li");

            for (Element promo : promoElements) {
                Element promoTextElement = promo.selectFirst(".promo-text");
                String promoText = promoTextElement.selectFirst("a").text();
                if (newsRepository.existsByNewsTitle(promoText))
                    continue;
                System.out.println(promoText);
                Element timestampElement;
                String timestamp;
                if (promo.selectFirst("time") == null) {
                    continue;
                } else {
                    timestampElement = promo.selectFirst("time.promo-timestamp");
                    timestamp = timestampElement.attr("datetime");
                }


                Element linkElement = promoTextElement.selectFirst("a");
                String link = linkElement.attr("href");

                Timestamp date = parseDate(timestamp); // метод для парсингу дати
                News news = new News();
                news.setNewsTitle(promoText);
                news.setNewsText(link);
                news.setNewsDate(date);

                newsList.add(news);
            }
            return newsList;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }

    private Timestamp parseDate(String dateString) {
        // Шаблон для парсингу дати
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        try {
            // Парсинг рядка у Date
            Date parsedDate = dateFormat.parse(dateString);

            // Конвертація Date в Timestamp
            return new Timestamp(parsedDate.getTime());
        } catch (ParseException e) {
            // Обробка помилки парсингу
            System.err.println("Помилка парсингу дати: " + e.getMessage());
            return null; // або повернення за замовчуванням або викидання винятку
        }
    }
}
