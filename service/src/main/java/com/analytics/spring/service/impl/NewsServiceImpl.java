package com.analytics.spring.service.impl;

import com.analytics.spring.dao.NewsRepository;
import com.analytics.spring.model.News;
import com.analytics.spring.service.NewsService;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import org.jsoup.nodes.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alexandr Velikiy on 10.06.2015.
 */

@Service
public class NewsServiceImpl implements com.analytics.spring.service.NewsService {

    @Autowired
    private NewsRepository newsRepository;

    public List getNews() {

        List<News>  lastNews= newsRepository.getFirstTenNews();

        if (lastNews.size() > 0) {
            return lastNews;
        } else {
            return updateNews();
        }
    }

    @Async
    public List updateNews() {
        List<News> listNews = new ArrayList<>();

        Document doc = null;
        try {
            doc = Jsoup.connect("http://www.bloomberg.com/europe").get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Elements content = doc.select("article.hero-normal-story.hero-story.type-article.site-bbiz.has-reaction");
        for (Element element : content) {
            News news = new News();
            news.setTopicName(element.getElementsByClass("hero-story__information").first().getElementsByTag("a").first().text());
            news.setTopicUrl(element.getElementsByClass("hero-story__information").first().getElementsByTag("a").first().attr("abs:href"));
            String stringTopicPictureUrlFull = element.getElementsByClass("hero-story__image").first().attr("style");
            Integer startPathPoint = stringTopicPictureUrlFull.indexOf("(");
            Integer endPathPoint = stringTopicPictureUrlFull.indexOf(")");
            news.setTopicImageUrl(element.getElementsByClass("hero-story__image").first().attr("style").substring(startPathPoint + 2, endPathPoint - 1));
            //Date carrentDate= new Date();
            news.setDate(new Timestamp(System.currentTimeMillis()));

            News newsFromDB = newsRepository.getNewsByTopicUrl(news.getTopicUrl());
            if (newsFromDB == null) {
                newsRepository.createNews(news);
            }
            listNews.add(news);
        }

        return listNews;
    }

    @Override
    public News createNews(News news) {
       News newNews =  newsRepository.createNews(news);
       return newNews;
    }

    @Override
    public void deleteNews(Long id) {
        newsRepository.deleteNews(id);
    }

    @Override
    public News getNewsById(Long id) {
        News news = newsRepository.getNewsById(id);
        return news;
    }

    @Override
    public News getNewsByTopicUrl(String topicUrl) {
        News news =  newsRepository.getNewsByTopicUrl(topicUrl);
        return news;
    }

    @Override
    public List<News> getFirstTenNews() {
        List<News> listNews =  newsRepository.getFirstTenNews();
        return listNews;
    }

    @Override
    public List<News> getPreviousNews(String lastUrl) {
        List<News> listNews =  newsRepository.getPreviousNews(lastUrl);
        return listNews;
    }

    @Override
    public List<News> getNextNews(String lastUrl) {
        List<News> listNews =  newsRepository.getNextNews(lastUrl);
        return listNews;
    }
}
