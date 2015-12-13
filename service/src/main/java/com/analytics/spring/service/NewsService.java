package com.analytics.spring.service;

import com.analytics.spring.model.News;

import java.util.List;

/**
 * Created by Alexandr Velikiy on 16.06.2015.
 */
public interface NewsService {

    News createNews(News news);

    void deleteNews(Long id);

    News getNewsById(Long id);

    News getNewsByTopicUrl(String name);

    List<News> getFirstTenNews();

    List getNews();

    List<News> getPreviousNews(String lastUrl);

    List<News> getNextNews(String lastUrl);

    List updateNews();
}
