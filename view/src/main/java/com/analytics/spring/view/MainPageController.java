package com.analytics.spring.view;

import com.analytics.spring.model.Constans.Constans;
import com.analytics.spring.model.News;
import com.analytics.spring.service.NewsService;
import com.analytics.spring.service.impl.QuotesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by Dmitry Natalenko on 08.06.2015.
 */
@Controller
public class MainPageController {

    @Autowired
    private QuotesService quotesService;

    @Autowired
    private NewsService newsService;

    @RequestMapping(value = {"/"}, method = RequestMethod.GET)
    public String  defaultPage(){
        return "index.page";
    }

    @RequestMapping(value="/quotes", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    List getTable(){
        return quotesService.getCurrentRatioOfPairs(Constans.SYMBOLS);
    }

    @RequestMapping(value="/updateQuotes", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    List getUpdateQuotes(){
        return quotesService.getCurrentRatioOfPairs(Constans.SYMBOLS);
    }

    @RequestMapping(value="/getNews",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    List getNewsTable(){
        List<News> newsList = newsService.getNews();

        return newsList;
    }

    @RequestMapping(value="/getPreviousNews",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    List getPreviousNews(@RequestParam("lastUrl") String lastUrl){
        List<News> newsList = newsService.getPreviousNews(lastUrl);

        return newsList;
    }

    @RequestMapping(value="/getNextNews",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    List getNextNews(@RequestParam("lastUrl") String lastUrl){
        List<News> newsList = newsService.getNextNews(lastUrl);

        return newsList;
    }
}


