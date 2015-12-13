package com.analytics.spring.service.impl;

import com.analytics.spring.model.CurrencyPair;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import yahoofinance.YahooFinance;
import yahoofinance.quotes.fx.FxQuote;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Dmitry Natalenko on 09.06.2015.
 */
@Service
public class QuotesService {


    @Autowired
    private CacheManager cacheManager;

    private Cache quotesCache;


    @PostConstruct
    public void init() {
        quotesCache = cacheManager.getCache("quotecache");
    }

    @Async
    public void updatePrevClose(String[] symbols){
        List<CurrencyPair> list = new ArrayList<>();
        for (int i = 0; i < symbols.length; i++) {
            Document doc = null;
            try {
                doc = Jsoup.connect("http://finance.yahoo.com/q?s="+symbols[i]+"").get();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Elements links = doc.select("#table1");
            String arr[] = links.text().split(" ");
            String prevClose = arr[2];
            CurrencyPair currencyPair = new CurrencyPair();
            currencyPair.setSymbol(symbols[i].substring(0,6));
            currencyPair.setPrevClose(Double.parseDouble(prevClose));
            list.add(currencyPair);
        }
        quotesCache.put(new Element("prevCloseList",list));
    }

    public List getPrevCloseList(){
        Cache cache = cacheManager.getCache("quotecache");
        Element element = cache.get("prevCloseList");
        List<CurrencyPair> prevCloseList = new ArrayList<>();
        if (element != null) {
            prevCloseList = (List<CurrencyPair>) element.getObjectValue();
        }
        return prevCloseList;
    }

    public List getCurrentRatioOfPairs(String[] symbols){
        Map<String,FxQuote> map = YahooFinance.getFx(symbols);
        List<CurrencyPair> items = new ArrayList<>();
        for (Map.Entry<String, FxQuote> entry : map.entrySet()) {
            CurrencyPair currencyPair = new CurrencyPair();
            FxQuote value = entry.getValue();
            String symbol = value.toString().substring(0, 6);
            String ratio = value.toString().substring(10);
            currencyPair.setSymbol(symbol);
            currencyPair.setRatio(Double.parseDouble(ratio));
            items.add(currencyPair);
        }
        return items;
    }
}
