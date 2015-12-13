package com.analytics.spring.service.impl;


import com.analytics.spring.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by Velikiy Alexandr on 09.06.2015.
 */
@Component
public class NewsScheduler {

    @Autowired
    private NewsService newsService;

    @Scheduled(fixedRate=10000)
    public void checkForUpdates(){
        newsService.updateNews();
    }
}
