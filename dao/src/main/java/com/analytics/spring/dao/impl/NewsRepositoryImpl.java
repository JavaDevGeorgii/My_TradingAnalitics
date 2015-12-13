package com.analytics.spring.dao.impl;

import com.analytics.spring.dao.NewsRepository;
import com.analytics.spring.model.News;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by Alexandr Velikiy on 16.06.2015.
 */
@Repository("newsRepository")
@Transactional
public class NewsRepositoryImpl implements NewsRepository{


    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public News createNews(News news) {
        entityManager.persist(news);
        return news;
    }

    @Override
    public void deleteNews(Long id) {
        News news = entityManager.find(News.class, id);
        entityManager.remove(news);
    }

    @Override
    public News getNewsById(Long id) {
        News news = entityManager.find(News.class, id);
        return  news;
    }

    @Override
    public News getNewsByTopicUrl(String topicUrl) {
        Query query = entityManager.createQuery("SELECT n from News n WHERE n.topicUrl= :topicUrl");
        query.setParameter("topicUrl",topicUrl);
        List<News> news = query.getResultList();
        if (news.size()>0) {
            News newsByTopicUrl = news.get(0);
            return newsByTopicUrl;
        } else {
            return null;
        }
    }

    @Override
    public List<News> getFirstTenNews() {

        Query query = entityManager.createQuery("SELECT n from News n ORDER BY n.id DESC").setMaxResults(10);

        List<News> listNews = query.getResultList();
        return listNews;
    }

    @Override
    public List<News> getPreviousNews(String lastUrl) {
        News lastNews = getNewsByTopicUrl(lastUrl);

        Long startId = lastNews.getId()-11;
        Long endId = startId+10;

        if  (startId < 0) {
            startId = (long)0;
            endId = (long)10;
        }

        Query query = entityManager.createQuery("SELECT n from News n WHERE n.id BETWEEN :startId and :endId ORDER BY n.id DESC").setMaxResults(10);
        query.setParameter("startId", startId);
        query.setParameter("endId", endId);

        List<News> listNews = query.getResultList();
        return listNews;
    }

    @Override
    public List<News> getNextNews(String lastUrl) {
        News lastNews = getNewsByTopicUrl(lastUrl);

        Long startId = lastNews.getId()+1;
        Long endId = lastNews.getId()+11;

        Query query = entityManager.createQuery("SELECT n from News n WHERE n.id BETWEEN :startId and :endId ORDER BY n.id DESC").setMaxResults(10);
        query.setParameter("startId", startId);
        query.setParameter("endId", endId);

        List<News> listNews = query.getResultList();
        return listNews;
    }
}
