package com.analytics.spring.service.impl;

import com.analytics.spring.model.Constans.Constans;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by Dmitry Natalenko on 09.06.2015.
 */
@Component
public class QuotesInitializer {

    @Autowired
    private QuotesService quotesService;

    //@Scheduled(cron="10 0 8 * * MON-FRI")
    @Scheduled(fixedRate=50000)
    public void checkForUpdates(){
        quotesService.updatePrevClose(Constans.SYMBOLS);
    }
}
