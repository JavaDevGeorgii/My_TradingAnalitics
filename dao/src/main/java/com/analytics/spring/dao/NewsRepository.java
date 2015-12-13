package com.analytics.spring.dao;

import com.analytics.spring.model.News;

import java.util.List;

/**
 * Created by Alexandr Velikiy on 16.06.2015.
 */
public interface NewsRepository {

    News createNews(News news);

    void deleteNews(Long id);

    News getNewsById(Long id);

    News getNewsByTopicUrl(String topicUrl);

    List<News> getFirstTenNews();

    List<News> getPreviousNews(String lastUrl);

    List<News> getNextNews(String lastUrl);
}
